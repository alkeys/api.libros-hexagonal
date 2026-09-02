package com.aviles.api.escuela.niveles.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un grado dentro de un nivel educativo.
 */
public record Grado(
    Id id,
    Id idNivel,
    String nombreGrado,
    String descripcion
) {
    public Grado {
        if (idNivel == null) throw new IllegalArgumentException("El nivel educativo es obligatorio");
        if (nombreGrado == null || nombreGrado.isBlank()) throw new IllegalArgumentException("El nombre del grado es obligatorio");
    }

    public static Grado nuevo(Id idNivel, String nombreGrado, String descripcion) {
        return new Grado(null, idNivel, nombreGrado, descripcion);
    }
}
