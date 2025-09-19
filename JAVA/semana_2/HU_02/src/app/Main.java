package app;

import app.Service.MiniStoreService;

import javax.swing.*;
import java.security.Provider;

public class Main {
    public static void main(String[] args) {
        // Create an instance of your service class
        MiniStoreService service = new MiniStoreService();

        // A boolean flag to control the main loop of the menu
        boolean finishProgram = true;
        while (finishProgram) {
            String menu =
                    """
                        1. Add product
                        2. List inventory
                        3. Buy product
                        4. Display statistics (cheapest and most expensive)
                        5. Search for product by name
                        6. Exit with final ticket
                    """;
            try {
                String opcStr = JOptionPane.showInputDialog(null, menu, "RIWI Mini-Shop Menu", JOptionPane.PLAIN_MESSAGE);

                // If the user clicks "Cancel" or closes the dialog, opcStr will be null
                if (opcStr == null) {
                    finishProgram = false; // Set the flag to exit the while loop
                    continue; // Skip the rest of the current iteration
                }

                int opc = Integer.parseInt(opcStr);

                // The switch statement directs the program to the correct method based on user input
                switch (opc) {
                    case 1:
                        service.createProduct();
                        break;
                    case 2:
                        service.listInventory();
                        break;
                    case 3:
                        service.buyProduct();
                        break;
                    case 4:
                        service.showStatistics();
                        break;
                    case 5:
                        service.searchProduct();
                        break;
                    case 6:
                        finishProgram = false; // Set the flag to exit the loop
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option. Please choose a number from 1 to 6");
                        break;
                }
            } catch (NumberFormatException e) {
                // This catches errors if the user enters non-numeric text
                JOptionPane.showMessageDialog(null, "Error!!! You must enter a valid number", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, String.format(" --- Goodbye!!!! Come back soon c: --- \n Total purchases this session: $%,.2f", service.getTotalBuy()));
    }
}