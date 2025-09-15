import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Fruit> listFruit = new ArrayList<>();

    public static void main(String[] args) {



        boolean finishProgram = true;


        do {
            String opcStr = JOptionPane.showInputDialog(null, "----------- Menu ----------- \n"+
                    "1. Crear fruta\n" +
                    "2. Listar todas\n" +
                    "3. Buscar (por id o por nombre parcial)\n" +
                    "4. Actualizar (cambiar uno o más atributos)\n" +
                    "5. Eliminar (por id)\n" +
                    "6. Salir\n", "Fruit Menu c:", JOptionPane.PLAIN_MESSAGE);
            switch (opcStr) {
                case "1":
                    createFruit();
                    break;
                case "2":
                    listFruit();
                    break;
                case "3":
                    searchFruit();
                    break;
                case "4":
                    updateFruit();
                    break;
                case "5":
                    deleteFruit();
                    break;
                case "6":
                    finishProgram = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Incorrect option", "error", JOptionPane.WARNING_MESSAGE);
                    break;
            }


        } while(finishProgram);

    }

    public static void createFruit() {

        String name, color, origin;
        double weightKg, price;
        boolean isOrganic;

        int amountUser = Integer.parseInt(JOptionPane.showInputDialog(null, "How many fruits do you want to add?", "RIWI fruits", JOptionPane.PLAIN_MESSAGE));

        for (int i = 0; i < amountUser; i++) {
            JOptionPane.showMessageDialog(null, String.format("Fruit %d of %d", i + 1, amountUser));

            name = JOptionPane.showInputDialog(null, "Type the name of fruit:");
            weightKg = Double.parseDouble(JOptionPane.showInputDialog(null, "Type the weight of fruit:"));
            color = JOptionPane.showInputDialog(null, "Type the color of fruit:");
            price = Double.parseDouble(JOptionPane.showInputDialog(null, "Type the price of fruit:"));
            origin = JOptionPane.showInputDialog(null, "Type the origin of fruit:");
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

    public static void listFruit() {

    }

    public static void searchFruit() {

    }
    public static void updateFruit() {

    }
    public static void deleteFruit() {

    }

}