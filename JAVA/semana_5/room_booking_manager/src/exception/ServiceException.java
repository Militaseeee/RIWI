package exception;

// Error 500
public class ServiceException extends BookingException {
    // To wrap technical exceptions (Wrapping)
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
