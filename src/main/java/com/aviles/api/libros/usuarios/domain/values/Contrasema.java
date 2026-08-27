package com.aviles.api.libros.usuarios.domain.values;


import lombok.Value;

@Value
public class Contrasema {
    private final String contrasena;

    public Contrasema(String contrasena) {
        if (contrasena == null || contrasena.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        this.contrasena = contrasena;
    }
    
}
