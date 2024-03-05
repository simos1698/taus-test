package taus.test.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("Username " + username + " already belongs to another user.");
    }
}
