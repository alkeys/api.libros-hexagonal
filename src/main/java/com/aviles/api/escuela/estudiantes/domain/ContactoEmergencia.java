package com.aviles.api.escuela.estudiantes.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un contacto de emergencia de un estudiante.
 */
public record ContactoEmergencia(
    Id id,
    Id idEstudiante,
    String nombres,
    String apellidos,
    String parentesco,
    String telefono,
    String telefonoAlternativo,
    Integer prioridad
) {
    public ContactoEmergencia {
        if (idEstudiante == null) throw new IllegalArgumentException("El estudiante es obligatorio");
        if (nombres == null || nombres.isBlank()) throw new IllegalArgumentException("Los nombres son obligatorios");
        if (telefono == null || telefono.isBlank()) throw new IllegalArgumentException("El teléfono es obligatorio");
        if (prioridad == null || prioridad <= 0) throw new IllegalArgumentException("La prioridad debe ser mayor a 0");
    }

    public static ContactoEmergencia nuevo(Id idEstudiante, String nombres, String apellidos,
                                            String parentesco, String telefono, Integer prioridad) {
        return new ContactoEmergencia(null, idEstudiante, nombres, apellidos, parentesco, telefono, null, prioridad);
    }
}
