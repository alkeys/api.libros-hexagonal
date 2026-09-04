package com.aviles.api.escuela.evaluaciones.infra.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataCalificacionRepository extends JpaRepository<JpaCalificacion, Long> {
    List<JpaCalificacion> findByIdEvaluacion(Long idEvaluacion);
    List<JpaCalificacion> findByIdEstudiante(Long idEstudiante);
    Optional<JpaCalificacion> findByIdEvaluacionAndIdEstudiante(Long idEvaluacion, Long idEstudiante);
}
