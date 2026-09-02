package com.aviles.api.escuela.anioescolar.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un período académico dentro de un año escolar.
 * Un año escolar puede tener múltiples períodos (trimestres, semestres, etc.).
 */
public record PeriodoAcademico(
    Id id,
    Id idAnioEscolar,
    String nombre,
    Integer numeroPeriodo,
    java.time.LocalDate fechaInicio,
    java.time.LocalDate fechaFin,
    String estado
) {
    public PeriodoAcademico {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
        if (numeroPeriodo == null || numeroPeriodo <= 0) throw new IllegalArgumentException("El número de período debe ser mayor a 0");
        if (idAnioEscolar == null) throw new IllegalArgumentException("El año escolar es obligatorio");
        if (!fechaInicio.isBefore(fechaFin)) throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la de fin");
    }

    public static PeriodoAcademico nuevo(Id idAnioEscolar, String nombre, Integer numeroPeriodo,
                                          java.time.LocalDate fechaInicio, java.time.LocalDate fechaFin) {
        return new PeriodoAcademico(null, idAnioEscolar, nombre, numeroPeriodo, fechaInicio, fechaFin, "PLANIFICADO");
    }
}
