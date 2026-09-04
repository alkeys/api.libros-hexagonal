package com.aviles.api.escuela.niveles.application.port.in;

import com.aviles.api.escuela.niveles.domain.Seccion;

public interface UpdateSeccionCase {
    Seccion update(Seccion seccion);
}