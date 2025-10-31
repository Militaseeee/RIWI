package com.crudActivity.camila_acosta_mobileFix.dto;

import jakarta.validation.constraints.NotBlank;

// Para POST y PUT en /api/devices
public record DeviceRequest(

        @NotBlank(message = "The brand cannot be empty.")
        String brand,

        @NotBlank(message = "The model cannot be empty")
        String model,

        String serialNumber // Opcional
) {}
