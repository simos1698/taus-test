package taus.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import taus.test.dtos.TaskDTO;
import taus.test.entities.Task;
import taus.test.entities.User;
import taus.test.events.TaskExpiredEvent;
import taus.test.exceptions.BadTaskAttributesException;
import taus.test.exceptions.TaskNotFoundException;
import taus.test.exceptions.UnauthorizedTaskAccessException;
import taus.test.exceptions.UserTaskLimitReachedException;
import taus.test.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Value("${maxTasks}")
    private int maxTasks;
    private List<Task> alreadyExpiredTasks = new ArrayList<>();

    @Scheduled(fixedDelay = 5000) // Run every five seconds
    public void checkForExpiredTasks() {
        Date currentDate = new Date();
        List<Task> expiredTasks = new ArrayList<>(taskRepository
                .findAll()
                .stream()
                .filter(task -> currentDate.after(task.getDueDate()))
                .toList());

        expiredTasks.removeAll(alreadyExpiredTasks);

        for (Task task : expiredTasks) {
            eventPublisher.publishEvent(new TaskExpiredEvent(this,task.getId()));
            alreadyExpiredTasks.add(task);
        }
    }

    public TaskDTO getTaskById(Long id, String username){
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()){

            Task task = optionalTask.get();

            if(task.getUser().getUsername().equals(username)){
                return optionalTask.get().toDTO();
            }
            else throw new UnauthorizedTaskAccessException();
        }
        else throw new TaskNotFoundException();
    }

    public List<TaskDTO> getAllTasks(String username) {
        Long connectedUserId= userService.getUserByUsername(username).getId();

        return taskRepository.findByUserId(connectedUserId).stream().map(Task::toDTO).toList();
    }

    public TaskDTO createTask(TaskDTO taskDTO, String username) {
        User user = userService.getUserByUsername(username);

        //Example data validation
        if(taskDTO.title().length() > 100 || taskDTO.description().length()>100){
            throw new BadTaskAttributesException();
        }

        if (user.getTasks().size()<maxTasks){
            Task createdTask = taskRepository.save(new Task(taskDTO, user));
            return createdTask.toDTO();
        }
        else throw new UserTaskLimitReachedException();

    }

    public TaskDTO editTask(Long id, TaskDTO taskDTO) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isEmpty()) {
            throw new TaskNotFoundException();
        }
        Task task = taskOptional.get();

        //Example data validation
        if(taskDTO.title().length() > 100 || taskDTO.description().length()>100){
            throw new BadTaskAttributesException();
        }
        if(!taskDTO.username().equals(task.getUser().getUsername())){
            User newAssignedUser = userService.getUserByUsername(taskDTO.username());

            //make sure that if we define a different user for the task he has the necessary assignment space
            if (newAssignedUser.getTasks().size()>=maxTasks){
                throw new UserTaskLimitReachedException();
            }
            task.setUser(newAssignedUser);
        }

        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setDueDate(taskDTO.dueDate());

        Task savedTask = taskRepository.save(task);
        return task.toDTO();
    }

    public void deleteTask(Long id, String username){
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()){

            Task task = optionalTask.get();

            if(task.getUser().getUsername().equals(username)){
                taskRepository.deleteById(id);
            }
            else throw new UnauthorizedTaskAccessException();
        }
        else throw new TaskNotFoundException();
    }
}

