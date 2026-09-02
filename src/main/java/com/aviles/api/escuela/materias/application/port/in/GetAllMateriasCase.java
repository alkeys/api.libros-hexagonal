package com.aviles.api.escuela.materias.application.port.in;

import java.util.List;
import com.aviles.api.escuela.materias.domain.Materia;

public interface GetAllMateriasCase {
    List<Materia> getAll();
}
