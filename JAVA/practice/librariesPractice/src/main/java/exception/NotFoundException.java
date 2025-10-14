package exception;

// Error 404
public class NotFoundException extends RuntimeException {
    private final int statusCode = 404;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
