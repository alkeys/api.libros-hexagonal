package com.aviles.api.escuela.actividades.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa la entrega de una actividad por parte de un estudiante.
 */
public record EntregaActividad(
    Id id,
    Id idActividad,
    Id idEstudiante,
    OffsetDateTime fechaEntrega,
    String archivoUrl,
    String comentario,
    BigDecimal nota,
    String estado
) {
    public EntregaActividad {
        if (idActividad == null) throw new IllegalArgumentException("La actividad es obligatoria");
        if (idEstudiante == null) throw new IllegalArgumentException("El estudiante es obligatorio");
    }

    public static EntregaActividad nueva(Id idActividad, Id idEstudiante, String archivoUrl, String comentario) {
        return new EntregaActividad(null, idActividad, idEstudiante, OffsetDateTime.now(), archivoUrl, comentario, null, "PENDIENTE");
    }
}
