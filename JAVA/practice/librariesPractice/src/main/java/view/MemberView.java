package view;

import controller.LoanController;
import controller.MemberController;
import domain.Member;
import exception.ServiceException;
import javax.swing.JOptionPane;
import java.util.List;

public class MemberView {

    private final MemberController memberController;
    private final LoanController loanController;
    private final BookView bookView;

    public MemberView(MemberController memberController, LoanController loanController, BookView bookView) {
        this.memberController = memberController;
        this.loanController = loanController;
        this.bookView = bookView;
    }

    // --- PORTAL PRINCIPAL PARA EL SOCIO ---
    public void showMemberPortal() {
        String[] options = {"1. Login with Email", "2. Create New Account", "Back"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Welcome, Member! How would you like to proceed?",
                "Member Portal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        switch (choice) {
            case 0: // Login con Email
                Member loggedMember = identifyMember();
                if (loggedMember != null) {
                    showMemberMenu(loggedMember);
                }
                break;
            case 1: // Crear cuenta
                createMember();
                break;
            default: // Volver o cerrar
                break;
        }
    }

    // --- MENÚ Y ACCIONES DEL SOCIO (UNA VEZ LOGUEADO) ---
    public void showMemberMenu(Member loggedMember) {
        while (true) {
            String option = JOptionPane.showInputDialog(null,
                    "===== 👤 Member Panel (" + loggedMember.getFullName() + ") =====\n\n" +
                            "1. List All Books\n" +             // <-- NUEVA OPCIÓN
                            "2. Borrow a Book\n" +
                            "3. Update My Information\n" +
                            "4. Deactivate My Account\n" +
                            "5. Logout\n\n" +
                            "Choose an option:"
            );

            if (option == null) break;

            switch (option) {
                case "1":
                    // 🔑 CAMBIO CLAVE: Reutilizamos el método de BookView
                    bookView.listAllBooks();
                    break;
                case "2":
                    handleSelfLoan(loggedMember);
                    break;
                case "3":
                    updateSelfInfo(loggedMember);
                    break;
                case "4":
                    deactivateAccount(loggedMember);
                    if (!loggedMember.isActive()) return;
                    break;
                case "5":
                    return; // Logout
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void handleSelfLoan(Member loggedMember) {
        try {
            String bookIdStr = JOptionPane.showInputDialog(null, "Enter the ID of the book you want to borrow:");
            if (bookIdStr == null || bookIdStr.trim().isEmpty()) return;
            int bookId = Integer.parseInt(bookIdStr);

            // For a self-service loan, a default librarian ID is used to satisfy the system's requirements.
            int defaultLibrarianId = 1; // Assuming user 'admin' has ID 1.

            loanController.createLoan(bookId, loggedMember.getIdMember(), defaultLibrarianId);
            JOptionPane.showMessageDialog(null, "Book borrowed successfully! Please return it in 15 days.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Book ID. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Could not borrow book: " + e.getMessage(), "Loan Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateSelfInfo(Member member) {
        try {
            String newName = JOptionPane.showInputDialog(null, "Enter your new full name:", member.getFullName());
            String newPhone = JOptionPane.showInputDialog(null, "Enter your new phone number:", member.getPhone());

            if (newName != null && !newName.trim().isEmpty() && newPhone != null && !newPhone.trim().isEmpty()) {
                memberController.updateMember(member.getIdMember(), newName, member.getEmail(), newPhone, member.isActive());
                JOptionPane.showMessageDialog(null, "Information updated successfully!");
                // Update the local object to reflect the changes immediately in the UI
                member.setFullName(newName);
                member.setPhone(newPhone);
            }
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Could not update information: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deactivateAccount(Member member) {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to deactivate your account?\nYou will not be able to borrow books.",
                "Confirm Deactivation",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                memberController.updateMember(member.getIdMember(), member.getFullName(), member.getEmail(), member.getPhone(), false);
                JOptionPane.showMessageDialog(null, "Account deactivated successfully.");
                member.setActive(false);
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(null, "Could not deactivate account: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // --- MÉTODOS DE SOPORTE Y PARA EL ADMIN ---
    public Member identifyMember() {
        try {
            String email = JOptionPane.showInputDialog(null, "Please enter your registered email to continue:");
            if (email == null || email.trim().isEmpty()) {
                return null;
            }
            Member member = memberController.findMemberByEmail(email);
            JOptionPane.showMessageDialog(null, "Welcome, " + member.getFullName() + "!");
            return member;
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Could not find member: " + e.getMessage(), "Identification Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void createMember() {
        try {
            String fullName = JOptionPane.showInputDialog(null, "Enter your full name:");
            if (fullName == null || fullName.trim().isEmpty()) return;

            String email = JOptionPane.showInputDialog(null, "Enter your email address:");
            if (email == null || email.trim().isEmpty()) return;

            String phone = JOptionPane.showInputDialog(null, "Enter your phone number:");
            if (phone == null || phone.trim().isEmpty()) return;

            memberController.createMember(fullName, email, phone);
            JOptionPane.showMessageDialog(null, "Account created successfully! You can now log in with your email.");

        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Error creating account: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listAllMembers() {
        try {
            List<Member> members = memberController.findAllMembers();
            if (members.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No members found in the system.");
                return;
            }

            StringBuilder sb = new StringBuilder("--- All Members ---\n\n");
            for (Member member : members) {
                String status = member.isActive() ? "Active" : "Inactive";
                sb.append(String.format("ID: %d | Name: %s | Email: %s | Status: %s\n",
                        member.getIdMember(), member.getFullName(), member.getEmail(), status));
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Member List", JOptionPane.INFORMATION_MESSAGE);

        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving members: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}