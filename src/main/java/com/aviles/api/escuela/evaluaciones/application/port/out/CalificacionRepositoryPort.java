package com.aviles.api.escuela.evaluaciones.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.evaluaciones.domain.Calificacion;
import com.aviles.api.escuela.shared.domain.values.Id;

public interface CalificacionRepositoryPort {
    Calificacion save(Calificacion calificacion);
    List<Calificacion> findByEvaluacion(Id idEvaluacion);
    List<Calificacion> findByEstudiante(Id idEstudiante);
    Optional<Calificacion> findByEvaluacionAndEstudiante(Id idEvaluacion, Id idEstudiante);
    Optional<Calificacion> findCalificacionById(Long id);
    void deleteCalificacionById(Long id);
}