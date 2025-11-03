package com.crudActivity.camila_acosta_mobileFix.service.impl;

import com.crudActivity.camila_acosta_mobileFix.dto.LoginRequest;
import com.crudActivity.camila_acosta_mobileFix.dto.LoginResponse;
import com.crudActivity.camila_acosta_mobileFix.exception.ConflictException;
import com.crudActivity.camila_acosta_mobileFix.exception.ForbiddenAccessException;
import com.crudActivity.camila_acosta_mobileFix.model.User;
import com.crudActivity.camila_acosta_mobileFix.repository.UserRepository;
import com.crudActivity.camila_acosta_mobileFix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        // Username unico
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ConflictException("The username already exists: " + user.getUsername());
        }

        return userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        // 1. Buscar al usuario por su username
        User user = userRepository.findByUsername(loginRequest.username())
                // Si no lo encuentra, lanza error 403 (Prohibido)
                .orElseThrow(() -> new ForbiddenAccessException("Credenciales inválidas"));

        // 2. Verificar la contraseña (esto funciona porque tu pass es 'pass' en texto plano)
        if (!user.getPassword().equals(loginRequest.password())) {
            throw new ForbiddenAccessException("Credenciales inválidas");
        }

        // 3. Si todo está bien, devuelve el ID, Rol y Nombre
        return new LoginResponse(user.getId(), user.getRole(), user.getFullName());
    }
}