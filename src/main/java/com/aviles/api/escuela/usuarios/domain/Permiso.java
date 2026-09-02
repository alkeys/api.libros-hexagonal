package com.aviles.api.escuela.usuarios.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un permiso del sistema.
 */
public record Permiso(
    Id id,
    String nombre,
    String descripcion
) {
    public Permiso {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
    }

    public static Permiso nuevo(String nombre, String descripcion) {
        return new Permiso(null, nombre, descripcion);
    }
}
