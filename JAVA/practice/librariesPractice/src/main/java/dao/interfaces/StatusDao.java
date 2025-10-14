package dao.interfaces;

import domain.Status;
import exception.DataAccessException;

import java.util.List;
import java.util.Optional;

public interface StatusDao {

    Optional<Status> findById(int id) throws DataAccessException;
    List<Status> findAll() throws DataAccessException;

}
