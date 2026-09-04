package com.aviles.api.escuela.niveles.application.port.in;

import com.aviles.api.escuela.niveles.domain.NivelEducativo;

public interface UpdateNivelCase {
    NivelEducativo update(NivelEducativo nivel);
}