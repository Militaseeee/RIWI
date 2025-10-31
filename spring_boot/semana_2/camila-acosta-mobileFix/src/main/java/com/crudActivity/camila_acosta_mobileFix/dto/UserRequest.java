package com.crudActivity.camila_acosta_mobileFix.dto;

import com.crudActivity.camila_acosta_mobileFix.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// POST /api/users
public record UserRequest(

        @NotBlank
        @Size(min = 3, message = "El username debe tener al menos 3 caracteres")
        String username,

        @NotBlank
        @Size(min = 6, message = "El password debe tener al menos 6 caracteres")
        String password,

        @NotNull(message = "El rol no puede ser nulo")
        Role role,

        String fullName,

        @Email(message = "Debe ser un email válido")
        String email
) {}
