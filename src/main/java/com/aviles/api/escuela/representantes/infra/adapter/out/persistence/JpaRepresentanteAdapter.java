package com.aviles.api.escuela.representantes.infra.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.representantes.application.port.out.RepresentanteRepositoryPort;
import com.aviles.api.escuela.representantes.domain.Representante;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaRepresentanteAdapter implements RepresentanteRepositoryPort {
    private final SpringDataRepresentanteRepository repository;
    public JpaRepresentanteAdapter(SpringDataRepresentanteRepository repository) { this.repository = repository; }

    @Override
    public Representante save(Representante r) { return toDomain(repository.save(toJpa(r))); }
    @Override
    public List<Representante> findAll() { return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList()); }
    @Override
    public Optional<Representante> findById(Long id) { return repository.findById(id).map(this::toDomain); }
    @Override
    public void deleteById(Long id) { repository.deleteById(id); }

    private Representante toDomain(JpaRepresentante j) { return new Representante(new Id(j.getId()), j.getNombres(), j.getApellidos(), j.getDui(), j.getCorreoElectronico(), j.getTelefono(), j.getTelefonoAlternativo(), j.getDireccion(), j.getOcupacion(), j.getEstado()); }
    private JpaRepresentante toJpa(Representante d) { return new JpaRepresentante(d.id() != null ? d.id().getValue() : null, d.nombres(), d.apellidos(), d.dui(), d.correoElectronico(), d.telefono(), d.telefonoAlternativo(), d.direccion(), d.ocupacion(), d.estado()); }
}
