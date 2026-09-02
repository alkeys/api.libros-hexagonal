package com.aviles.api.escuela.auditoria.domain;

import java.time.OffsetDateTime;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un registro de auditoría del sistema.
 * Registra todas las acciones realizadas en la base de datos.
 */
public record Auditoria(
    Id id,
    Id idUsuario,
    String tablaAfectada,
    String idRegistro,
    String accion,
    String datosAnteriores,
    String datosNuevos,
    String ip,
    OffsetDateTime fecha
) {
    public Auditoria {
        if (tablaAfectada == null || tablaAfectada.isBlank()) throw new IllegalArgumentException("La tabla afectada es obligatoria");
        if (accion == null || accion.isBlank()) throw new IllegalArgumentException("La acción es obligatoria");
    }

    public static Auditoria nueva(Id idUsuario, String tablaAfectada, String idRegistro, String accion) {
        return new Auditoria(null, idUsuario, tablaAfectada, idRegistro, accion, null, null, null, OffsetDateTime.now());
    }
}
