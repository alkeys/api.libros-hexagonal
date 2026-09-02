package com.aviles.api.escuela.niveles.application.port.in;

import java.util.List;
import com.aviles.api.escuela.niveles.domain.Grado;
import com.aviles.api.escuela.shared.domain.values.Id;

public interface GetGradosByNivelCase {
    List<Grado> getByNivel(Id idNivel);
}
