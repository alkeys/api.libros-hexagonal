package com.aviles.api.escuela.profesores.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un profesor del sistema escolar.
 */
public record Profesor(
    Id id,
    String codigoProfesor,
    String nombres,
    String apellidos,
    String dui,
    String especialidad,
    String correoElectronico,
    String telefono,
    String direccion,
    LocalDate fechaContratacion,
    String estado,
    String fotoUrl,
    OffsetDateTime fechaCreacion
) {
    public Profesor {
        if (codigoProfesor == null || codigoProfesor.isBlank()) throw new IllegalArgumentException("El código es obligatorio");
        if (nombres == null || nombres.isBlank()) throw new IllegalArgumentException("Los nombres son obligatorios");
        if (apellidos == null || apellidos.isBlank()) throw new IllegalArgumentException("Los apellidos son obligatorios");
    }

    public static Profesor nuevo(String codigoProfesor, String nombres, String apellidos, String especialidad) {
        return new Profesor(null, codigoProfesor, nombres, apellidos, null, especialidad, null, null, null,
                null, "ACTIVO", null, OffsetDateTime.now());
    }
}
