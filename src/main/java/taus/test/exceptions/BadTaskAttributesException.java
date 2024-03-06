package taus.test.exceptions;

public class BadTaskAttributesException extends RuntimeException {
    public BadTaskAttributesException() {
        super("Illegal task title or description size");
    }
}
