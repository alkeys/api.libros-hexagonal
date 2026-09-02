package com.aviles.api.escuela.estudiantes.application.port.in;

import java.util.Optional;
import com.aviles.api.escuela.estudiantes.domain.Estudiante;
import com.aviles.api.escuela.shared.domain.values.Id;

public interface GetEstudianteByIdCase {
    Optional<Estudiante> getById(Id id);
}
