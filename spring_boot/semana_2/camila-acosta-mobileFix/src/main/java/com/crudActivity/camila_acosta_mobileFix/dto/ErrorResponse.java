package com.crudActivity.camila_acosta_mobileFix.dto;

// Esto sirve para enviar los mensajes de error en formato JSON
import java.util.Map;

// Map.of() crea un mapa inmutable
// Este constructor es para errores de validación -> 400
public record ErrorResponse(String error, Map<String, String> fields) {

    // Este constructor es para errores simples (404, 403, 409)
    public ErrorResponse(String error) {
        this(error, null);
    }
}