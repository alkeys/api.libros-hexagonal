package com.aviles.api.escuela.representantes.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un representante (padre/tutor) de un estudiante.
 */
public record Representante(
    Id id,
    String nombres,
    String apellidos,
    String dui,
    String correoElectronico,
    String telefono,
    String telefonoAlternativo,
    String direccion,
    String ocupacion,
    String estado
) {
    public Representante {
        if (nombres == null || nombres.isBlank()) throw new IllegalArgumentException("Los nombres son obligatorios");
        if (apellidos == null || apellidos.isBlank()) throw new IllegalArgumentException("Los apellidos son obligatorios");
    }

    public static Representante nuevo(String nombres, String apellidos, String dui, String telefono) {
        return new Representante(null, nombres, apellidos, dui, null, telefono, null, null, null, "ACTIVO");
    }
}
