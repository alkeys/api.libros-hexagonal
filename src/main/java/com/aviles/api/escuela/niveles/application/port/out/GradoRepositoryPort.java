package com.aviles.api.escuela.niveles.application.port.out;

import java.util.List;
import com.aviles.api.escuela.niveles.domain.Grado;
import com.aviles.api.escuela.shared.domain.values.Id;

public interface GradoRepositoryPort {
    Grado save(Grado grado);
    List<Grado> findAllGrados();
    List<Grado> findByNivel(Id idNivel);
}
