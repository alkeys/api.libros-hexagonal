package com.aviles.api.escuela.niveles.application.port.in;

import com.aviles.api.escuela.niveles.domain.Seccion;

public interface CreateSeccionCase {
    Seccion create(Seccion seccion);
}
