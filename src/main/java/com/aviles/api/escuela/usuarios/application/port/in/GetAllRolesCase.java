package com.aviles.api.escuela.usuarios.application.port.in;

import java.util.List;
import com.aviles.api.escuela.usuarios.domain.Rol;

public interface GetAllRolesCase {
    List<Rol> getAllRoles();
}
