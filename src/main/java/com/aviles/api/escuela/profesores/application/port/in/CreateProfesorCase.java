package com.aviles.api.escuela.profesores.application.port.in;

import com.aviles.api.escuela.profesores.domain.Profesor;

public interface CreateProfesorCase {
    Profesor create(Profesor profesor);
}
