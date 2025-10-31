package com.crudActivity.camila_acosta_mobileFix.service;

import com.crudActivity.camila_acosta_mobileFix.model.User;
import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User createUser(User user);
}