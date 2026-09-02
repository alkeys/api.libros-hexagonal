package com.aviles.api.escuela.asistencia.infra.adapter.out.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataAsistenciaRepository extends JpaRepository<JpaAsistencia, Long> {
    List<JpaAsistencia> findByIdAsignacion(Long idAsignacion);
}
