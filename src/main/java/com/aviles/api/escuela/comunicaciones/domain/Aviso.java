package com.aviles.api.escuela.comunicaciones.domain;

import java.time.OffsetDateTime;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un aviso o comunicado de la institución.
 */
public record Aviso(
    Id id,
    String titulo,
    String contenido,
    OffsetDateTime fechaPublicacion,
    OffsetDateTime fechaExpiracion,
    String prioridad,
    String estado,
    Id idUsuarioAutor
) {
    public Aviso {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("El título es obligatorio");
        if (contenido == null || contenido.isBlank()) throw new IllegalArgumentException("El contenido es obligatorio");
    }

    public static Aviso nuevo(String titulo, String contenido, String prioridad, Id idUsuarioAutor) {
        return new Aviso(null, titulo, contenido, OffsetDateTime.now(), null, prioridad, "BORRADOR", idUsuarioAutor);
    }
}
