package com.aviles.api.escuela.profesores.application.port.in;

import java.util.List;
import com.aviles.api.escuela.profesores.domain.Profesor;

public interface GetAllProfesoresCase {
    List<Profesor> getAll();
}
