package pl.coderslab.charity.exceptions;

public class InvalidOldPasswordException extends RuntimeException{
    public InvalidOldPasswordException(String message) {
        super(message);
    }
}
