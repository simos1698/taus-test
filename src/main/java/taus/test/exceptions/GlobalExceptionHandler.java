package taus.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedTaskAccessException.class)
    public ResponseEntity<String> handleUnauthorizedTaskAccessException(UnauthorizedTaskAccessException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized access to task");
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Void> handleTaskNotFoundException(TaskNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
