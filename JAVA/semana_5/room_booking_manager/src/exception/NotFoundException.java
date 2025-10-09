package exception;

// Error 404
public class NotFoundException extends BookingException {
    public NotFoundException(String message) {
        super(message);
    }
}
