package com.aviles.api.escuela.usuarios.domain;

import java.time.OffsetDateTime;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un usuario del sistema escolar.
 * Los usuarios pueden ser estudiantes, profesores, administradores, etc.
 */
public record Usuario(
    Id id,
    String username,
    String passwordHash,
    String correo,
    String estado,
    Integer intentosFallidos,
    OffsetDateTime fechaCreacion,
    OffsetDateTime fechaActualizacion
) {
    public Usuario {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("El username es obligatorio");
        if (passwordHash == null || passwordHash.isBlank()) throw new IllegalArgumentException("El password hash es obligatorio");
    }

    public static Usuario nuevo(String username, String passwordHash, String correo) {
        return new Usuario(null, username, passwordHash, correo, "ACTIVO", 0, OffsetDateTime.now(), OffsetDateTime.now());
    }
}
