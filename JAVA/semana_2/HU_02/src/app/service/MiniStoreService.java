package app.service;

import app.interfaces.MiniStoreInterface;
import app.model.Appliance;
import app.model.Food;
import app.model.Product;
import app.util.Validation;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class MiniStoreService implements MiniStoreInterface {
    // --- Data Model ---
    // These are 'static' so they can be accessed from any static method in the class
    private ArrayList<Product> inventory = new ArrayList<>();
    private HashMap<String, Integer> stock = new HashMap<>(); // Maps a product name (String) to its stock count (Integer)
    // Accumulates the total value of purchases made during the session.
    private double totalBuy = 0.0;
    private double[] prices = new double[0];

    public void createProduct() {
        String[] typeOption = {"Food", "Appliance"};
        int productType = JOptionPane.showOptionDialog(null, "What type of product do you want to add?", "Select Type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, typeOption, typeOption[0]);

        // If person cancel the process
        if (productType == -1) {
            JOptionPane.showMessageDialog(null, "Operation Cancelled");
            return;
        }

        String productName = JOptionPane.showInputDialog("Type the name of the product:");

        productName = Validation.validSearchInput(productName);
        if (productName == null) return;

        productName = productName.toLowerCase(); // Standardize to lowercase to prevent duplicates

        if (stock.containsKey(productName)) {
            JOptionPane.showMessageDialog(null, "The product '" + productName + "' already exists.", "Error!!!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(JOptionPane.showInputDialog("Type the price of the product:"));
            int amount = Integer.parseInt(JOptionPane.showInputDialog("Type the initial stock of the product:"));

            // Validate that numbers are not negative
            if (price < 0 || amount < 0) {
                JOptionPane.showMessageDialog(null, "Price and stock cannot be negative", "Error!!!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // If true we create a food object, if not an Appliance object
            Product addNewProduct = (productType == 0) ? new Food(productName, price) : new Appliance(productName, price);
            inventory.add(addNewProduct); // We save it in the product list
            stock.put(productName, amount); // We save your initial stock in the HashMap

            prices = expandPrices(prices, price);

            JOptionPane.showMessageDialog(null, "Product '" + productName + "' added successfully!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid price or stock. Must be numbers", "Error!!!", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            // This catches if the user cancels one of the input dialogs
            JOptionPane.showMessageDialog(null, "Operation cancelled");
        }
    }

    public void listInventory() {
        if (!Validation.inventoryNotEmpty(inventory)) {;
            return;
        }

        // StringBuilder is efficient for building strings in a loop
        StringBuilder inventoryStr = new StringBuilder("------------------- Inventory --------------------\n");
        // String.format() creates a formatted string with placeholders for alignment
        inventoryStr.append(String.format("%-20s | %-15s | %-10s | %s\n", "Product", "Price", "Stock", "Description"));
        inventoryStr.append("---------------------------------------------------\n");

        // Loop through all products to build the display string
        for (Product product : inventory) {
            inventoryStr.append(String.format("%-20s | $%,-14.2f | %-10d | %s\n", product.getNombre(), product.getPrice(), stock.get(product.getNombre()), product.getDescription()));
        }

        // Using a JTextArea inside a JOptionPane to display monospaced text correctly aligned
        JTextArea textArea = new JTextArea(inventoryStr.toString());
        textArea.setEditable(false); // Prevents the user from editing the text
        JScrollPane scrollPane = new JScrollPane(textArea); // Adds a scrollbar if the text is too long
        JOptionPane.showMessageDialog(null, scrollPane, "Store Inventory", JOptionPane.PLAIN_MESSAGE);
    }

    public void buyProduct() {
        if (!Validation.inventoryNotEmpty(inventory)) {;
            return;
        }

        String productName = JOptionPane.showInputDialog("What product do you want to buy?");
        if (productName == null) return; // Exit method if user cancels
        productName = productName.trim().toLowerCase();

        Product productBuy = null;
        for (Product product : inventory) {
            if (product.getNombre().equals(productName)) {
                productBuy = product;
                break;
            }
        }

        if (productBuy == null) {
            JOptionPane.showMessageDialog(null,"Sorry, the product '" + productName + "' does not exist.", "Product not found", JOptionPane.WARNING_MESSAGE);
        }

        try {
            int amountToBuy = Integer.parseInt(JOptionPane.showInputDialog("How many units of '" + productName + "' do you want to buy?"));
            int currentStock = stock.get(productName);

            if (amountToBuy <= 0) {
                JOptionPane.showMessageDialog(null, "The amount must be greater than zero", "Error!!!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (amountToBuy > currentStock) {
                JOptionPane.showMessageDialog(null, "There is not enough stock. Only " + currentStock + " units.", "Insufficient stock", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double subtotal = productBuy.getPrice() * amountToBuy;

            // Use a confirmation dialog to let the user confirm their purchase.
            int confirmation = JOptionPane.showConfirmDialog(null,
                    String.format("Confirm purchase:\n%d x %s = $%,.2f\nDo you wish to continue?", amountToBuy, productName, subtotal),
                    "Confirm Purchase",
                    JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                // Update the stock in the HashMap
                stock.put(productName, currentStock - amountToBuy);
                // Add the subtotal to the session's total
                totalBuy += subtotal;
                JOptionPane.showMessageDialog(null, "Purchase made successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Purchase canceled");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "You must enter a valid numeric amount", "Error!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showStatistics() {
        double priceMajor = prices[0];
        double priceMinor = prices[0];
        int searchMajor = 0;
        int searchMinor = 0;

        if (!Validation.inventoryNotEmpty(inventory)) {;
            return;
        }

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > priceMajor) {
                priceMajor = prices[i];
                searchMajor = i;
            }
            if (prices[i] < priceMinor) {
                priceMinor = prices[i];
                searchMinor = i;
            }
        }

        // Initialize expensive/cheap values with the first product's data
        Product productExpensive = inventory.get(searchMajor);
        Product productCheap = inventory.get(searchMinor);

        String statistics = String.format(
                " -------------- Price statistics -------------- \n\n" +
                        "Most expensive product: %s --> $%,.2f\n" + "Cheapest product: %s --> $%,.2f",
                productExpensive.getNombre(), productExpensive.getPrice(), productCheap.getNombre(), productCheap.getPrice()
        );
        JOptionPane.showMessageDialog(null, statistics);
    }

    public void searchProduct() {
        if (!Validation.inventoryNotEmpty(inventory)) {;
            return;
        }

        String search = JOptionPane.showInputDialog("Type the name of the product you want to search for:");
        if (search == null) return;

        search = Validation.validSearchInput(search);
        if (search == null) return;

        search = search.trim().toLowerCase();
        StringBuilder searchResults = new StringBuilder("----------- Search results ----------- \n\n");
        boolean found = false;

        for (Product product : inventory) {
            if (product.getNombre().toLowerCase().contains(search)) {
                searchResults.append(String.format("Product: %s\nPrice: $%,.2f\nStock: %d units\n\n",
                        product.getNombre(), product.getPrice(), stock.get(product.getNombre()), product.getDescription()));
                found = true;
            }
        }

        if (found) {
            JOptionPane.showMessageDialog(null, searchResults.toString());
        } else {
            // Display the original search term if nothing is found
            JOptionPane.showMessageDialog(null, "No products were found matching this: '" + search + "'.");
        }
    }

    // Expands the prices array by one to accommodate a new product
    public double getTotalBuy() {
        return this.totalBuy;
    }

    public static double[] expandPrices(double[] prices, double newPrice) {
        // Create a new array that is one element larger than the original
        double[] newArray = new double[prices.length + 1];
        // Copy all elements from the old array to the new one
        System.arraycopy(prices, 0, newArray, 0, prices.length);
        // Add the new price at the very end of the new array
        newArray[newArray.length - 1] = newPrice;
        return newArray;
    }
}