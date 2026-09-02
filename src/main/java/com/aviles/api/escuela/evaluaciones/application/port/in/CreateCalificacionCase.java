package com.aviles.api.escuela.evaluaciones.application.port.in;

import com.aviles.api.escuela.evaluaciones.domain.Calificacion;

public interface CreateCalificacionCase {
    Calificacion create(Calificacion calificacion);
}
