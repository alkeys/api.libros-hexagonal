package com.aviles.api.escuela.evaluaciones.domain;

import java.math.BigDecimal;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un tipo de evaluación (Examen, Tarea, Proyecto, etc.).
 */
public record TipoEvaluacion(
    Id id,
    String nombre,
    String descripcion,
    BigDecimal porcentaje
) {
    public TipoEvaluacion {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
        if (porcentaje == null) throw new IllegalArgumentException("El porcentaje es obligatorio");
    }

    public static TipoEvaluacion nuevo(String nombre, String descripcion, BigDecimal porcentaje) {
        return new TipoEvaluacion(null, nombre, descripcion, porcentaje);
    }
}
