import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String nameFruit, typeFruit, colorFruit, originFruit;
        double weightFruit, dateFruit;

        // Pedir cantidad de frutas
        int amountUser = Integer.parseInt(JOptionPane.showInputDialog(null, "How many fruits do you want to add?", "RIWI fruits", JOptionPane.PLAIN_MESSAGE));

        List<Fruit> hamper = new ArrayList<>();

        for (int i = 0; i < amountUser; i++) {
            // Mostrar contador
            JOptionPane.showMessageDialog(null, String.format("Fruit %d of %d", i + 1, amountUser));

            // Pedir datos
            nameFruit = JOptionPane.showInputDialog(null, "Type the name of fruit:");
            typeFruit = JOptionPane.showInputDialog(null, "Type the type of fruit:");
            colorFruit = JOptionPane.showInputDialog(null, "Type the color of fruit:");
            originFruit = JOptionPane.showInputDialog(null, "Type the origin of fruit:");

            weightFruit = Double.parseDouble(JOptionPane.showInputDialog(null, "Type the weight of fruit:"));
            dateFruit = Double.parseDouble(JOptionPane.showInputDialog(null, "Type the expiration date (as number):"));

            // Crear objeto Fruit y agregarlo a la lista
            Fruit inputFruit = new Fruit(nameFruit, typeFruit, colorFruit, originFruit, weightFruit, dateFruit);
            hamper.add(inputFruit);
        }

        StringBuilder inventoryStr = new StringBuilder("------------------- Inventory --------------------\n");
        // String.format() creates a formatted string with placeholders for alignment
        inventoryStr.append(String.format("%-15s | %-10s | %-10s | %-10s | %-10s | %-10s\n", "Fruit", "Type", "color", "origin", "weight", "date"));
        inventoryStr.append("---------------------------------------------------\n");

        for (Fruit fruit : hamper) {
            inventoryStr.append(String.format("%-15s | %-10s | %-10s | %-10s | %-10.2f | %-10.2f\n", fruit.name, fruit.type, fruit.color, fruit.origin, fruit.weight, fruit.date));
        }

        // Using a JTextArea inside a JOptionPane to display monospaced text correctly aligned
        JTextArea textArea = new JTextArea(inventoryStr.toString());
        textArea.setEditable(false); // Prevents the user from editing the text
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea); // Adds a scrollbar if the text is too long
        JOptionPane.showMessageDialog(null, scrollPane, "Fruit Inventory", JOptionPane.PLAIN_MESSAGE);




//        Scanner sc = new Scanner(System.in);
//
//        String nameFruit = "", typeFruit = "", colorFruit = "", originFruit = "";
//        double weightFruit = 0.0, dateFruit = 0.0;
//
//        System.out.println("Type how many fruits you want to add:");
//        int amountUser = sc.nextInt();
//        sc.nextLine();
//
//        List<Fruit> hamper = new ArrayList<>();
//
//        for (int i = 0; i < amountUser; i++) {
//            System.out.printf("\n--- Fruit %d of %d ---\n", i + 1, amountUser);
//
//            System.out.println("\nType the name of fruit: ");
//            nameFruit = sc.nextLine();
//
//            System.out.println("\nType the type of fruit:");
//            typeFruit = sc.nextLine();
//
//            System.out.println("\nType the color of fruit:");
//            colorFruit = sc.nextLine();
//
//            System.out.println("\nType the origin of fruit:");
//            originFruit = sc.nextLine();
//
//            System.out.println("\nType the weight of fruit:");
//            weightFruit = sc.nextDouble();
//
//            System.out.println("\nType the date the expire of fruit:");
//            dateFruit = sc.nextDouble();
//
//            Fruit inputFruit = new Fruit(nameFruit, typeFruit, colorFruit, originFruit, weightFruit, dateFruit);
//            hamper.add(inputFruit);
//        }
//
//        for ( Fruit fruit : hamper ) {
//            System.out.println(fruit.name);
//        }






    }
}