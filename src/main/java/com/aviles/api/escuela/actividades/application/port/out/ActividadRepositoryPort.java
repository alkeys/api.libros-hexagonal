package com.aviles.api.escuela.actividades.application.port.out;

import com.aviles.api.escuela.actividades.domain.Actividad;

public interface ActividadRepositoryPort {
    Actividad save(Actividad actividad);
    java.util.List<Actividad> findAll();
}
