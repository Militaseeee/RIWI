package view;

import controller.LoanController;
import domain.Loan;
import exception.ServiceException;

import javax.swing.JOptionPane;
import java.util.List;

import java.io.FileWriter; // <-- AÑADIR IMPORT
import java.io.IOException;  // <-- AÑADIR IMPORT

public class LoanView {

    private final LoanController loanController;

    public LoanView(LoanController loanController) {
        this.loanController = loanController;
    }

    public void createLoan(int idUser) {
        try {
            int bookId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Book ID:"));
            int memberId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Member ID:"));
            int userId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter User ID (librarian):"));

            loanController.createLoan(bookId, memberId, userId);
            JOptionPane.showMessageDialog(null, "Loan created successfully!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Business Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void returnLoan() {
        try {
            int loanId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Loan ID to return:"));
            loanController.returnLoan(loanId);
            JOptionPane.showMessageDialog(null, "Loan returned successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listAllLoans() {
        try {
            List<Loan> loans = loanController.findAllLoans();
            if (loans.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No loans found.");
                return;
            }

            StringBuilder sb = new StringBuilder("--- All Loans ---\n");
            for (Loan loan : loans) {
                sb.append(String.format("ID: %d | Book: %s | Member: %s | Status: %s\n",
                        loan.getIdLoan(), loan.getBook().getTitle(), loan.getMember().getFullName(), loan.getStatus().getName()));
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exportOverdueLoans() {
        try {
            // 1. Obtener la lista de préstamos vencidos
            List<Loan> overdueLoans = loanController.findOverdueLoans();
            if (overdueLoans.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No overdue loans to export.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String filename = "overdue_loans.csv";
            // 2. Usar try-with-resources para manejar el archivo
            try (FileWriter writer = new FileWriter(filename)) {
                // 3. Escribir la cabecera del archivo
                writer.append("Loan ID,Book Title,Member Name,Loan Date,Return Date Due\n");

                // 4. Escribir los datos de cada préstamo vencido
                for (Loan loan : overdueLoans) {
                    writer.append(String.valueOf(loan.getIdLoan())).append(",");
                    writer.append(loan.getBook().getTitle()).append(",");
                    writer.append(loan.getMember().getFullName()).append(",");
                    writer.append(loan.getLoanDate().toString()).append(",");
                    writer.append(loan.getReturnDate().toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, "Overdue loans report exported successfully!\nFile saved as: " + filename);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Error fetching loan data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}