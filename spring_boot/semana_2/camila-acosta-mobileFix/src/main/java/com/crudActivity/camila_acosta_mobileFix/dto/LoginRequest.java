package com.crudActivity.camila_acosta_mobileFix.dto;

import jakarta.validation.constraints.NotBlank;

// Esto es lo que el usuario envía: { "username": "...", "password": "..." }
public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
) {}