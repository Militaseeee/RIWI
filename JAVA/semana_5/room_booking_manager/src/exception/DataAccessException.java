package exception;

// Checked Exception
public class DataAccessException extends Exception {

    public DataAccessException(String message) {
        super(message); // Passes the message to the parent Exception class
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
