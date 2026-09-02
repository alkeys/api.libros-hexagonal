package com.aviles.api.escuela.actividades.infra.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.actividades.application.port.out.ActividadRepositoryPort;
import com.aviles.api.escuela.actividades.domain.Actividad;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaActividadAdapter implements ActividadRepositoryPort {

    private final SpringDataActividadRepository repository;

    public JpaActividadAdapter(SpringDataActividadRepository repository) { this.repository = repository; }

    @Override
    public Actividad save(Actividad a) { return toDomain(repository.save(toJpa(a))); }
    @Override
    public List<Actividad> findAll() { return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList()); }

    private Actividad toDomain(JpaActividad j) {
        return new Actividad(new Id(j.getId()), new Id(j.getIdAsignacion()), new Id(j.getIdPeriodo()), j.getTitulo(), j.getDescripcion(), j.getFechaPublicacion(), j.getFechaEntrega(), j.getPorcentaje(), j.getEstado());
    }
    private JpaActividad toJpa(Actividad d) {
        return new JpaActividad(d.id() != null ? d.id().getValue() : null, d.idAsignacion().getValue(), d.idPeriodo().getValue(), d.titulo(), d.descripcion(), d.fechaPublicacion(), d.fechaEntrega(), d.porcentaje(), d.estado());
    }
}
