package util;

public class ValidateInput {
    private static final String EMPTY_ERROR = "Input must not be empty!";
    private static final String NEGATIVE_NUMBER_ERROR = "Input must not be negative!";


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
}
