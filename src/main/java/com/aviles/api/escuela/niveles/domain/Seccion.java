package com.aviles.api.escuela.niveles.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa una sección (A, B, C, etc.).
 */
public record Seccion(
    Id id,
    String nombre,
    String descripcion
) {
    public Seccion {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
    }

    public static Seccion nueva(String nombre, String descripcion) {
        return new Seccion(null, nombre, descripcion);
    }
}
