package taus.test.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import taus.test.entities.Task;
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
    public ResponseEntity<List<Task>> getAllTasks() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        List<Task> tasks = taskService.getAllTasks(username);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        Task task = taskService.getTaskById(id, username);
        return ResponseEntity.ok(task);
    }
}
