package com.aviles.api.escuela.evaluaciones.application.port.in;

import com.aviles.api.escuela.evaluaciones.domain.Evaluacion;

public interface UpdateEvaluacionCase {
    Evaluacion update(Evaluacion evaluacion);
}