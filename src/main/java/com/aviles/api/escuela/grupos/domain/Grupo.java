package com.aviles.api.escuela.grupos.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un grupo escolar.
 * Un grupo combina un grado, una sección y un año escolar.
 */
public record Grupo(
    Id id,
    Id idGrado,
    Id idSeccion,
    Id idAnioEscolar,
    String nombre,
    Integer capacidad,
    String turno,
    String estado
) {
    public Grupo {
        if (idGrado == null) throw new IllegalArgumentException("El grado es obligatorio");
        if (idSeccion == null) throw new IllegalArgumentException("La sección es obligatoria");
        if (idAnioEscolar == null) throw new IllegalArgumentException("El año escolar es obligatorio");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
        if (capacidad == null || capacidad <= 0) throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
    }

    public static Grupo nuevo(Id idGrado, Id idSeccion, Id idAnioEscolar, String nombre, Integer capacidad, String turno) {
        return new Grupo(null, idGrado, idSeccion, idAnioEscolar, nombre, capacidad, turno, "ACTIVA");
    }
}
