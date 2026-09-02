package com.aviles.api.escuela.evaluaciones.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa una evaluación programada para una clase.
 */
public record Evaluacion(
    Id id,
    Id idAsignacion,
    Id idPeriodo,
    Id idTipoEvaluacion,
    String nombre,
    String descripcion,
    LocalDate fechaEvaluacion,
    BigDecimal porcentaje,
    BigDecimal notaMaxima,
    String estado
) {
    public Evaluacion {
        if (idAsignacion == null) throw new IllegalArgumentException("La asignación es obligatoria");
        if (idPeriodo == null) throw new IllegalArgumentException("El período es obligatorio");
        if (idTipoEvaluacion == null) throw new IllegalArgumentException("El tipo de evaluación es obligatorio");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
    }

    public static Evaluacion nueva(Id idAsignacion, Id idPeriodo, Id idTipoEvaluacion, String nombre,
                                    LocalDate fechaEvaluacion, BigDecimal porcentaje, BigDecimal notaMaxima) {
        return new Evaluacion(null, idAsignacion, idPeriodo, idTipoEvaluacion, nombre, null,
                fechaEvaluacion, porcentaje, notaMaxima, "PROGRAMADA");
    }
}
