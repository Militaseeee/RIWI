package dao.interfaces;

import domain.Role;
import exception.DataAccessException;

import java.util.List;
import java.util.Optional;

public interface RoleDao {

    Optional<Role> findById(int id) throws DataAccessException;
    List<Role> findAll() throws DataAccessException;
}
