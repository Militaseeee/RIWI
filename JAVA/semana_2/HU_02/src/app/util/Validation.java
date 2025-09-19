package app.util;

import app.model.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Validation {
    // Validates if the search input is not null or empty
    public static String validSearchInput(String search) {
        if (search == null || search.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Search input cannot be empty", "Error!!!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return search.trim().toLowerCase();
    }


    // Checks if the inventory is not empty
    public static boolean inventoryNotEmpty(ArrayList<?> inventory) {
        if (inventory.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The inventory is empty", "Error!!!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
