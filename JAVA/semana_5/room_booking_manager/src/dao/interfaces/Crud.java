package dao.interfaces;

import exception.DataAccessException;

import java.util.List;
import java.util.Optional;

public interface Crud <T> {
    void create(T object) throws DataAccessException;
    List<T> findAll() throws DataAccessException;
    void update(T object) throws DataAccessException;
    void delete(int id) throws DataAccessException;
    Optional<T> findById(int id) throws DataAccessException;
}
