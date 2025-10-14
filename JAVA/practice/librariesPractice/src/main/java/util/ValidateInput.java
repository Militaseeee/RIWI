package util;

import java.util.regex.Pattern;

public class ValidateInput {
    private static final String EMPTY_ERROR = "Input must not be empty!";
    private static final String NEGATIVE_NUMBER_ERROR = "Input must not be negative!";
    private static final String INVALID_EMAIL_ERROR = "Invalid email format!";

    // Expresión regular para validar emails
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    public static void validateString(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR);
        }
    }

    public static void validateNumber(int input){
        validateString(String.valueOf(input));
        if(input < 0){
            throw new IllegalArgumentException(NEGATIVE_NUMBER_ERROR);
        }
    }

    public static void validateEmail(String email) {
        validateString(email); // Reutilizamos para asegurar que no esté vacío
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException(INVALID_EMAIL_ERROR);
        }
    }
}
