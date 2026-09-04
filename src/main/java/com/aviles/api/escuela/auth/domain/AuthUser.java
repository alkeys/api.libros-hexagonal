package com.aviles.api.escuela.auth.domain;

import java.util.List;

/**
 * Usuario autenticado a partir del token JWT.
 */
public record AuthUser(
    Long id,
    String username,
    List<String> roles,
    Long idProfesor,
    Long idEstudiante
) {
    public boolean hasRole(String rol) {
        return roles != null && roles.stream().anyMatch(r -> r.equalsIgnoreCase(rol));
    }
}