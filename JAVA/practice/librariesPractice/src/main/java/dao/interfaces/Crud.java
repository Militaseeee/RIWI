package dao.interfaces;

import exception.DataAccessException;

import java.util.List;
import java.util.Optional;

public interface Crud<T, ID> {
    T create(T entity) throws DataAccessException;
    Optional<T> findById(ID id) throws DataAccessException;
    List<T> findAll() throws DataAccessException;
    void update(T entity) throws DataAccessException;
    void delete(ID id) throws DataAccessException;
}
