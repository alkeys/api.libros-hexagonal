package com.aviles.api.escuela.profesores.application.port.in;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface DeleteProfesorCase {
    void delete(Id id);
}