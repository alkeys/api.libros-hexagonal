package com.aviles.api.escuela.evaluaciones.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.evaluaciones.domain.Evaluacion;

public interface EvaluacionRepositoryPort {
    Evaluacion save(Evaluacion evaluacion);
    List<Evaluacion> findAllEvaluaciones();
    Optional<Evaluacion> findEvaluacionById(Long id);
    void deleteEvaluacionById(Long id);
}