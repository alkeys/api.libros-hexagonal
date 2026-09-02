package com.aviles.api.escuela.disciplina.application.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.aviles.api.escuela.disciplina.application.port.in.*;
import com.aviles.api.escuela.disciplina.application.port.out.*;
import com.aviles.api.escuela.disciplina.domain.*;

@Service
public class DisciplinaService implements CreateIncidenteCase, CreateObservacionCase {
    private final IncidenteRepositoryPort incidenteRepositoryPort;
    private final ObservacionRepositoryPort observacionRepositoryPort;

    public DisciplinaService(IncidenteRepositoryPort incidenteRepositoryPort, ObservacionRepositoryPort observacionRepositoryPort) {
        this.incidenteRepositoryPort = incidenteRepositoryPort;
        this.observacionRepositoryPort = observacionRepositoryPort;
    }

    @Override
    public IncidenteDisciplinario create(IncidenteDisciplinario incidente) { return incidenteRepositoryPort.save(incidente); }
    @Override
    public ObservacionAcademica create(ObservacionAcademica observacion) { return observacionRepositoryPort.save(observacion); }
}
