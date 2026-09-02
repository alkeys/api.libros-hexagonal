package com.aviles.api.escuela.estudiantes.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa la matrícula de un estudiante en un grupo.
 */
public record Matricula(
    Id id,
    Id idEstudiante,
    Id idGrupo,
    LocalDate fechaMatricula,
    String tipoMatricula,
    String estado,
    String observaciones,
    OffsetDateTime fechaCreacion
) {
    public Matricula {
        if (idEstudiante == null) throw new IllegalArgumentException("El estudiante es obligatorio");
        if (idGrupo == null) throw new IllegalArgumentException("El grupo es obligatorio");
    }

    public static Matricula nueva(Id idEstudiante, Id idGrupo, String tipoMatricula) {
        return new Matricula(null, idEstudiante, idGrupo, LocalDate.now(), tipoMatricula, "PENDIENTE", null, OffsetDateTime.now());
    }
}
