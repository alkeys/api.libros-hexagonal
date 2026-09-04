package com.aviles.api.escuela.anioescolar.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.anioescolar.domain.AnioEscolar;

/**
 * Puerto de salida para la persistencia de años escolares.
 */
public interface AnioEscolarRepositoryPort {
    AnioEscolar save(AnioEscolar anioEscolar);
    List<AnioEscolar> findAll();
    Optional<AnioEscolar> findAnioEscolarById(Long id);
    void deleteAnioEscolarById(Long id);
}