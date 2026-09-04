package com.aviles.api.escuela.usuarios.application.port.in;

import java.util.List;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface GetRolesUsuarioCase {
    /** Roles efectivos (con ESTUDIANTE por defecto si no tiene ninguno). */
    List<String> getRoles(Id idUsuario);

    /** Roles realmente asignados en la BD (sin el default). */
    List<String> getRolesAsignados(Id idUsuario);
}