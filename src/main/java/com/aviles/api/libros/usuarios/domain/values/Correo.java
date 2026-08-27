package com.aviles.api.libros.usuarios.domain.values;


import lombok.Value;

@Value
public class Correo {
    private final String correo;
    public Correo(String correo) {
        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }
        this.correo = correo;
    }
    
}
