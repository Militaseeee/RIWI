package util;

import javax.swing.*;
import java.util.List;

public class Inputs {
    // Requests and validates a string using JOptionPane
    public static String requestString(String message, String title) {
        return JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
    }

    // Requests and validates an integer using JOptionPane
    public static int requestInteger(String message, String title) {
        return Integer.parseInt(JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE));
    }

    // Requests and validates a double using JOptionPane
    public static double requestDouble(String message, String title) {
        return Double.parseDouble(JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE));
    }

    public static String requestSelection(String message, String title, List<String> options) {
        Object selected = JOptionPane.showInputDialog(
                null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options.toArray(),
                options.getFirst()
        );

        return selected.toString();
    }


    // String con valor por defecto
    public static String requestString(String message, String title, String defaultValue) {
        return JOptionPane.showInputDialog(
                null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                defaultValue
        ).toString();
    }

    // Integer con valor por defecto
    public static int requestInteger(String message, String title, int defaultValue) {
        return (int) JOptionPane.showInputDialog(
                null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                defaultValue
        );
    }

    // Double con valor por defecto
    public static double requestDouble(String message, String title, double defaultValue) {
        return (double) JOptionPane.showInputDialog(
                null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                defaultValue
        );
    }
}
