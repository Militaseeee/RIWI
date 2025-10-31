package com.crudActivity.camila_acosta_mobileFix.dto;

import com.crudActivity.camila_acosta_mobileFix.model.Status;
import jakarta.validation.constraints.NotNull;

// PUT /api/orders/{id}/status
public record ChangeStatusRequest(

        @NotNull(message = "The state cannot be null")
        Status status,

        String techNotes // Las notas son opcionales
) {} // -> eso es un cuerpo de record