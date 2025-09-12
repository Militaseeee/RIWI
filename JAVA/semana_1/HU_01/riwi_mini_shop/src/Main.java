import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static ArrayList<String> products = new ArrayList<>();
    static double [] prices = new double[0]; // // Inicia vacío, lo expandiremos después
    static HashMap<String, Integer> stock = new HashMap<>();

    static double totalBuy = 0.0;

    public static void main(String[] args) {

        boolean finishProgram = true;
        while (finishProgram) { //se ejecuta mientras no termine el programa
            String menu =
                    """
                        1. Add product.
                        2. List inventory
                        3. Buy product
                        4. Display statistics (cheapest and most expensive)
                        5. Search for product by name
                        6. Exit with final ticket
                    """;
            try {
                String opcStr = JOptionPane.showInputDialog(null, menu, "RIWI Mini-Shop Menu", JOptionPane.PLAIN_MESSAGE);

                // Si el usuario presiona "Cancelar" o cierra el diálogo, salimos
                if (opcStr == null) {
                    finishProgram = false;
                    continue; // Salta al siguiente ciclo del bucle (que terminará)
                }

                int opc = Integer.parseInt(opcStr);

                switch (opc) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        listInventory();
                        break;
                    case 3:
                        buyProduct();
                        break;
                    case 4:
                        //mostrarEstadisticas();
                        System.out.println("HELLO 4");
                        break;
                    case 5:
                        //buscarProducto();
                        System.out.println("HELLO 5");
                        break;
                    case 6:
                        finishProgram = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option. Please choose a number from 1 to 6");
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error!!! You must enter a valid number", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Al salir del bucle, mostramos el ticket final
        JOptionPane.showMessageDialog(null, String.format(" - Goodbye!!!! Come back soon c: - \n Total purchases this session: $%,.2f", totalBuy));

    }


    public static void createProduct() {
        String productName = JOptionPane.showInputDialog("Type the name of the product:");
        // Validamos que el nombre no sea nulo (si presiona cancelar) o vacío
        if (productName == null || productName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "The name of product can't be empty", "Error!!!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        productName = productName.toLowerCase();

        // Validamos que el producto no exista
        if (products.contains(productName)) {
            JOptionPane.showMessageDialog(null, "The product '" + productName + "' already exists in the inventory", "Error!!!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(JOptionPane.showInputDialog("Type the price of the product:"));
            int amount = Integer.parseInt(JOptionPane.showInputDialog("Type the initial stock of the product:"));

            if (price < 0 || amount < 0) {
                JOptionPane.showMessageDialog(null, "Price and stock cannot be negative", "Error!!!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // If everything is correct, we call the utility method to add the product
            addProduct(productName, price, amount);
            JOptionPane.showMessageDialog(null, "Product '" + productName + "' added successfully!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid price or stock. Must be numbers", "Error!!!", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            // El usuario presionó cancelar en algún punto
            JOptionPane.showMessageDialog(null, "Operation cancelled");
        }

    }

    public static void listInventory() {
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The inventory is empty");
            return;
        }

        // StringBuilder -> Es una clase que sirve para construir textos grandes de manera eficiente
        StringBuilder inventaryStr = new StringBuilder("- Inventory -\n\n");
        inventaryStr.append(String.format("%-20s | %-15s | %s\n", "Product", "Price", "Stock"));
        inventaryStr.append("---------------------------------------------------\n");

        for (int i = 0; i < products.size(); i++) {
            String productName = products.get(i);
            double price = prices[i];
            int amount = stock.get(productName); // Obtenemos el stock desde el HashMap usando el nombre como clave
            inventaryStr.append(String.format("%-20s | $%,-14.2f | %d units\n", productName, price, amount));
        }

        // Usamos un JTextArea dentro del JOptionPane para mostrar texto con formato monoespaciado
        JTextArea textArea = new JTextArea(inventaryStr.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "Store Inventory", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void buyProduct() {
        String productName = JOptionPane.showInputDialog("What product do you want to buy?");
        if (productName == null)
            return;  // Si cancela, salimos del metodo

        productName = productName.trim().toLowerCase();

        int i = products.indexOf(productName); //Buscamos si ese producto existe en la lista "products"

        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Sorry, the product '" + productName + "' does not exist", "Product not found", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Pedimos cuántas unidades quiere comprar el usuario
            int amountBuy = Integer.parseInt(JOptionPane.showInputDialog("How many units of '" + productName + "' do you want to buy?"));
            int CurrentStock = stock.get(productName); // Obtenemos el stock actual de ese producto desde el HashMap

            if (amountBuy <= 0) {
                JOptionPane.showMessageDialog(null, "The amount must be greater than zero", "Error!!!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (amountBuy > CurrentStock) {
                JOptionPane.showMessageDialog(null, "There is not enough stock. Only " + CurrentStock + " units.", "Insufficient stock", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double subtotal = prices[i] * amountBuy;

            // Confirmación de la compra
            int confirmacion = JOptionPane.showConfirmDialog(null,
                    String.format("Confirmar compra:\n%d x %s = $%,.2f\n¿Deseas continuar?", amountBuy, productName, subtotal),
                    "Confirmar Compra",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                // Actualizar el stock
                stock.put(productName, CurrentStock - amountBuy);
                // Actualizar el total de la sesión
                totalBuy += subtotal;
                JOptionPane.showMessageDialog(null, "Purchase made successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Purchase canceled");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "You must enter a valid numeric amount", "Error!!!", JOptionPane.ERROR_MESSAGE);
        }
    }



    public static void addProduct(String productName, double price, int amount) {
        // 1. Agregar el nombre al ArrayList
        products.add(productName);
        // 2. Agregar el stock al HashMap
        stock.put(productName, amount);
        // 3. Expandir el array de precios y agregar el nuevo precio
        prices = expandPrices(prices, price);
    }

    // pricesArray -> lo definí como parámetro del metodo
    public static double[] expandPrices(double[] pricesArray, double newPrice) {
        // Creamos un nuevo array con una posición más
        double[] newArray = new double[pricesArray.length + 1];
        // Copiamos todos los elementos del array original al nuevo
        for (int i = 0; i < pricesArray.length; i++) {
            newArray[i] = pricesArray[i];
        }
        // Añadimos el nuevo precio en la última posición
        newArray[newArray.length - 1] = newPrice;
        return newArray;
    }

}



