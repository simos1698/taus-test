package taus.test.exceptions;

public class UserTaskLimitReachedException extends RuntimeException {
    public UserTaskLimitReachedException() {
        super("User cannot be currently assigned any more tasks.");
    }
}
