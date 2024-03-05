package taus.test.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import taus.test.entities.User;

@RestController
public class TaskController
{
    public TaskController() {
    }

    @GetMapping("/tasks")
    public ResponseEntity<String> getAllTasks(@AuthenticationPrincipal String username) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return ResponseEntity.ok("no tasks for user " + authentication.getName()); // Return 200 OK with user data
    }
}
