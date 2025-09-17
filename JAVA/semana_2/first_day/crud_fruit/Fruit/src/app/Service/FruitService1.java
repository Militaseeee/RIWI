package app.Service;

import app.Model.Fruit;
import app.Util.Validation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FruitService1 {
    static List<Fruit> listFruit = new ArrayList<>();

    public static void createFruit() {

        String name, color, origin;
        double weightKg, price;
        boolean isOrganic;

        int amountUser;
        try {
            amountUser = Integer.parseInt(JOptionPane.showInputDialog(null, "How many fruits do you want to add?", "RIWI fruits", JOptionPane.PLAIN_MESSAGE));
            if (amountUser <= 0) {
                JOptionPane.showMessageDialog(null, "Please enter a number greater than 0");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number. Please enter a valid quantity");
            return;
        }

        for (int i = 0; i < amountUser; i++) {
            JOptionPane.showMessageDialog(null, String.format("Fruit %d of %d", i + 1, amountUser));

            name = JOptionPane.showInputDialog(null, "Type the name of fruit:");
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty. This fruit will not be added");
                break;
            }

            try {
                String weightStr = JOptionPane.showInputDialog(null, "Type the weight of fruit:");
                if (weightStr == null) {
                    JOptionPane.showMessageDialog(null, "Operation canceled. Creation process stopped.");
                    break;
                }
                weightKg = Double.parseDouble(weightStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid weight format. This fruit will not be added");
                break;
            }

            color = JOptionPane.showInputDialog(null, "Type the color of fruit:");
            if (color == null || color.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Color cannot be empty. This fruit will not be added");
                break;
            }

            try {
                String priceStr = JOptionPane.showInputDialog(null, "Type the price of fruit:");
                if (priceStr == null) {
                    JOptionPane.showMessageDialog(null, "Operation canceled. Creation process stopped.");
                    break;
                }
                price = Double.parseDouble(priceStr);

                if (price < 0) {
                    JOptionPane.showMessageDialog(null, "Price cannot be negative. This fruit will not be added");
                    break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid price format. This fruit will not be added");
                break;
            }

            origin = JOptionPane.showInputDialog(null, "Type the origin of fruit:");
            if (origin == null || origin.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Origin cannot be empty. This fruit will not be added");
                break;
            }

            String[] options = {"-- Select one option --", "Yes", "No"};
            JComboBox<String> comboBox = new JComboBox<>(options);
            int result = JOptionPane.showConfirmDialog(
                    null,
                    comboBox,
                    "Is the fruit organic?",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );




            isOrganic = false;

            if (result == JOptionPane.OK_OPTION) {
                String selected = (String) comboBox.getSelectedItem();

                if (selected.equals("-- Select one option --")) {
                    JOptionPane.showMessageDialog(null,
                            "Please select Yes or No before continuing.",
                            "Invalid selection",
                            JOptionPane.WARNING_MESSAGE);
                    // Aquí puedes volver a mostrar el diálogo si quieres forzar que elija bien
                } else {
                    isOrganic = selected.equals("Yes");
                }
            }

            Fruit fruit = new Fruit(name, weightKg, color, price, origin, isOrganic);
            listFruit.add(fruit);
        }
    }

    public static void fruitList() {
        StringBuilder list = new StringBuilder();
        for (Fruit fruit : listFruit) {
            list.append(fruit.toString());
        }
        JOptionPane.showMessageDialog(null, list.toString());
    }

    public static void searchFruit() {
        String searchOption = JOptionPane.showInputDialog(
                null,
                "Search fruit by:\n" +
                        "1. ID\n" +
                        "2. Name (partial match)",
                "Search Menu",
                JOptionPane.QUESTION_MESSAGE
        );

        StringBuilder result = new StringBuilder("Search result:\n");

        switch (searchOption) {
            case "1":
                try {
                    int idSearch = Integer.parseInt(
                            JOptionPane.showInputDialog(null, "Enter the fruit ID to search:")
                    );

                    boolean found = false;
                    for (Fruit fruit : listFruit) {
                        if (fruit.getIdFruit() == idSearch) {
                            result.append(fruit).append("\n");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        result.append("No fruit found with ID ").append(idSearch);
                    }

                    JOptionPane.showMessageDialog(null, result.toString());

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid ID format");
                }
                break;

            case "2":

                String nameSearch = JOptionPane.showInputDialog(
                        null, "Enter the fruit name (or part of it) to search:"
                );

                nameSearch = Validation.valDataNull(nameSearch, "You must enter a name to search");

                if (nameSearch == null) {
                    return; // o salir del metodo si no hay valor válido
                }

                nameSearch = nameSearch.toLowerCase();
                boolean anyFound = false;

                for (Fruit fruit : listFruit) {
                    if(fruit.getName().toLowerCase().contains(nameSearch)) {
                        result.append(fruit.toString()).append("\n");
                        anyFound = true;
                    }
                }

                if (!anyFound) {
                    JOptionPane.showMessageDialog(null,
                            "No fruits found with name containing '" + nameSearch + "'");
                } else {
                    JOptionPane.showMessageDialog(null, result.toString());
                }
                break;
        }
    }

    public static void updateFruit() {
        try {
            int idUpdate = Integer.parseInt(
                    JOptionPane.showInputDialog(null, "Enter the ID of the fruit to update:")
            );

            Fruit fruitToUpdate = null;
            for (Fruit fruit : listFruit) {
                if (fruit.getIdFruit() == idUpdate) {
                    fruitToUpdate = fruit;
                    break;
                }
            }

            if (fruitToUpdate == null) {
                JOptionPane.showMessageDialog(null, "No fruit found with ID " + idUpdate);
                return;
            }

            boolean keepUpdating = true;
            while (keepUpdating) {
                String option = JOptionPane.showInputDialog(
                        null,
                        "Updating fruit: " + fruitToUpdate.getName() +
                                "\n1. Name\n2. Weight\n3. Color\n4. Price\n5. Origin\n6. IsOrganic\n7. Finish",
                        "Update Menu",
                        JOptionPane.QUESTION_MESSAGE
                );

                switch (option) {
                    case "1":
                        String newName = JOptionPane.showInputDialog("Enter new name:");
                        if (newName != null && !newName.trim().isEmpty()) {
                            fruitToUpdate.setName(newName.trim());
                        }
                        break;
                    case "2":
                        try {
                            double newWeight = Double.parseDouble(JOptionPane.showInputDialog("Enter new weight:"));
                            if (newWeight > 0) fruitToUpdate.setWeightKg(newWeight);
                            else JOptionPane.showMessageDialog(null, "Weight must be > 0");
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid weight value.");
                        }
                        break;
                    case "3":
                        String newColor = JOptionPane.showInputDialog("Enter new color:");
                        if (newColor != null && !newColor.trim().isEmpty()) {
                            fruitToUpdate.setColor(newColor.trim());
                        }
                        break;
                    case "4":
                        try {
                            double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter new price:"));
                            if (newPrice >= 0) fruitToUpdate.setPrice(newPrice);
                            else JOptionPane.showMessageDialog(null, "Price must be >= 0");
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid price value.");
                        }
                        break;
                    case "5":
                        String newOrigin = JOptionPane.showInputDialog("Enter new origin:");
                        if (newOrigin != null && !newOrigin.trim().isEmpty()) {
                            fruitToUpdate.setOrigin(newOrigin.trim());
                        }
                        break;
                    case "6":
                        int confirm = JOptionPane.showConfirmDialog(null, "Is the fruit organic?");
                        fruitToUpdate.setIsOrganic(confirm == JOptionPane.YES_OPTION);
                        break;
                    case "7":
                        keepUpdating = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option.");
                        break;
                }
            }
            JOptionPane.showMessageDialog(null, "Fruit updated successfully:\n" + fruitToUpdate);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID format");
        }
    }

    public static void deleteFruit() {
        try {
            int idDelete = Integer.parseInt(
                    JOptionPane.showInputDialog(null, "Enter the ID of the fruit to delete:")
            );

            Fruit fruitToDelete = null;
            for (Fruit fruit : listFruit) {
                if (fruit.getIdFruit() == idDelete) {
                    fruitToDelete = fruit;
                    break;
                }
            }

            if (fruitToDelete == null) {
                JOptionPane.showMessageDialog(null, "No fruit found with ID " + idDelete);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this fruit?\n" + fruitToDelete,
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                listFruit.remove(fruitToDelete);
                JOptionPane.showMessageDialog(null, "Fruit deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Deletion canceled.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID format.");
        }
    }
}
