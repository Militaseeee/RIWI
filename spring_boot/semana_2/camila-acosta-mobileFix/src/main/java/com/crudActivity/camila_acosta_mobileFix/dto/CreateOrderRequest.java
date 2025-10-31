package com.crudActivity.camila_acosta_mobileFix.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Este DTO valida que los datos de entrada para crear una orden sean correctos
// POST /api/orders
public record CreateOrderRequest(

        @NotNull(message = "El ID del dispositivo no puede ser nulo")
        Long deviceId,

        @NotNull(message = "La descripción no puede ser nula")
        @Size(min = 10, message = "La descripción debe tener al menos 10 caracteres")
        String issueDescription
) {}
