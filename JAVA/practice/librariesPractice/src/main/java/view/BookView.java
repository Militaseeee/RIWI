package view;

import controller.BookController;
import domain.Book;
import exception.DataAccessException;
import exception.ServiceException;

import javax.swing.JOptionPane;
import java.util.List;

import java.io.FileWriter; // <-- AÑADIR IMPORT
import java.io.IOException;  // <-- AÑADIR IMPORT

public class BookView {

    private final BookController bookController;

    public BookView(BookController bookController) {
        this.bookController = bookController;
    }

    public void createBook() {
        try {
            String title = JOptionPane.showInputDialog(null, "Enter title:");
            String author = JOptionPane.showInputDialog(null, "Enter author:");
            String isbn = JOptionPane.showInputDialog(null, "Enter ISBN:");
            int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter stock:"));

            Book newBook = new Book();
            newBook.setTitle(title);
            newBook.setAuthor(author);
            newBook.setIsbn(isbn);
            newBook.setStock(stock);
            newBook.setStatus(true);

            Book createdBook = bookController.createBook(newBook);
            JOptionPane.showMessageDialog(null, "Book created successfully with ID: " + createdBook.getIdBook());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid stock. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Business Error", JOptionPane.ERROR_MESSAGE);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void listAllBooks() {
        try {
            List<Book> books = bookController.findAllBooks();
            if (books.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No books found.");
                return;
            }

            StringBuilder sb = new StringBuilder("--- All Books ---\n");
            for (Book book : books) {
                sb.append(String.format("ID: %d, Title: %s, Author: %s, Stock: %d\n",
                        book.getIdBook(), book.getTitle(), book.getAuthor(), book.getStock()));
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportBookCatalog() {
        try {
            // 1. Obtener la lista de todos los libros
            List<Book> books = bookController.findAllBooks();
            if (books.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No books to export.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String filename = "book_catalog.csv";
            // 2. Usar try-with-resources para manejar el archivo
            try (FileWriter writer = new FileWriter(filename)) {
                // 3. Escribir la cabecera del archivo
                writer.append("ID,Title,Author,ISBN,Stock,Status\n");

                // 4. Escribir los datos de cada libro en una nueva línea
                for (Book book : books) {
                    writer.append(String.valueOf(book.getIdBook())).append(",");
                    writer.append(book.getTitle()).append(",");
                    writer.append(book.getAuthor()).append(",");
                    writer.append(book.getIsbn()).append(",");
                    writer.append(String.valueOf(book.getStock())).append(",");
                    writer.append(String.valueOf(book.isStatus())).append("\n");
                }
                JOptionPane.showMessageDialog(null, "Book catalog exported successfully!\nFile saved as: " + filename);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ServiceException | DataAccessException e) {
            JOptionPane.showMessageDialog(null, "Error fetching book data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}