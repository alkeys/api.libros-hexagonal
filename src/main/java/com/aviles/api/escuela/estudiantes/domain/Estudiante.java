package com.aviles.api.escuela.estudiantes.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un estudiante del sistema escolar.
 * Contiene información personal, académica y de contacto.
 */
public record Estudiante(
    Id id,
    String codigoEstudiante,
    String nombres,
    String apellidos,
    LocalDate fechaNacimiento,
    String genero,
    String nacionalidad,
    String dui,
    String nie,
    String correoElectronico,
    String telefono,
    String direccion,
    LocalDate fechaIngreso,
    String estado,
    String fotoUrl,
    OffsetDateTime fechaCreacion,
    OffsetDateTime fechaActualizacion
) {
    public Estudiante {
        if (codigoEstudiante == null || codigoEstudiante.isBlank()) throw new IllegalArgumentException("El código es obligatorio");
        if (nombres == null || nombres.isBlank()) throw new IllegalArgumentException("Los nombres son obligatorios");
        if (apellidos == null || apellidos.isBlank()) throw new IllegalArgumentException("Los apellidos son obligatorios");
    }

    public static Estudiante nuevo(String codigoEstudiante, String nombres, String apellidos,
                                    LocalDate fechaNacimiento, String genero) {
        return new Estudiante(null, codigoEstudiante, nombres, apellidos, fechaNacimiento, genero,
                null, null, null, null, null, null, null, "ACTIVO", null,
                OffsetDateTime.now(), OffsetDateTime.now());
    }

    public static Estudiante nuevo(String codigoEstudiante, String nombres, String apellidos,
                                    LocalDate fechaNacimiento, String genero, String nacionalidad,
                                    String dui, String nie, String correoElectronico, String telefono,
                                    String direccion, LocalDate fechaIngreso) {
        return new Estudiante(null, codigoEstudiante, nombres, apellidos, fechaNacimiento, genero,
                nacionalidad, dui, nie, correoElectronico, telefono, direccion, fechaIngreso, "ACTIVO", null,
                OffsetDateTime.now(), OffsetDateTime.now());
    }
}
