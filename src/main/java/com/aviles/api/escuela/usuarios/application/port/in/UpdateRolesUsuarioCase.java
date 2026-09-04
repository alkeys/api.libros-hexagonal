package com.aviles.api.escuela.usuarios.application.port.in;

import java.util.List;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface UpdateRolesUsuarioCase {
    /**
     * Reemplaza los roles asignados de un usuario por la lista indicada.
     * Devuelve los nombres de roles asignados tras la operación.
     */
    List<String> updateRoles(Id idUsuario, List<String> roles);
}
