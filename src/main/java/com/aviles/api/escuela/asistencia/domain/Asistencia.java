package com.aviles.api.escuela.asistencia.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa la asistencia de un estudiante a una clase.
 */
public record Asistencia(
    Id id,
    Id idEstudiante,
    Id idAsignacion,
    LocalDate fecha,
    String estado,
    String observacion,
    OffsetDateTime fechaRegistro
) {
    public Asistencia {
        if (idEstudiante == null) throw new IllegalArgumentException("El estudiante es obligatorio");
        if (idAsignacion == null) throw new IllegalArgumentException("La asignación es obligatoria");
        if (fecha == null) throw new IllegalArgumentException("La fecha es obligatoria");
        if (estado == null || estado.isBlank()) throw new IllegalArgumentException("El estado es obligatorio");
    }

    public static Asistencia nueva(Id idEstudiante, Id idAsignacion, LocalDate fecha, String estado) {
        return new Asistencia(null, idEstudiante, idAsignacion, fecha, estado, null, OffsetDateTime.now());
    }
}
