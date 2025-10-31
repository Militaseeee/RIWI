package com.crudActivity.camila_acosta_mobileFix.service.impl;

import com.crudActivity.camila_acosta_mobileFix.exception.ConflictException;
import com.crudActivity.camila_acosta_mobileFix.model.User;
import com.crudActivity.camila_acosta_mobileFix.repository.UserRepository;
import com.crudActivity.camila_acosta_mobileFix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyecto el de SecurityConfig

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

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hashear el password

        return userRepository.save(user);
    }
}