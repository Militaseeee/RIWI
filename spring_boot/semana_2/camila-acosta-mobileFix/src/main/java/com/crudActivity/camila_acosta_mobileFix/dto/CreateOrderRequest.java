package com.crudActivity.camila_acosta_mobileFix.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Este DTO valida que los datos de entrada para crear una orden sean correctos
// POST /api/orders
public record CreateOrderRequest(

        @NotNull(message = "The device ID cannot be null")
        Long deviceId,

        @NotNull(message = "The description cannot be null")
        @Size(min = 10, message = "The description must be at least 10 characters long")
        String issueDescription
) {}
