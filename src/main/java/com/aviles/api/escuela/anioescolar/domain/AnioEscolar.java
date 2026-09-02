package com.aviles.api.escuela.anioescolar.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un año escolar.
 * Contiene las fechas de inicio y fin, y su estado actual.
 */
public record AnioEscolar(
    Id id,
    String nombre,
    Integer anio,
    java.time.LocalDate fechaInicio,
    java.time.LocalDate fechaFin,
    String estado
) {
    public AnioEscolar {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
        if (anio == null || anio < 2000 || anio > 2100) throw new IllegalArgumentException("El año debe estar entre 2000 y 2100");
        if (fechaInicio == null || fechaFin == null) throw new IllegalArgumentException("Las fechas son obligatorias");
        if (!fechaInicio.isBefore(fechaFin)) throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la de fin");
    }

    public static AnioEscolar nuevo(String nombre, Integer anio, java.time.LocalDate fechaInicio, java.time.LocalDate fechaFin) {
        return new AnioEscolar(null, nombre, anio, fechaInicio, fechaFin, "PLANIFICADO");
    }
}
