package service.impl;

import dao.interfaces.UserDao;
import domain.User;
import exception.DataAccessException;
import exception.NotFoundException;
import exception.ServiceException;
import exception.UnauthorizedException;
import service.interfaces.UserService;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String username, String password) throws ServiceException {
        try {

            User user = userDao.findByNameUser(username)
                    .orElseThrow(() -> new UnauthorizedException("Invalid username or password"));

            if (!user.getPassword().equals(password)) {
                throw new UnauthorizedException("Invalid username or password");
            }

            if (!user.isStatus()) {
                throw new UnauthorizedException("User account is disabled");
            }

            return user;
        } catch (DataAccessException | UnauthorizedException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User findUserById(int id) throws ServiceException {
        try {
            return userDao.findById(id)
                    .orElseThrow(() -> new NotFoundException("User with ID " + id + " not found"));
        } catch (DataAccessException | NotFoundException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
