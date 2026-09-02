package com.aviles.api.escuela.horarios.application.port.in;

import com.aviles.api.escuela.horarios.domain.Aula;

public interface CreateAulaCase {
    Aula create(Aula aula);
}
