package com.aviles.api.escuela.usuarios.application.port.out;

import java.util.List;
import com.aviles.api.escuela.usuarios.domain.Rol;

public interface RolRepositoryPort {
    Rol save(Rol rol);
    List<Rol> findAllRoles();
}
