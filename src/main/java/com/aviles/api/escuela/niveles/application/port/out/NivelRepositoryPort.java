package com.aviles.api.escuela.niveles.application.port.out;

import java.util.List;
import com.aviles.api.escuela.niveles.domain.NivelEducativo;

public interface NivelRepositoryPort {
    NivelEducativo save(NivelEducativo nivel);
    List<NivelEducativo> findAll();
}
