package com.aviles.api.escuela.evaluaciones.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa la nota final de un estudiante en una asignación y período.
 */
public record NotaFinal(
    Id id,
    Id idEstudiante,
    Id idAsignacion,
    Id idPeriodo,
    BigDecimal nota,
    String estado,
    String observacion,
    OffsetDateTime fechaRegistro
) {
    public NotaFinal {
        if (idEstudiante == null) throw new IllegalArgumentException("El estudiante es obligatorio");
        if (idAsignacion == null) throw new IllegalArgumentException("La asignación es obligatoria");
        if (idPeriodo == null) throw new IllegalArgumentException("El período es obligatorio");
    }

    public static NotaFinal nueva(Id idEstudiante, Id idAsignacion, Id idPeriodo, BigDecimal nota) {
        return new NotaFinal(null, idEstudiante, idAsignacion, idPeriodo, nota, "PENDIENTE", null, OffsetDateTime.now());
    }
}
