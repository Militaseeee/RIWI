package app;

import app.model.Administrator;
import app.model.Client;
import app.model.User;
import app.service.ServiceUser;
import javax.swing.JOptionPane;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        ServiceUser service = new ServiceUser();

        // Creamos un admin por defecto para poder probar
        service.createUser("Admin Root", "admin@root.com", "admin123", "admin");

        while (true) {
            String menuPrincipal = "--- MENÚ PRINCIPAL ---\n\n" +
                    "1. Crear nuevo usuario\n" +
                    "2. Iniciar sesión\n" +
                    "3. Salir\n\n" +
                    "Elige una opción:";

            String choiceStr = JOptionPane.showInputDialog(null, menuPrincipal);

            // Si el usuario presiona "Cancelar" o cierra la ventana
            if (choiceStr == null) {
                break;
            }

            try {
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        handleCreateUser(service);
                        break;
                    case 2:
                        handleLogin(service);
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                        return; // Termina el programa
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Inténtalo de nuevo.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido.");
            }
        }
    }

    private static void handleCreateUser(ServiceUser service) {
        String name = JOptionPane.showInputDialog(null, "Introduce tu nombre:");
        if (name == null) return;

        String email = JOptionPane.showInputDialog(null, "Introduce tu email:");
        if (email == null) return;

        String password = JOptionPane.showInputDialog(null, "Introduce tu contraseña (mínimo 6 caracteres):");
        if (password == null) return;

        String rol = JOptionPane.showInputDialog(null, "¿Qué rol quieres? (cliente/admin):");
        if (rol == null) return;

        try {
            service.createUser(name, email, password, rol);
            JOptionPane.showMessageDialog(null, "¡Usuario " + name + " creado con éxito!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error al crear usuario: " + e.getMessage());
        }
    }

    private static void handleLogin(ServiceUser service) {
        String email = JOptionPane.showInputDialog(null, "Email:");
        if (email == null) return;

        String password = JOptionPane.showInputDialog(null, "Contraseña:");
        if (password == null) return;

        Optional<User> loggedInUser = service.loginUser(email, password);

        if (loggedInUser.isPresent()) {
            User user = loggedInUser.get();
            JOptionPane.showMessageDialog(null, "¡Bienvenido, " + user.getName() + "!\n" + user.rolDescription());

            if (user instanceof Administrator) {
                showAdminMenu(service);
            } else if (user instanceof Client) {
                showClientMenu((Client) user, service);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas o usuario no existe.");
        }
    }

    private static void showAdminMenu(ServiceUser service) {
        while (true) {
            String menuAdmin = "--- MENÚ DE ADMINISTRADOR ---\n\n" +
                    "1. Ver todos los usuarios\n" +
                    "2. Bloquear usuario\n" +
                    "3. Cerrar sesión\n\n" +
                    "Elige una opción:";
            String choiceStr = JOptionPane.showInputDialog(null, menuAdmin);
            if (choiceStr == null) break;

            try {
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        String userList = service.listAllUsers();
                        JOptionPane.showMessageDialog(null, userList, "Listado de Usuarios", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 2:
                        String emailToBlock = JOptionPane.showInputDialog(null, "Introduce el email del usuario a bloquear:");
                        if (emailToBlock != null) {
                            String message = service.blockUser(emailToBlock);
                            JOptionPane.showMessageDialog(null, message);
                        }
                        break;
                    case 3:
                        return; // Vuelve al menú principal
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido.");
            }
        }
    }

    private static void showClientMenu(Client client, ServiceUser service) {
        while (true) {
            String menuCliente = "--- MENÚ DE CLIENTE ---\n\n" +
                    "1. Ver mi perfil\n" +
                    "2. Actualizar mi información de contacto\n" +
                    "3. Cerrar sesión\n\n" +
                    "Elige una opción:";
            String choiceStr = JOptionPane.showInputDialog(null, menuCliente);
            if (choiceStr == null) break;

            try {
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        String profile = "Nombre: " + client.getName() + "\n" +
                                "Email: " + client.getEmail() + "\n" +
                                "Teléfono: " + (client.getTelephone() != null ? client.getTelephone() : "No especificado") + "\n" +
                                "Dirección: " + (client.getAddress() != null ? client.getAddress() : "No especificada");
                        JOptionPane.showMessageDialog(null, profile, "Mi Perfil", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 2:
                        String newPhone = JOptionPane.showInputDialog(null, "Introduce tu nuevo teléfono:", client.getTelephone());
                        if (newPhone == null) break;

                        String newAddress = JOptionPane.showInputDialog(null, "Introduce tu nueva dirección:", client.getAddress());
                        if (newAddress == null) break;

                        String message = service.updateClient(client, newPhone, newAddress);
                        JOptionPane.showMessageDialog(null, message);
                        break;
                    case 3:
                        return; // Vuelve al menú principal
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido.");
            }
        }
    }
}