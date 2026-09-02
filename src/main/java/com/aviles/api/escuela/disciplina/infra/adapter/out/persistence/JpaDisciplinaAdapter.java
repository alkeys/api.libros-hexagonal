package com.aviles.api.escuela.disciplina.infra.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.disciplina.application.port.out.*;
import com.aviles.api.escuela.disciplina.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaDisciplinaAdapter implements IncidenteRepositoryPort, ObservacionRepositoryPort {

    private final SpringDataIncidenteRepository incidenteRepo;
    private final SpringDataObservacionRepository observacionRepo;

    public JpaDisciplinaAdapter(SpringDataIncidenteRepository incidenteRepo, SpringDataObservacionRepository observacionRepo) {
        this.incidenteRepo = incidenteRepo;
        this.observacionRepo = observacionRepo;
    }

    @Override
    public IncidenteDisciplinario save(IncidenteDisciplinario i) { return toDomainIncidente(incidenteRepo.save(toJpaIncidente(i))); }
    @Override
    public List<IncidenteDisciplinario> findAllIncidentes() { return incidenteRepo.findAll().stream().map(this::toDomainIncidente).collect(Collectors.toList()); }
    @Override
    public ObservacionAcademica save(ObservacionAcademica o) { return toDomainObservacion(observacionRepo.save(toJpaObservacion(o))); }
    @Override
    public List<ObservacionAcademica> findAllObservaciones() { return observacionRepo.findAll().stream().map(this::toDomainObservacion).collect(Collectors.toList()); }

    private IncidenteDisciplinario toDomainIncidente(JpaIncidenteDisciplinario j) {
        return new IncidenteDisciplinario(new Id(j.getId()), new Id(j.getIdEstudiante()), j.getFecha(), j.getTipo(), j.getMotivo(), j.getDescripcion(), j.getMedidaTomada(), j.getEstado(), j.getIdProfesor() != null ? new Id(j.getIdProfesor()) : null);
    }
    private JpaIncidenteDisciplinario toJpaIncidente(IncidenteDisciplinario d) {
        return new JpaIncidenteDisciplinario(d.id() != null ? d.id().getValue() : null, d.idEstudiante().getValue(), d.fecha(), d.tipo(), d.motivo(), d.descripcion(), d.medidaTomada(), d.estado(), d.idProfesor() != null ? d.idProfesor().getValue() : null, null);
    }
    private ObservacionAcademica toDomainObservacion(JpaObservacionAcademica j) {
        return new ObservacionAcademica(new Id(j.getId()), new Id(j.getIdEstudiante()), j.getIdProfesor() != null ? new Id(j.getIdProfesor()) : null, j.getIdPeriodo() != null ? new Id(j.getIdPeriodo()) : null, j.getFecha(), j.getTipo(), j.getDescripcion());
    }
    private JpaObservacionAcademica toJpaObservacion(ObservacionAcademica d) {
        return new JpaObservacionAcademica(d.id() != null ? d.id().getValue() : null, d.idEstudiante().getValue(), d.idProfesor() != null ? d.idProfesor().getValue() : null, d.idPeriodo() != null ? d.idPeriodo().getValue() : null, d.fecha(), d.tipo(), d.descripcion());
    }
}
