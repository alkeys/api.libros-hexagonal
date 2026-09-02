package com.aviles.api.escuela.estudiantes.application.port.in;

import com.aviles.api.escuela.estudiantes.domain.Matricula;

public interface CreateMatriculaCase {
    Matricula create(Matricula matricula);
}
