package com.aviles.api.escuela.comunicaciones.domain;

import java.time.OffsetDateTime;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un evento institucional.
 */
public record Evento(
    Id id,
    String titulo,
    String descripcion,
    OffsetDateTime fechaInicio,
    OffsetDateTime fechaFin,
    String ubicacion,
    String tipo,
    String estado
) {
    public Evento {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("El título es obligatorio");
        if (fechaInicio == null) throw new IllegalArgumentException("La fecha de inicio es obligatoria");
    }

    public static Evento nuevo(String titulo, String descripcion, OffsetDateTime fechaInicio, String ubicacion, String tipo) {
        return new Evento(null, titulo, descripcion, fechaInicio, null, ubicacion, tipo, "PROGRAMADO");
    }
}
