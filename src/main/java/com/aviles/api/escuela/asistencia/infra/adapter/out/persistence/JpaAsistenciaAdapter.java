package com.aviles.api.escuela.asistencia.infra.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.asistencia.application.port.out.AsistenciaRepositoryPort;
import com.aviles.api.escuela.asistencia.domain.Asistencia;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaAsistenciaAdapter implements AsistenciaRepositoryPort {

    private final SpringDataAsistenciaRepository repository;

    public JpaAsistenciaAdapter(SpringDataAsistenciaRepository repository) { this.repository = repository; }

    @Override
    public Asistencia save(Asistencia asistencia) { return toDomain(repository.save(toJpa(asistencia))); }

    @Override
    public List<Asistencia> findByAsignacion(Id idAsignacion) {
        return repository.findByIdAsignacion(idAsignacion.getValue()).stream().map(this::toDomain).collect(Collectors.toList());
    }

    private Asistencia toDomain(JpaAsistencia j) {
        return new Asistencia(new Id(j.getId()), new Id(j.getIdEstudiante()), new Id(j.getIdAsignacion()),
                j.getFecha(), j.getEstado(), j.getObservacion(), j.getFechaRegistro());
    }

    private JpaAsistencia toJpa(Asistencia d) {
        return new JpaAsistencia(d.id() != null ? d.id().getValue() : null, d.idEstudiante().getValue(),
                d.idAsignacion().getValue(), d.fecha(), d.estado(), d.observacion(), d.fechaRegistro());
    }
}
