package com.aviles.api.escuela.documentos.domain;

import java.time.OffsetDateTime;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un documento de un estudiante (DUI, NIE, partida, etc.).
 */
public record DocumentoEstudiante(
    Id id,
    Id idEstudiante,
    String tipoDocumento,
    String nombreArchivo,
    String urlArchivo,
    OffsetDateTime fechaSubida,
    String estado,
    String observacion
) {
    public DocumentoEstudiante {
        if (idEstudiante == null) throw new IllegalArgumentException("El estudiante es obligatorio");
        if (tipoDocumento == null || tipoDocumento.isBlank()) throw new IllegalArgumentException("El tipo de documento es obligatorio");
    }

    public static DocumentoEstudiante nuevo(Id idEstudiante, String tipoDocumento, String nombreArchivo, String urlArchivo) {
        return new DocumentoEstudiante(null, idEstudiante, tipoDocumento, nombreArchivo, urlArchivo, OffsetDateTime.now(), "PENDIENTE", null);
    }
}
