package com.aviles.api.escuela.grupos.infra.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.aviles.api.escuela.grupos.application.port.out.GrupoRepositoryPort;
import com.aviles.api.escuela.grupos.domain.Grupo;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaGrupoAdapter implements GrupoRepositoryPort {

    private final SpringDataGrupoRepository repository;

    public JpaGrupoAdapter(SpringDataGrupoRepository repository) { this.repository = repository; }

    @Override
    public Grupo save(Grupo grupo) {
        return toDomain(repository.save(toJpa(grupo)));
    }

    @Override
    public List<Grupo> findAll() {
        return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Grupo> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Grupo toDomain(JpaGrupo j) {
        return new Grupo(new Id(j.getId()), new Id(j.getIdGrado()), new Id(j.getIdSeccion()),
                new Id(j.getIdAnioEscolar()), j.getNombre(), j.getCapacidad(), j.getTurno(), j.getEstado());
    }

    private JpaGrupo toJpa(Grupo d) {
        return new JpaGrupo(d.id() != null ? d.id().getValue() : null, d.idGrado().getValue(),
                d.idSeccion().getValue(), d.idAnioEscolar().getValue(), d.nombre(), d.capacidad(), d.turno(), d.estado());
    }
}
