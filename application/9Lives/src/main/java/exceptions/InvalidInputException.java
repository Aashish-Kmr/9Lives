package exceptions;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String message) {
        super("Invalid Input: " + message);
    }
}
