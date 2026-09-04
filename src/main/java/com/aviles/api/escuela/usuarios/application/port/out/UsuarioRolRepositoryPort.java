package com.aviles.api.escuela.usuarios.application.port.out;

import java.util.List;
import com.aviles.api.escuela.shared.domain.values.Id;

public interface UsuarioRolRepositoryPort {
    List<String> findRolNamesByUsuario(Id idUsuario);
    void assignRol(Id idUsuario, Id idRol);
    /** Elimina la asignación de un rol concreto a un usuario (si existe). */
    void removeRol(Id idUsuario, Id idRol);
}