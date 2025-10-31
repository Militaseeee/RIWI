package com.crudActivity.camila_acosta_mobileFix.controller;

import com.crudActivity.camila_acosta_mobileFix.dto.UserRequest;
import com.crudActivity.camila_acosta_mobileFix.model.User;
import com.crudActivity.camila_acosta_mobileFix.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


// Este controlador es solo para que el ADMIN gestione otros usuarios
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint: GET /api/users
     * Permisos: ADMIN
     * Lista todos los usuarios del sistema.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        // Ocultamos el password antes de enviarlos
        users.forEach(user -> user.setPassword(null));
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint: POST /api/users
     * Permisos: ADMIN
     * Crea un nuevo usuario (USER, TECH, o ADMIN).
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(request.password()); // El servicio lo hasheará
        user.setRole(request.role());
        user.setFullName(request.fullName());
        user.setEmail(request.email());

        User newUser = userService.createUser(user);

        // Ocultamos el password en la respuesta
        newUser.setPassword(null);

        URI location = URI.create("/api/users/" + newUser.getId());
        return ResponseEntity.created(location).body(newUser);
    }

    // NOTA: El taller no pide PUT ni DELETE para usuarios,
    // pero se añadirían aquí si fuera necesario.
}