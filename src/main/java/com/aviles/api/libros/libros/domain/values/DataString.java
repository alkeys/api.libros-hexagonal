package com.aviles.api.libros.libros.domain.values;

import lombok.Value;

@Value
public class DataString {
    private final String value;
    public DataString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El valor no puede ser nulo o vacío");
        }
        this.value = value;
    }    

}
