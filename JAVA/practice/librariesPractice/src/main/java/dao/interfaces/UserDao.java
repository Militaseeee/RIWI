package dao.interfaces;

import domain.User;
import exception.DataAccessException;

import java.util.Optional;

public interface UserDao extends Crud<User, Integer>{
    Optional<User> findByNameUser(String name) throws DataAccessException;
}
