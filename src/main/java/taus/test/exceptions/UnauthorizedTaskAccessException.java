package taus.test.exceptions;

public class UnauthorizedTaskAccessException extends RuntimeException {
    public UnauthorizedTaskAccessException() {
        super("Unauthorized access to task");
    }
}
