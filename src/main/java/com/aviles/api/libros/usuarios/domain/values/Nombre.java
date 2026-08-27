package com.aviles.api.libros.usuarios.domain.values;

import lombok.Value;

@Value
public class Nombre {
    private final String nombre;

    public Nombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }
    
}
