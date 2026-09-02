package com.aviles.api.escuela.niveles.application.port.in;

import java.util.List;
import com.aviles.api.escuela.niveles.domain.NivelEducativo;

public interface GetAllNivelesCase {
    List<NivelEducativo> getAll();
}
