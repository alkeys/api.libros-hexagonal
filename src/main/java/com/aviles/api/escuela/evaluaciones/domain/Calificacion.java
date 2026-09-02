package com.aviles.api.escuela.evaluaciones.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa la calificación de un estudiante en una evaluación.
 */
public record Calificacion(
    Id id,
    Id idEvaluacion,
    Id idEstudiante,
    BigDecimal notaObtenida,
    String observacion,
    OffsetDateTime fechaRegistro
) {
    public Calificacion {
        if (idEvaluacion == null) throw new IllegalArgumentException("La evaluación es obligatoria");
        if (idEstudiante == null) throw new IllegalArgumentException("El estudiante es obligatorio");
    }

    public static Calificacion nueva(Id idEvaluacion, Id idEstudiante, BigDecimal notaObtenida, String observacion) {
        return new Calificacion(null, idEvaluacion, idEstudiante, notaObtenida, observacion, OffsetDateTime.now());
    }
}
