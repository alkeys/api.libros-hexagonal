package com.aviles.api.escuela.usuarios.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un rol del sistema (ADMIN, PROFESOR, ESTUDIANTE, etc.).
 */
public record Rol(
    Id id,
    String nombre,
    String descripcion
) {
    public Rol {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
    }

    public static Rol nuevo(String nombre, String descripcion) {
        return new Rol(null, nombre, descripcion);
    }
}
