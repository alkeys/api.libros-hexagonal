package com.aviles.api.escuela.actividades.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa una actividad académica asignada a los estudiantes.
 */
public record Actividad(
    Id id,
    Id idAsignacion,
    Id idPeriodo,
    String titulo,
    String descripcion,
    OffsetDateTime fechaPublicacion,
    OffsetDateTime fechaEntrega,
    BigDecimal porcentaje,
    String estado
) {
    public Actividad {
        if (idAsignacion == null) throw new IllegalArgumentException("La asignación es obligatoria");
        if (idPeriodo == null) throw new IllegalArgumentException("El período es obligatorio");
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("El título es obligatorio");
    }

    public static Actividad nueva(Id idAsignacion, Id idPeriodo, String titulo, String descripcion, OffsetDateTime fechaEntrega, BigDecimal porcentaje) {
        return new Actividad(null, idAsignacion, idPeriodo, titulo, descripcion, OffsetDateTime.now(), fechaEntrega, porcentaje, "BORRADOR");
    }
}
