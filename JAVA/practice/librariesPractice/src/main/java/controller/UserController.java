package controller;

import domain.User;
import exception.ServiceException;
import service.interfaces.UserService;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User login(String username, String password) throws ServiceException {
        return userService.login(username, password);
    }

    public User findUserById(int id) throws ServiceException {
        return userService.findUserById(id);
    }
}