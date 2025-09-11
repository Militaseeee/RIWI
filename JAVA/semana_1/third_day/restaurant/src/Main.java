import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Save information in different HashMaps
        HashMap<Integer, String> customers = new HashMap<>();
        HashMap<Integer, String> orders = new HashMap<>();
        HashMap<Integer, Integer> id_price = new HashMap<>();

        // Self-incrementing ID counter
        int idCounter = 1;
        int opc;

        do {
            // Menu
            System.out.println("\n ------ MAMIJU´S RESTAURANT ------\n");
            System.out.println("1. Show menu");
            System.out.println("2. Register customer and order");
            System.out.println("3. Show registered accounts");
            System.out.println("4. Go out");
            System.out.println("\n ---------------------------------\n");

            System.out.print("Choose an option: ");
            opc = sc.nextInt();
            sc.nextLine(); // It is so that I can write an input

            switch(opc) {
                case 1:
                    // Show restaurant menu with prices
                    System.out.println("\n--- Restaurant menu ---");
                    System.out.println("1. Menu of the day: soup, rice, meat, juice and salad → $17.000");
                    System.out.println("2. Executive menu: salad, chicken, rice, dessert(Mazamorra or bocadillo), juice → $18.000");
                    break;

                case 2:
                    // Register customer
                    System.out.print("Type the customer's name:");
                    String name = sc.nextLine();

                    customers.put(idCounter, name);

                    System.out.println("Choose the menu: \n");
                    System.out.println("1 = Menu of the day");
                    System.out.println("2 = Executive menu");
                    int menuChoice = sc.nextInt();
                    sc.nextLine();

                    if (menuChoice == 1) {
                        orders.put(idCounter, "Menu of the day");
                        id_price.put(idCounter, 17000);
                    } else if (menuChoice == 2) {
                        orders.put(idCounter, "Executive menu");
                        id_price.put(idCounter, 18000);
                    } else {
                        orders.put(idCounter, "Not selected");
                        id_price.put(idCounter, 0);
                    }

                    System.out.println("Registered customer with ID: " + idCounter);
                    idCounter++; // each registered customer is increased
                    break;

                case 3:
                    // Show all customers, orders and accounts
                    System.out.println("\n--- Registered accounts ---");
                    for (Map.Entry<Integer, String> entry : customers.entrySet()) {
                        int id = entry.getKey();
                        String CustomerName = entry.getValue();
                        String order = orders.get(id);
                        int total = id_price.get(id);
                        System.out.println("ID: " + id + " → Nombre: " + CustomerName + " → Pedido: " + order + " → Total: $" + total);
                    }
                    break;

                case 4:
                    System.out.println("Are you sure you want to exit? (y/n)");
                    String confirm = sc.nextLine().toLowerCase();

                    if (confirm.equals("y")) {
                        System.out.println("Bye!!!");
                        opc = 4;
                    } else {
                        opc = 0;
                    }
                    break;

                default:
                    System.out.println("Invalid option, please try again");
            }

        } while(opc != 4);
    }
}