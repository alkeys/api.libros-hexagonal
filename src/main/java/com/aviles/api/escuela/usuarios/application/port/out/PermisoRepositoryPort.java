package com.aviles.api.escuela.usuarios.application.port.out;

import com.aviles.api.escuela.usuarios.domain.Permiso;

public interface PermisoRepositoryPort {
    Permiso save(Permiso permiso);
    java.util.List<Permiso> findAllPermisos();
}
