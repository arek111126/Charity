package pl.coderslab.charity.exception;

public class NotFoundUserException extends Throwable {
    public NotFoundUserException(String message) {
        super(message);
    }
}
