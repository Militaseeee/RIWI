package util;

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

}
