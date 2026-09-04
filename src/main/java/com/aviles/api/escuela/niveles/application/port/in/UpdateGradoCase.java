package com.aviles.api.escuela.niveles.application.port.in;

import com.aviles.api.escuela.niveles.domain.Grado;

public interface UpdateGradoCase {
    Grado update(Grado grado);
}