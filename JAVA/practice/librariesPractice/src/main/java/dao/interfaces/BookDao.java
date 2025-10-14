package dao.interfaces;

import domain.Book;
import exception.DataAccessException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface BookDao extends Crud<Book, Integer> {

    List<Book> findAll() throws DataAccessException;

    Optional<Book> findByIsbn(String isbn) throws DataAccessException;

    void update(Book book, Connection connection) throws DataAccessException;
}
