package com.aviles.api.escuela.evaluaciones.application.port.in;

import java.util.List;
import com.aviles.api.escuela.evaluaciones.domain.Evaluacion;

public interface GetAllEvaluacionesCase {
    List<Evaluacion> getAll();
}
