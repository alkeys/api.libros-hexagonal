package com.aviles.api.escuela.horarios.application.port.in;

import com.aviles.api.escuela.horarios.domain.BloqueHorario;

public interface UpdateBloqueHorarioCase {
    BloqueHorario update(BloqueHorario bloque);
}