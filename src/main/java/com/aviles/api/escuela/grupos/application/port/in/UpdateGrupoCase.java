package com.aviles.api.escuela.grupos.application.port.in;

import com.aviles.api.escuela.grupos.domain.Grupo;

public interface UpdateGrupoCase {
    Grupo update(Grupo grupo);
}