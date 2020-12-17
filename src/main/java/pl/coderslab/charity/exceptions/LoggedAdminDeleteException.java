package pl.coderslab.charity.exceptions;

public class LoggedAdminDeleteException extends RuntimeException {
    public LoggedAdminDeleteException(String message) {
        super(message);
    }
}
