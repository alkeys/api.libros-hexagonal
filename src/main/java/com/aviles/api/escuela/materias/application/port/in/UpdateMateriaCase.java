package com.aviles.api.escuela.materias.application.port.in;

import com.aviles.api.escuela.materias.domain.Materia;

public interface UpdateMateriaCase {
    Materia update(Materia materia);
}