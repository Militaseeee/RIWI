package com.crudActivity.camila_acosta_mobileFix.dto;

import com.crudActivity.camila_acosta_mobileFix.model.Role;

// Esto es lo que el servidor responde: { "id": 1, "role": "ADMIN", ... }
public record LoginResponse(
        Long id,
        Role role,
        String fullName
) {}