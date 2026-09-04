package com.aviles.api.escuela.evaluaciones.application.port.in;

import java.util.List;

import com.aviles.api.escuela.evaluaciones.domain.NotaEstudiante;
import com.aviles.api.escuela.shared.domain.values.Id;

public interface GetNotasEstudianteCase {
    List<NotaEstudiante> getNotasByEstudiante(Id idEstudiante);
}