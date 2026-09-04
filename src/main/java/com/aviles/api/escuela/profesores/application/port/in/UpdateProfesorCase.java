package com.aviles.api.escuela.profesores.application.port.in;

import com.aviles.api.escuela.profesores.domain.Profesor;

public interface UpdateProfesorCase {
    Profesor update(Profesor profesor);
}