package com.aviles.api.escuela.niveles.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un nivel educativo (Inicial, Básica, Media).
 */
public record NivelEducativo(
    Id id,
    String nombre,
    String descripcion
) {
    public NivelEducativo {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
    }

    public static NivelEducativo nuevo(String nombre, String descripcion) {
        return new NivelEducativo(null, nombre, descripcion);
    }
}
