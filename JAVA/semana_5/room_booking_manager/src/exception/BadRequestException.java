package exception;

// Error 400
public class BadRequestException extends BookingException {
    public BadRequestException(String message) {
        super(message);
    }
}
