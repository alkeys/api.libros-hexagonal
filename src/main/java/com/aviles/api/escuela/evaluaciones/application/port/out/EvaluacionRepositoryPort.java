package com.aviles.api.escuela.evaluaciones.application.port.out;

import java.util.List;
import com.aviles.api.escuela.evaluaciones.domain.Evaluacion;

public interface EvaluacionRepositoryPort {
    Evaluacion save(Evaluacion evaluacion);
    List<Evaluacion> findAllEvaluaciones();
}
