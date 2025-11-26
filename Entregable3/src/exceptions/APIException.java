package exceptions;

public class APIException extends Exception {
    public APIException(String msg) {
        super(msg);
    }

    public APIException(String msg, Throwable cause) {
        super(msg, cause); 
    }
}
