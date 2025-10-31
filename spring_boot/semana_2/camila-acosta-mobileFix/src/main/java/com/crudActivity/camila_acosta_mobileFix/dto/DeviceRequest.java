package com.crudActivity.camila_acosta_mobileFix.dto;

import jakarta.validation.constraints.NotBlank;

// Para POST y PUT en /api/devices
public record DeviceRequest(

        @NotBlank(message = "La marca no puede estar vacía")
        String brand,

        @NotBlank(message = "El modelo no puede estar vacío")
        String model,

        String serialNumber // Opcional
) {}
