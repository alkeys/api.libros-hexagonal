package com.aviles.api.escuela.grupos.application.port.in;

import com.aviles.api.escuela.grupos.domain.Grupo;

public interface CreateGrupoCase {
    Grupo create(Grupo grupo);
}
