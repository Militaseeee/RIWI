package controller;

import domain.Book;
import exception.DataAccessException;
import exception.ServiceException;
import service.interfaces.BookService;
import java.util.List;

public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public Book createBook(Book book) throws ServiceException, DataAccessException {
        return bookService.createBook(book);
    }

    public List<Book> findAllBooks() throws ServiceException, DataAccessException {
        return bookService.findAllBooks();
    }

    public Book findBookById(int id) throws ServiceException, DataAccessException {
        return bookService.findBookById(id);
    }

    public Book findBookByIsbn(String isbn) throws ServiceException, DataAccessException {
        return bookService.findBookByIsbn(isbn);
    }
}