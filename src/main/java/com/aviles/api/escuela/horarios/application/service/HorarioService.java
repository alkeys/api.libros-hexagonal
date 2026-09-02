package com.aviles.api.escuela.horarios.application.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.aviles.api.escuela.horarios.application.port.in.*;
import com.aviles.api.escuela.horarios.application.port.out.*;
import com.aviles.api.escuela.horarios.domain.*;

@Service
public class HorarioService implements CreateAulaCase, GetAllAulasCase,
        CreateBloqueHorarioCase, GetAllBloquesHorarioCase,
        CreateAsignacionClaseCase, GetAllAsignacionesCase {

    private final AulaRepositoryPort aulaRepositoryPort;
    private final BloqueHorarioRepositoryPort bloqueHorarioRepositoryPort;
    private final AsignacionClaseRepositoryPort asignacionClaseRepositoryPort;

    public HorarioService(AulaRepositoryPort aulaRepositoryPort, BloqueHorarioRepositoryPort bloqueHorarioRepositoryPort,
                          AsignacionClaseRepositoryPort asignacionClaseRepositoryPort) {
        this.aulaRepositoryPort = aulaRepositoryPort;
        this.bloqueHorarioRepositoryPort = bloqueHorarioRepositoryPort;
        this.asignacionClaseRepositoryPort = asignacionClaseRepositoryPort;
    }

    @Override
    public Aula create(Aula aula) { return aulaRepositoryPort.save(aula); }

    @Override
    public List<Aula> getAllAulas() { return aulaRepositoryPort.findAll(); }

    @Override
    public BloqueHorario create(BloqueHorario bloque) { return bloqueHorarioRepositoryPort.save(bloque); }

    @Override
    public List<BloqueHorario> getAllBloques() { return bloqueHorarioRepositoryPort.findAllBloques(); }

    @Override
    public AsignacionClase create(AsignacionClase asignacion) { return asignacionClaseRepositoryPort.save(asignacion); }

    @Override
    public List<AsignacionClase> getAllAsignaciones() { return asignacionClaseRepositoryPort.findAllAsignaciones(); }
}
