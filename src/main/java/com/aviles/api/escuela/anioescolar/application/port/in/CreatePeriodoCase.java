package com.aviles.api.escuela.anioescolar.application.port.in;

import com.aviles.api.escuela.anioescolar.domain.PeriodoAcademico;

/**
 * Puerto de entrada para crear un período académico.
 */
public interface CreatePeriodoCase {
    PeriodoAcademico create(PeriodoAcademico periodo);
}
