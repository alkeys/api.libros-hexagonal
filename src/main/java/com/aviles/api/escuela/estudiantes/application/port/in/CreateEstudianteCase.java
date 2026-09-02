package com.aviles.api.escuela.estudiantes.application.port.in;

import com.aviles.api.escuela.estudiantes.domain.Estudiante;

public interface CreateEstudianteCase {
    Estudiante create(Estudiante estudiante);
}
