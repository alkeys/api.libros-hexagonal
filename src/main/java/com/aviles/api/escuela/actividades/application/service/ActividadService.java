package com.aviles.api.escuela.actividades.application.service;

import org.springframework.stereotype.Service;
import com.aviles.api.escuela.actividades.application.port.in.CreateActividadCase;
import com.aviles.api.escuela.actividades.application.port.out.ActividadRepositoryPort;
import com.aviles.api.escuela.actividades.domain.Actividad;

@Service
public class ActividadService implements CreateActividadCase {
    private final ActividadRepositoryPort actividadRepositoryPort;

    public ActividadService(ActividadRepositoryPort actividadRepositoryPort) {
        this.actividadRepositoryPort = actividadRepositoryPort;
    }

    @Override
    public Actividad create(Actividad actividad) { return actividadRepositoryPort.save(actividad); }
}
