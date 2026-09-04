package com.aviles.api.escuela.horarios.application.port.in;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface DeleteBloqueHorarioCase {
    void deleteBloque(Id id);
}