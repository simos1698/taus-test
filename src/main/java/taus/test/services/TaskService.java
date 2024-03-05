package taus.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import taus.test.entities.Task;
import taus.test.entities.User;
import taus.test.exceptions.TaskNotFoundException;
import taus.test.exceptions.UnauthorizedTaskAccessException;
import taus.test.repositories.TaskRepository;
import taus.test.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Value("${maxTasks}")
    private int maxTasks;

    public Task getTaskById(Long id, String username){
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()){

            Task task = optionalTask.get();

            if(task.getUser().getUsername().equals(username)){
                return optionalTask.get();
            }
            else throw new UnauthorizedTaskAccessException();
        }
        else throw new TaskNotFoundException();
    }

    public List<Task> getAllTasks(String username) {
        Long connectedUserId= userRepository.findByUsername(username).getId();

        return taskRepository.findByUserId(connectedUserId);
    }
}

