package com.aviles.api.escuela.estudiantes.application.port.in;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface DeleteEstudianteCase {
    void delete(Id id);
}