package com.aviles.api.escuela.actividades.application.port.in;

import com.aviles.api.escuela.actividades.domain.Actividad;

public interface CreateActividadCase {
    Actividad create(Actividad actividad);
}
