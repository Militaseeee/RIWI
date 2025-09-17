package app.Service;

import app.Model.Fruit;
import app.Util.Validation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FruitService {
    static List<Fruit> listFruit = new ArrayList<>();

    public static void createFruit() {

        String name, color, origin;
        Double weightKg, price;
        boolean isOrganic;

        Integer amountUser = Validation.getPositiveInt("How many fruits do you want to add?");
        if (amountUser == null) return; // Si el usuario cancela, salimos del metodo

        for (int i = 0; i < amountUser; i++) {
            JOptionPane.showMessageDialog(null, String.format("Fruit %d of %d", i + 1, amountUser));

            name = Validation.valString("Type the name of fruit:");
            if (name == null) break;

            weightKg = Validation.getPositiveDouble("Type the weight of fruit (Kg):");
            if (weightKg == null) break;

            color = Validation.valString("Type the color of fruit:");
            if (color == null) break;

            price = Validation.getPositiveDouble("Type the price of fruit:");
            if (price == null) break;

            origin = Validation.valString("Type the origin of fruit:");
            if (origin == null) break;

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
                } else {
                    isOrganic = selected.equals("Yes");
                }
            }

            Fruit fruit = new Fruit(name, weightKg, color, price, origin, isOrganic);
            listFruit.add(fruit);
        }
    }

    public static void fruitList() {
        if (Validation.isListEmpty(listFruit, "fruit")) {
            return;
        }

        StringBuilder list = new StringBuilder();
        for (Fruit fruit : listFruit) {
            list.append(fruit.toString());
        }
        JOptionPane.showMessageDialog(null, list.toString());
    }

    public static void searchFruit() {
        if (Validation.isListEmpty(listFruit, "fruit")) {
            return;
        }

        String searchOption = JOptionPane.showInputDialog(
                null,
                "Search fruit by:\n" +
                        "1. ID\n" +
                        "2. Name (partial match)",
                "Search Menu",
                JOptionPane.QUESTION_MESSAGE
        );
        if (searchOption == null) return;

        StringBuilder result = new StringBuilder("Search result:\n");

        boolean found = false;
        switch (searchOption) {
            case "1":
                Integer idSearch = Validation.getPositiveInt("Enter the fruit ID to search:");
                if (idSearch == null) return;

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
                break;
            case "2":
                String nameSearch = JOptionPane.showInputDialog(
                        null, "Enter the fruit name (or part of it) to search:"
                );
                if (nameSearch == null) return;
                // nameSearch = Validation.valDataNull(nameSearch, "You must enter a name to search");
                nameSearch = nameSearch.toLowerCase();
                for (Fruit fruit : listFruit) {
                    if(fruit.getName().toLowerCase().contains(nameSearch)) {
                        result.append(fruit.toString()).append("\n");
                        found = true;
                    }
                }

                if (!found) {
                    result.append("No fruits found with name containing '").append(nameSearch).append("'");
                }
                break;
        }
    }

    public static void updateFruit() {
        if (Validation.isListEmpty(listFruit, "fruit")) {
            return;
        }

        Integer idUpdate = Validation.getPositiveInt("Enter the ID of the fruit to update:");
        if (idUpdate == null) return;

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
            if (option == null);

            switch (option) {
                case "1":
                    String newName = Validation.valString("Enter new name:");
                    if (newName != null) fruitToUpdate.setName(newName);
                    break;
                case "2":
                     Double newWeight = Validation.getPositiveDouble("Enter new weight:");
                     if (newWeight != null) fruitToUpdate.setWeightKg(newWeight);
                     break;
                case "3":
                    String newColor = Validation.valString("Enter new color:");
                    if (newColor != null) fruitToUpdate.setColor(newColor);
                    break;
                case "4":
                    Double newPrice = Validation.getPositiveDouble("Enter new price:");
                    if (newPrice != null) fruitToUpdate.setPrice(newPrice);
                    break;
                case "5":
                    String newOrigin = Validation.valString("Enter new origin:");
                    if (newOrigin != null) fruitToUpdate.setOrigin(newOrigin);
                    break;
                case "6":
                    int confirm = JOptionPane.showConfirmDialog(null, "Is the fruit organic?");
                    fruitToUpdate.setIsOrganic(confirm == JOptionPane.YES_OPTION);
                    break;
                case "7":
                    keepUpdating = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option");
                    break;
            }
        }
        JOptionPane.showMessageDialog(null, "Fruit updated successfully:\n" + fruitToUpdate);
    }

    public static void deleteFruit() {
        if (Validation.isListEmpty(listFruit, "fruit")) {
            return;
        }

        Integer idDelete = Validation.getPositiveInt("Enter the ID of the fruit to delete:");
        if (idDelete == null) return;

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
    }
}