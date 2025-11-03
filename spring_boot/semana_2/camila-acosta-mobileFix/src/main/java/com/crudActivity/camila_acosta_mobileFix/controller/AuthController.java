package com.crudActivity.camila_acosta_mobileFix.controller;

import com.crudActivity.camila_acosta_mobileFix.dto.LoginRequest;
import com.crudActivity.camila_acosta_mobileFix.dto.LoginResponse;
import com.crudActivity.camila_acosta_mobileFix.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        // El servicio buscará al usuario y validará la contraseña
        LoginResponse response = userService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}