//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import controller.ProductController;
import database.ConfigDB;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        ProductController productController = new ProductController();

        boolean finishProgram = true;
        while (finishProgram) {
            String menu = """
                    --- MINI-SHOP INVENTORY ---
                    1. Add product
                    2. List inventory
                    3. Update price
                    4. Update stock
                    5. Delete product
                    6. Search product by name
                    7. Exit with summary
                    """;
            try {
                // Mostramos el menú y capturamos la opción del usuario.
                String opcStr = JOptionPane.showInputDialog(null, menu, "RIWI Mini-Shop Menu", JOptionPane.PLAIN_MESSAGE);

                // If the user clicks "Cancel" or closes the dialog, opcStr will be null
                if (opcStr == null) {
                    finishProgram = false; // Set the flag to exit the while loop
                    continue; // Skip the rest of the current iteration
                }

                int opc = Integer.parseInt(opcStr);

                // The switch statement directs the program to the correct method based on user input
                switch (opc) {
                    case 1 -> productController.createProduct();
                    case 2 -> productController.listAllProducts(); // List product
                    case 3 -> productController.updatePrice();
                    case 4 -> productController.updateStock();
                    case 5 -> productController.deleteProduct();
                    case 6 -> productController.findProductByName();
                    case 7 -> {
                        finishProgram = false; // Set the flag to exit the loop
                        // Mostramos el resumen final antes de cerrar.
                        JOptionPane.showMessageDialog(null, productController.getOperationsSummary() + "\nGoodbye!", "Session Finished", JOptionPane.INFORMATION_MESSAGE);
                    }
                    default -> JOptionPane.showMessageDialog(null, "Invalid option. Please choose a number from 1 to 7");
                }
            } catch (NumberFormatException e) {
                // Capturamos el error si el usuario no ingresa un número.
                JOptionPane.showMessageDialog(null, "Error! You must enter a valid number", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}