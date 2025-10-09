package controller;

// CONTROLLER -> LA COMUNICACIÓN CON EL USUARIO

import entity.Product;
import model.ProductModel;
import util.Validation;

import javax.swing.*;
import java.util.List;

public class ProductController {

    private final ProductModel objProductModel;
    private int addsCounter;
    private int updatesCounter;
    private int deletesCounter;

    // El constructor inicializa las variables.
    public ProductController() {
        this.objProductModel = new ProductModel();
        this.addsCounter = 0;
        this.updatesCounter = 0;
        this.deletesCounter = 0;
    }

    public void createProduct() {

        String productName;

        while (true) {

            productName = Validation.getStringInput("Type the name of the product:");
            if (productName == null) {
                JOptionPane.showMessageDialog(null, "Product creation cancelled.");
                return;
            }
            productName = productName.toLowerCase();

            // Consultamos la base de datos ANTES de continuar. Usamos un metodo específico en el modelo que busca por nombre exacto.
            Product existingProduct = this.objProductModel.findByNameExact(productName);
            if (existingProduct == null) {
                break; // Si es null, el nombre está disponible. Rompemos el bucle
            } else {
                // Si ya existe, informamos al usuario y el bucle continuará
                JOptionPane.showMessageDialog(null, "A product with the name '" + productName + "' already exists. Please choose another name.", "Duplicate Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        try {

            Double price = Validation.getDoubleInput("Type the price of the product:");
            if (price == null) return;

            Integer stock = Validation.getIntInput("Type the initial stock of the product:");
            if (stock == null) return;

//            if (price < 0 || stock < 0) {
//                JOptionPane.showMessageDialog(null, "Price and stock cannot be negative", "Error!!!", JOptionPane.ERROR_MESSAGE);
//                return;
//            }

            Product objProduct = new Product();
            objProduct.setName(productName);
            objProduct.setPrice(price);
            objProduct.setStock(stock);

            // El modelo intenta crear el producto y devuelve el objeto con el ID
            Object objResult = this.objProductModel.insert(objProduct);

            // VALIDACIÓN: Comprobar si la inserción fue exitosa
            if (objProduct != null) {
                Product insertedProduct = (Product) objResult;
                this.addsCounter++;
                JOptionPane.showMessageDialog(null, "Product '" + insertedProduct.getName() + "' added successfully!\n" + insertedProduct);
            } else {
                JOptionPane.showMessageDialog(null, "Could not add product. The name '" + productName + "' already exists.", "Duplicate Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid price or stock. Please enter valid numbers", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listAllProducts() {

        List<Object> listProduct = this.objProductModel.findAll();

        if (listProduct.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The inventory is empty", "Error!!!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // StringBuilder is efficient for building strings in a loop
        StringBuilder inventoryStr = new StringBuilder("------------------- Inventory --------------------\n");
        for (Object obj : listProduct) {
            Product product = (Product) obj;
            inventoryStr.append(product).append("\n");
        }

        // Using a JTextArea inside a JOptionPane to display monospaced text correctly aligned
        JTextArea textArea = new JTextArea(inventoryStr.toString());
        textArea.setEditable(false); // Prevents the user from editing the text
        JScrollPane scrollPane = new JScrollPane(textArea); // Adds a scrollbar if the text is too long
        JOptionPane.showMessageDialog(null, scrollPane, "Product Inventory", JOptionPane.PLAIN_MESSAGE);
    }

    private String generateProductListString() {
        List<Object> products = this.objProductModel.findAll();
        if (products.isEmpty()) {
            return "No products available.";
        }
        StringBuilder listString = new StringBuilder("Available Products:\n");
        for (Object obj : products) {
            listString.append(obj.toString()).append("\n");
        }
        return listString.toString();
    }

    public void updatePrice() {

        String productList = generateProductListString();
        try {
            Integer idUpdate = Validation.getIntInput(productList + "\nEnter the ID of the product to update its price:");
            if (idUpdate == null) return;

            Product existingProduct = this.objProductModel.findById(idUpdate);
            if (existingProduct == null) {
                JOptionPane.showMessageDialog(null, "Product with ID " + idUpdate + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Double newPrice = Validation.getDoubleInput("Enter the new price for '" + existingProduct.getName() + "':");
            if (newPrice == null) return;

            existingProduct.setPrice(newPrice);
            if (this.objProductModel.update(existingProduct)) {
                this.updatesCounter++;
                JOptionPane.showMessageDialog(null, "Price updated successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Could not update price", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID and price", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateStock() {

        String productList = generateProductListString();
        try {
            Integer idUpdate = Validation.getIntInput(productList + "\nEnter the ID of the product to update its stock:");
            if (idUpdate == null) return;

            Product existingProduct = this.objProductModel.findById(idUpdate);
            if (existingProduct == null) {
                JOptionPane.showMessageDialog(null, "Product with ID " + idUpdate + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Integer newStock = Validation.getIntInput("Enter the new stock for '" + existingProduct.getName() + "':");
            if (newStock == null) return;

            existingProduct.setStock(newStock);
            if (this.objProductModel.update(existingProduct)) {
                this.updatesCounter++;
                JOptionPane.showMessageDialog(null, "Stock updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Could not update stock.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID and stock.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteProduct() {

        String productList = generateProductListString();
        try {

            Integer idDelete = Validation.getIntInput(productList + "\nEnter the ID of the product to delete:");
            if (idDelete == null) return;

            Product tempProduct = new Product();
            tempProduct.setId(idDelete);

            if (this.objProductModel.delete(tempProduct)) {
                this.deletesCounter++;
                JOptionPane.showMessageDialog(null, "Product deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Could not delete product. ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void findProductByName() {

        String searchName = Validation.getStringInput("Enter the name (or part of it) to search for:");
        if (searchName == null) return;

//        if (searchName == null || searchName.trim().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Search term cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }

        List<Product> results = this.objProductModel.findByName(searchName.trim());
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No products found matching '" + searchName + "'.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder resultsStr = new StringBuilder("--- SEARCH RESULTS ---\n\n");
        for (Product product : results) {
            resultsStr.append(product).append("\n");
        }

        JTextArea textArea = new JTextArea(resultsStr.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, resultsStr.toString(), "Search Results", JOptionPane.PLAIN_MESSAGE);
    }

    // METODO para obtener el resumen final
    public String getOperationsSummary() {
        return String.format("--- Session Summary ---\nProducts Added: %d\nProducts Updated: %d\nProducts Deleted: %d",
                this.addsCounter, this.updatesCounter, this.deletesCounter);
    }
}