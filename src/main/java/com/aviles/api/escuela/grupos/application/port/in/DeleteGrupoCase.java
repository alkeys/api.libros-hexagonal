package com.aviles.api.escuela.grupos.application.port.in;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface DeleteGrupoCase {
    void delete(Id id);
}