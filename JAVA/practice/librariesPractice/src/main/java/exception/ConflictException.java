package exception;

// Error 409
public class ConflictException extends RuntimeException {
    private final int statusCode = 409;

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
