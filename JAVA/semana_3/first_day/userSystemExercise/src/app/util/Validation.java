package app.util;

import javax.swing.*;

public class Validation {

    public static String validSearchInput(String search) {
        if (search == null) {
            return null;
        }

        if (search.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Search input cannot be empty", "Error!!!", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        return search.trim();
    }

    public static boolean checkEmail(String email) {
        try {
            // Valida que el email no sea null, contenga '@' y tenga un '.' después del '@'
            if (email == null || !email.contains("@") || email.lastIndexOf('.') < email.indexOf('@')) {
                throw new IllegalArgumentException("The email must be in a valid format");
            }
            return true;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Email", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean checkPassword(String password) {
        try {
            if (password == null || password.length() < 6 ) {
                throw new IllegalArgumentException("The password must be major than six digits");
            }
            return true;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Password", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
