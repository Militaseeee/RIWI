package view;

import domain.User;
import javax.swing.JOptionPane;

public class AdminMenuView {

    // Necesita las otras vistas para delegar las tareas
    private final BookView bookView;
    private final LoanView loanView;
    private final MemberView memberView;

    public AdminMenuView(BookView bookView, LoanView loanView, MemberView memberView) {
        this.bookView = bookView;
        this.loanView = loanView;
        this.memberView = memberView;
    }

    public void showMenu(User loggedUser) {
        while (true) {
            String option = JOptionPane.showInputDialog(null,
                    "===== 📖 Admin Panel (" + loggedUser.getUsername() + ") =====\n\n" +
                            "1. Create New Book\n" +
                            "2. List All Books\n" +
                            "3. Create New Loan\n" +
                            "4. Return a Loan\n" +
                            "5. List All Loans\n" +
                            "6. Create New Member\n" +
                            "7. List All Members\n\n" +
                            "--- Reports ---\n" +
                            "8. Export Book Catalog (CSV)\n" +      // <-- NUEVA OPCIÓN
                            "9. Export Overdue Loans (CSV)\n" +     // <-- NUEVA OPCIÓN
                            "10. Logout\n\n" +                      // <-- ACTUALIZADO
                            "Choose an option:"
            );

            if (option == null) {
                break; // El usuario cerró la ventana
            }

            switch (option) {
                case "1": bookView.createBook(); break;
                case "2": bookView.listAllBooks(); break;
                // Le pasamos el ID del usuario para registrar quién hace el préstamo
                case "3": loanView.createLoan(loggedUser.getIdUser()); break;
                case "4": loanView.returnLoan(); break;
                case "5": loanView.listAllLoans(); break;
                case "6": memberView.createMember(); break;
                case "7": memberView.listAllMembers(); break;
                case "8": bookView.exportBookCatalog(); // Llamamos al método en BookView
                    break;
                case "9": loanView.exportOverdueLoans(); // Llamamos al método en LoanView
                    break;
                case "10":
                    return; // Sale de este menú y vuelve al principal
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}