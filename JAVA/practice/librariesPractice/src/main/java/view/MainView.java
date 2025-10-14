package view;

import domain.Member;
import domain.User;
import javax.swing.JOptionPane;

public class MainView {

    private final UserView userView;
    private final AdminMenuView adminMenuView;
    private final MemberView memberView;

    public MainView(UserView userView, AdminMenuView adminMenuView, MemberView memberView) {
        this.userView = userView;
        this.adminMenuView = adminMenuView;
        this.memberView = memberView;
    }

    public void start() {
        while (true) {
            String[] options = {"Login as Librarian (User)", "Login as Member", "Exit"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Welcome to LibroNova! How would you like to proceed?",
                    "LibroNova - Main Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0: // Librarian Login
                    User loggedUser = userView.login();
                    if (loggedUser != null) {
                        // Si el login es exitoso, le pasamos el usuario logueado al menú de admin
                        adminMenuView.showMenu(loggedUser);
                    }
                    break;
                case 1: // Member Login
                    memberView.showMemberPortal();
                    break;
                case 2: // Exit
                case -1: // User closed the dialog
                    JOptionPane.showMessageDialog(null, "Thank you for using LibroNova!");
                    return;
            }
        }
    }
}