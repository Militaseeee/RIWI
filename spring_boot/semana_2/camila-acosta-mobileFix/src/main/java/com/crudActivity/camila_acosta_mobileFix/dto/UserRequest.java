package com.crudActivity.camila_acosta_mobileFix.dto;

import com.crudActivity.camila_acosta_mobileFix.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// POST /api/users
public record UserRequest(

        @NotBlank
        @Size(min = 3, message = "The username must be at least 3 characters long")
        String username,

        @NotBlank
        @Size(min = 6, message = "The password must be at least 6 characters long")
        String password,

        @NotNull(message = "The role cannot be null")
        Role role,

        String fullName,

        @Email(message = "It must be a valid email address")
        String email
) {}
