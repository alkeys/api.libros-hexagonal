package com.aviles.api.escuela.estudiantes.application.port.in;

import java.util.List;
import com.aviles.api.escuela.estudiantes.domain.Estudiante;

public interface GetAllEstudiantesCase {
    List<Estudiante> getAll();
}
