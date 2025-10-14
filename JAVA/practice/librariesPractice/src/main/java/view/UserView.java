package view;

import controller.UserController;
import domain.User;
import exception.ServiceException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class UserView {

    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public User login() {
        try {
            String username = JOptionPane.showInputDialog(null, "Username:");
            if (username == null || username.trim().isEmpty()) return null;

            JPasswordField passwordField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(null, passwordField, "Password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                String password = new String(passwordField.getPassword());
                User loggedUser = userController.login(username, password);
                JOptionPane.showMessageDialog(null, "Welcome, " + loggedUser.getUsername() + "!");
                return loggedUser;
            }
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Login failed: " + e.getMessage(), "Authentication Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}