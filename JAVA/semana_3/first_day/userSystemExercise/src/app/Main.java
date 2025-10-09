package app;

import app.model.Administrator;
import app.model.Client;
import app.model.User;
import app.service.ServiceUser;
import app.util.Validation;

import javax.swing.*;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        ServiceUser service = new ServiceUser();

        // Creamos un admin por defecto para poder probar
        service.createUser("Admin Root", "admin@root.com", "admin123", "admin");

        while (true) {
            String menuPrincipal =
                    " -----------------------------------------------------\n" +
                    "                      MAIN MENU \n" +
                    " -----------------------------------------------------\n" +
                    "1. Create new user\n" +
                    "2. Login\n" +
                    "3. Go out\n\n" +
                    "Choose an option:";

            String choiceStr = JOptionPane.showInputDialog(null, menuPrincipal);

            // Si el usuario presiona "Cancelar" o cierra la ventana
            if (choiceStr == null) {
                break;
            }

            try {
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        handleCreateUser(service); // Handle -> manejo
                        break;
                    case 2:
                        handleLogin(service);
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Bye!!!! See you later!");
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option. Please try again");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number");
            }
        }
    }

    private static void handleCreateUser(ServiceUser service) {
//        String name = JOptionPane.showInputDialog(null, "Type your name:");
//        name = Validation.validSearchInput(name);
//        if (name == null) return;

        String name;
        do {
            name = JOptionPane.showInputDialog(null, "Type your name:");
            name = Validation.validSearchInput(name);
            if (name == null) return;
        } while (name.isEmpty());

        String email;
        do {
            email = JOptionPane.showInputDialog(null, "Type your email:");
            if (email == null) return;
            email = Validation.validSearchInput(email);
        } while (email == null || !Validation.checkEmail(email));

//        email = Validation.validSearchInput(email);
//        Validation.checkEmail(email);
//        if (email == null) return;

        String password;
        do {
            password = JOptionPane.showInputDialog(null, "Type your password (minimum 6 characters):");
            if (password == null) return;
            password = Validation.validSearchInput(password);
        } while (password == null || !Validation.checkPassword(password));

//        String password = JOptionPane.showInputDialog(null, "Type your password (minimum 6 characters):");
//        password = Validation.validSearchInput(password);
//        if (password == null) return;


        String[] typeOption = {"client", "admin"};
        int rolType = JOptionPane.showOptionDialog(null, "What role do you want?", "Select Type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, typeOption, typeOption[0]);

        // If person cancel the process
        if (rolType== -1) {
            JOptionPane.showMessageDialog(null, "Operation Cancelled");
            return;
        }

        // Esa línea convierte la opción seleccionada (un número) en el valor de rol en String para usarlo más adelante en tu código
        String rol = typeOption[rolType]; // Eso transforma el número en el texto correspondiente

//        String rol = JOptionPane.showInputDialog(null, "What role do you want? (client/admin):");
//        if (rol == null) return;
//        rol = Validation.validSearchInput(rol);

        try {
            service.createUser(name, email, password, rol);
            JOptionPane.showMessageDialog(null, "User " + name + " successfully created!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error creating user: " + e.getMessage());
        }
    }

    private static void handleLogin(ServiceUser service) {
        String email = JOptionPane.showInputDialog(null, "Email:");
        if (email == null) return;

        String password = JOptionPane.showInputDialog(null, "Password:");
        if (password == null) return;

        Optional<User> loggedInUser = service.loginUser(email, password);

        // isPresent() es un metodo de la clase Optional<T> en Java -> Sirve para saber si dentro del Optional hay un valor o está vacío
        if (loggedInUser.isPresent()) {
            User user = loggedInUser.get();
            JOptionPane.showMessageDialog(null, "¡Welcome, " + user.getName() + "!\n" + user.rolDescription());

            // -> instanceof funciona para verificar si un objeto es una instancia de una clase o interfaz específica
            // Verifica si el objeto "user" es de tipo Administrator
            if (user instanceof Administrator) {
                showAdminMenu(service);
            } else if (user instanceof Client) {
                showClientMenu((Client) user, service);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect credentials or user does not exist");
        }
    }

    private static void showAdminMenu(ServiceUser service) {
        while (true) {
            String menuAdmin = "--- ADMINISTRATOR MENU ---\n\n" +
                    "1. See all users\n" +
                    "2. Block user\n" +
                    "3. Sign out\n\n" +
                    "Choose an option:";
            String choiceStr = JOptionPane.showInputDialog(null, menuAdmin);
            if (choiceStr == null) break;

            try {
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        String userList = service.listAllUsers();

                        JTextArea textArea = new JTextArea(userList.toString());
                        textArea.setEditable(false);

                        // Esto hace que las columnas se alineen perfectamente.
                        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));

                        JScrollPane scrollPane = new JScrollPane(textArea); // Adds a scrollbar if the text is too long
                        JOptionPane.showMessageDialog(null, scrollPane, "List of Users", JOptionPane.PLAIN_MESSAGE);
                        break;

                        // String userList = service.listAllUsers();
                        // JOptionPane.showMessageDialog(null, userList, "List of Users", JOptionPane.INFORMATION_MESSAGE);
                        //  break;
                    case 2:
                        String emailToBlock = JOptionPane.showInputDialog(null, "Enter the email of the user to block:");
                        if (emailToBlock != null) {
                            String message = service.blockUser(emailToBlock);
                            JOptionPane.showMessageDialog(null, message);
                        }
                        break;
                    case 3:
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number");
            }
        }
    }

    private static void showClientMenu(Client client, ServiceUser service) {
        while (true) {
            String menuCliente = "--- CUSTOMER MENU ---\n\n" +
                    "1. View my profile\n" +
                    "2. Update my contact information\n" +
                    "3. Log out\n\n" +
                    "Choose an option:";
            String choiceStr = JOptionPane.showInputDialog(null, menuCliente);
            if (choiceStr == null) break;

            try {
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        String profile = "Name: " + client.getName() + "\n" +
                                "Email: " + client.getEmail() + "\n" +
                                "Telephone: " + (client.getTelephone() != null ? client.getTelephone() : "Not specified") + "\n" +
                                "Address: " + (client.getAddress() != null ? client.getAddress() : "Not specified");
                        JOptionPane.showMessageDialog(null, profile, "My profile", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 2:
                        String newPhone = JOptionPane.showInputDialog(null, "Enter your new phone number:", client.getTelephone());
                        if (newPhone == null) break;

                        String newAddress = JOptionPane.showInputDialog(null, "Enter your new address:", client.getAddress());
                        if (newAddress == null) break;

                        String message = service.updateClient(client, newPhone, newAddress);
                        JOptionPane.showMessageDialog(null, message);
                        break;
                    case 3:
                        return; // Vuelve al menú principal
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number");
            }
        }
    }
}