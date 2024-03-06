package taus.test.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import taus.test.dtos.TaskDTO;
import taus.test.entities.Task;
import taus.test.entities.User;
import taus.test.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController
{
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        List<TaskDTO> tasks = taskService.getAllTasks(username);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long id) {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        TaskDTO task = taskService.getTaskById(id, username);
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        TaskDTO newTaskDTO = taskService.createTask(taskDTO, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTaskDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> editTask(@RequestBody TaskDTO taskDTO, @PathVariable Long id) {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        TaskDTO newTaskDTO = taskService.editTask(id, taskDTO);
        return ResponseEntity.status(HttpStatus.OK).body(newTaskDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable Long id) {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        taskService.deleteTask(id, username);
        return ResponseEntity.noContent().build();
    }
}
