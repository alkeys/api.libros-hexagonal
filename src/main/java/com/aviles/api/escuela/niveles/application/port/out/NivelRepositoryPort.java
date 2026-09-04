package com.aviles.api.escuela.niveles.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.niveles.domain.NivelEducativo;

public interface NivelRepositoryPort {
    NivelEducativo save(NivelEducativo nivel);
    List<NivelEducativo> findAll();
    Optional<NivelEducativo> findNivelById(Long id);
    void deleteNivelById(Long id);
}