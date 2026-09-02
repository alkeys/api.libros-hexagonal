package com.aviles.api.escuela.comunicaciones.infra.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.comunicaciones.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaComunicacionesAdapter {

    private final SpringDataAvisoRepository avisoRepo;
    private final SpringDataEventoRepository eventoRepo;

    public JpaComunicacionesAdapter(SpringDataAvisoRepository avisoRepo, SpringDataEventoRepository eventoRepo) {
        this.avisoRepo = avisoRepo;
        this.eventoRepo = eventoRepo;
    }

    public Aviso saveAviso(Aviso a) { return toDomainAviso(avisoRepo.save(toJpaAviso(a))); }
    public List<Aviso> findAllAvisos() { return avisoRepo.findAll().stream().map(this::toDomainAviso).collect(Collectors.toList()); }
    public Evento saveEvento(Evento e) { return toDomainEvento(eventoRepo.save(toJpaEvento(e))); }
    public List<Evento> findAllEventos() { return eventoRepo.findAll().stream().map(this::toDomainEvento).collect(Collectors.toList()); }

    private Aviso toDomainAviso(JpaAviso j) { return new Aviso(new Id(j.getId()), j.getTitulo(), j.getContenido(), j.getFechaPublicacion(), j.getFechaExpiracion(), j.getPrioridad(), j.getEstado(), j.getIdUsuarioAutor() != null ? new Id(j.getIdUsuarioAutor()) : null); }
    private JpaAviso toJpaAviso(Aviso d) { return new JpaAviso(d.id() != null ? d.id().getValue() : null, d.titulo(), d.contenido(), d.fechaPublicacion(), d.fechaExpiracion(), d.prioridad(), d.estado(), d.idUsuarioAutor() != null ? d.idUsuarioAutor().getValue() : null); }
    private Evento toDomainEvento(JpaEvento j) { return new Evento(new Id(j.getId()), j.getTitulo(), j.getDescripcion(), j.getFechaInicio(), j.getFechaFin(), j.getUbicacion(), j.getTipo(), j.getEstado()); }
    private JpaEvento toJpaEvento(Evento d) { return new JpaEvento(d.id() != null ? d.id().getValue() : null, d.titulo(), d.descripcion(), d.fechaInicio(), d.fechaFin(), d.ubicacion(), d.tipo(), d.estado()); }
}
