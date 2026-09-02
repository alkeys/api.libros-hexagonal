package com.aviles.api.escuela.profesores.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un departamento académico.
 */
public record Departamento(
    Id id,
    String nombre,
    String descripcion
) {
    public Departamento {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
    }

    public static Departamento nuevo(String nombre, String descripcion) {
        return new Departamento(null, nombre, descripcion);
    }
}
