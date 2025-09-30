package util;

import javax.swing.*;

public class Validation {

    // Pide al usuario un número entero (int) y no lo deja avanzar hasta que ingrese un valor válido y no negativo
    public static Integer getIntInput(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(message);
            if (input == null) { // El usuario canceló
                return null;
            }
            try {
                int value = Integer.parseInt(input);
                if (value < 0) {
                    JOptionPane.showMessageDialog(null, "The number cannot be negative", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    continue; // Vuelve a pedir el número
                }
                return value; // El valor es válido, salimos del bucle
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid integer", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static Double getDoubleInput(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(message);
            if (input == null) { // El usuario canceló
                return null;
            }
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    JOptionPane.showMessageDialog(null, "The number cannot be negative", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    continue; // Vuelve a pedir el número
                }
                return value; // El valor es válido, salimos del bucle
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid decimal number (example: 123.45)", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String getStringInput(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(message);
            if (input == null) { // El usuario canceló
                return null;
            }
            if (input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "This field cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                // El bucle continuará pidiendo el texto
            } else {
                return input.trim(); // El valor es válido, lo devolvemos sin espacios extra
            }
        }
    }
}
