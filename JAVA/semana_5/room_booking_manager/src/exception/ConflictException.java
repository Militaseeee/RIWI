package exception;

// Error 409
public class ConflictException extends BookingException {
    public ConflictException(String message) {
        super(message);
    }
}
