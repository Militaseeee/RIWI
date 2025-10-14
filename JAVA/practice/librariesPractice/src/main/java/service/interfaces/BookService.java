package service.interfaces;

import domain.Book;
import exception.BadRequestException;
import exception.ConflictException;
import exception.DataAccessException;
import exception.NotFoundException;

import java.util.List;

public interface BookService {

    Book createBook(Book book) throws ConflictException, BadRequestException, DataAccessException;

    Book updateBook(Book book) throws NotFoundException, ConflictException, BadRequestException, DataAccessException;

    void deleteBook(Integer id) throws NotFoundException, DataAccessException;

    Book findBookById(Integer id) throws NotFoundException, DataAccessException;

    Book findBookByIsbn(String isbn) throws NotFoundException, DataAccessException;

    List<Book> findAllBooks() throws DataAccessException;

}
