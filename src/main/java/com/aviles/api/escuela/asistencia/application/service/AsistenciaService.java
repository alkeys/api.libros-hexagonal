package com.aviles.api.escuela.asistencia.application.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.aviles.api.escuela.asistencia.application.port.in.*;
import com.aviles.api.escuela.asistencia.application.port.out.AsistenciaRepositoryPort;
import com.aviles.api.escuela.asistencia.domain.Asistencia;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Servicio que implementa los casos de uso del módulo de asistencia.
 * Maneja el registro y consulta de asistencia de estudiantes.
 */
@Service
public class AsistenciaService implements CreateAsistenciaCase, GetAsistenciaByAsignacionCase {

    private final AsistenciaRepositoryPort asistenciaRepositoryPort;

    public AsistenciaService(AsistenciaRepositoryPort asistenciaRepositoryPort) {
        this.asistenciaRepositoryPort = asistenciaRepositoryPort;
    }

    @Override
    public Asistencia create(Asistencia asistencia) { return asistenciaRepositoryPort.save(asistencia); }

    @Override
    public List<Asistencia> getByAsignacion(Id idAsignacion) { return asistenciaRepositoryPort.findByAsignacion(idAsignacion); }
}
