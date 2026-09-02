package com.aviles.api.escuela.usuarios.application.port.in;

import com.aviles.api.escuela.usuarios.domain.Permiso;

public interface CreatePermisoCase {
    Permiso create(Permiso permiso);
}
