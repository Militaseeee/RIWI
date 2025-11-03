package com.crudActivity.camila_acosta_mobileFix.service;

import com.crudActivity.camila_acosta_mobileFix.dto.LoginRequest;
import com.crudActivity.camila_acosta_mobileFix.dto.LoginResponse;
import com.crudActivity.camila_acosta_mobileFix.model.User;
import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User createUser(User user);

    LoginResponse login(LoginRequest loginRequest);
}