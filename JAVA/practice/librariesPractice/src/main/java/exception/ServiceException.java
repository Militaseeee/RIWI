package exception;

// Error 500
public class ServiceException extends RuntimeException {
    private final int statusCode = 500;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
