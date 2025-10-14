package service.interfaces;

import domain.User;
import exception.ServiceException;

public interface UserService {

    User login(String username, String password) throws ServiceException;

    User findUserById(int id) throws ServiceException;

}
