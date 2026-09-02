package com.aviles.api.escuela.usuarios.application.port.in;

import com.aviles.api.escuela.usuarios.domain.Rol;

public interface CreateRolCase {
    Rol create(Rol rol);
}
