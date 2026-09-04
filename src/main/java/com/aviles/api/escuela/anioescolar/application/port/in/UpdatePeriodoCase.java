package com.aviles.api.escuela.anioescolar.application.port.in;

import com.aviles.api.escuela.anioescolar.domain.PeriodoAcademico;

public interface UpdatePeriodoCase {
    PeriodoAcademico update(PeriodoAcademico periodo);
}