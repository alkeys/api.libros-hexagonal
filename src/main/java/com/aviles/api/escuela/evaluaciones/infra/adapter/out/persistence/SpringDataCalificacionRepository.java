package com.aviles.api.escuela.evaluaciones.infra.adapter.out.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataCalificacionRepository extends JpaRepository<JpaCalificacion, Long> {
    List<JpaCalificacion> findByIdEvaluacion(Long idEvaluacion);
}
