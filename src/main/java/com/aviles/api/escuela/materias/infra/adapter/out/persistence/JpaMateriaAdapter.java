package com.aviles.api.escuela.materias.infra.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.materias.application.port.out.MateriaRepositoryPort;
import com.aviles.api.escuela.materias.domain.Materia;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaMateriaAdapter implements MateriaRepositoryPort {
    private final SpringDataMateriaRepository repository;

    public JpaMateriaAdapter(SpringDataMateriaRepository repository) { this.repository = repository; }

    @Override
    public Materia save(Materia materia) { return toDomain(repository.save(toJpa(materia))); }

    @Override
    public List<Materia> findAll() { return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList()); }

    private Materia toDomain(JpaMateria j) {
        return new Materia(new Id(j.getId()), j.getCodigoMateria(), j.getNombreMateria(), j.getDescripcion(),
                j.getHorasSemanales(), j.getTipo(), j.getEstado());
    }

    private JpaMateria toJpa(Materia d) {
        return new JpaMateria(d.id() != null ? d.id().getValue() : null, d.codigoMateria(), d.nombreMateria(),
                d.descripcion(), d.horasSemanales(), d.tipo(), d.estado());
    }
}
