package com.aviles.api.escuela.anioescolar.infra.adapter.out.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPeriodoRepository extends JpaRepository<JpaPeriodoAcademico, Long> {
    List<JpaPeriodoAcademico> findByIdAnioEscolar(Long idAnioEscolar);
}
