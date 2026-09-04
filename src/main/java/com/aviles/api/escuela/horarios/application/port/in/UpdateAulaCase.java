package com.aviles.api.escuela.horarios.application.port.in;

import com.aviles.api.escuela.horarios.domain.Aula;

public interface UpdateAulaCase {
    Aula update(Aula aula);
}