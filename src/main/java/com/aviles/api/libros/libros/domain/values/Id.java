package com.aviles.api.libros.libros.domain.values;

import java.util.UUID;

import lombok.Value;

@Value
public class Id {
    private final String value;

    public Id(String value) {
        if (value != null && !value.isBlank()) {
            try {
                UUID.fromString(value);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("El id no tiene un formato válido de UUID");
            }
        }
        this.value = (value != null && value.isBlank()) ? null : value;
    }

    /** null si aún no tiene id (libro nuevo) */
    public UUID toUuidOrNull() {
        if (value == null || value.isBlank()) {
            return null;
        }
        return UUID.fromString(value);
    }
}
