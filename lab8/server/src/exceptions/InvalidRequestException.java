package exceptions;

public class InvalidRequestException extends Exception {
    public InvalidRequestException(String msg) {
        super(msg);
    }
}
