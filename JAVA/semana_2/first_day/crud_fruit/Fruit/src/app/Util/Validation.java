package app.Util;

import javax.swing.*;
import java.util.List;

public class Validation {
    public static String valDataNull(String valueVal, String message) {
        if (valueVal == null || valueVal.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, message);
            return null;
        }
        return valueVal.trim();
    }

    public static String valString(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "This field cannot be empty");
            return null;
        }
        return input.trim();
    }

    public static Integer getPositiveInt(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message);
            if (input == null) { // El usuario presionó "Cancelar"
                return null;
            }
            try {
                int number = Integer.parseInt(input);
                if (number > 0) {
                    return number; // El número es válido
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a number greater than 0");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid format. Please enter a valid number");
            }
        }
    }

    public static Double getPositiveDouble(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message);
            if (input == null) { // El usuario presionó "Cancelar"
                return null;
            }
            try {
                double number = Double.parseDouble(input);
                if (number >= 0) {
                    return number; // El número es válido
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a positive number (or 0)");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid format. Please enter a valid number");
            }
        }
    }

    public static boolean isListEmpty(List<?> list, String listName) {
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The " + listName + " list is empty.");
            return true;
        }
        return false;
    }
}
