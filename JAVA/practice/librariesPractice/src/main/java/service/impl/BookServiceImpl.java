package service.impl;

import dao.interfaces.BookDao;
import domain.Book;
import exception.BadRequestException;
import exception.ConflictException;
import exception.DataAccessException;
import exception.NotFoundException;
import service.interfaces.BookService;
import util.ValidateInput;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    // We inject the dependency through the constructor
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book createBook(Book book) throws ConflictException, BadRequestException, DataAccessException {
        ValidateInput.validateString(book.getTitle());
        ValidateInput.validateString(book.getAuthor());
        ValidateInput.validateString(book.getIsbn());
        ValidateInput.validateNumber(book.getStock());
        // ISBN must be unique
        if (bookDao.findByIsbn(book.getIsbn()).isPresent()) {
            throw new ConflictException("A book with ISBN " + book.getIsbn() + " already exists.");
        }
        // Call the DAO to create the ledger
        return bookDao.create(book);
    }

    @Override
    public Book updateBook(Book book) throws NotFoundException, ConflictException, BadRequestException, DataAccessException {
        // Validate inputs
        ValidateInput.validateString(book.getTitle());
        ValidateInput.validateString(book.getAuthor());
        ValidateInput.validateString(book.getIsbn());
        ValidateInput.validateNumber(book.getStock());

        // Make sure the book to be updated exists
        Book existingBook = bookDao.findById(book.getIdBook())
                .orElseThrow(() -> new NotFoundException("Book with ID " + book.getIdBook() + " not found."));

        // If the ISBN has changed, verify that the new one does not exist in another book
        if (!existingBook.getIsbn().equals(book.getIsbn())) {
            Optional<Book> bookWithNewIsbn = bookDao.findByIsbn(book.getIsbn());
            if (bookWithNewIsbn.isPresent()) {
                throw new ConflictException("The new ISBN " + book.getIsbn() + " already belongs to another book.");
            }
        }
        bookDao.update(book);
        return book;
    }

    @Override
    public void deleteBook(Integer id) throws NotFoundException, DataAccessException {
        findBookById(id);
        bookDao.delete(id);
    }

    @Override
    public Book findBookById(Integer id) throws NotFoundException, DataAccessException {
        return bookDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with ID " + id + " not found"));
    }

    @Override
    public Book findBookByIsbn(String isbn) throws NotFoundException, DataAccessException {
        return bookDao.findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException("Book with ISBN " + isbn + " not found"));
    }

    @Override
    public List<Book> findAllBooks() throws DataAccessException {
        return bookDao.findAll();
    }
}
