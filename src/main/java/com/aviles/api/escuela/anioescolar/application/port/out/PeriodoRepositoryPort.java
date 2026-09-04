package com.aviles.api.escuela.anioescolar.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.anioescolar.domain.PeriodoAcademico;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Puerto de salida para la persistencia de períodos académicos.
 */
public interface PeriodoRepositoryPort {
    PeriodoAcademico save(PeriodoAcademico periodo);
    List<PeriodoAcademico> findByAnioEscolar(Id idAnioEscolar);
    Optional<PeriodoAcademico> findPeriodoById(Long id);
    void deletePeriodoById(Long id);
}