package com.aviles.api.escuela.pagos.infra.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.pagos.application.port.out.ConceptoPagoRepositoryPort;
import com.aviles.api.escuela.pagos.domain.ConceptoPago;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaConceptoPagoAdapter implements ConceptoPagoRepositoryPort {
    private final SpringDataConceptoPagoRepository repository;

    public JpaConceptoPagoAdapter(SpringDataConceptoPagoRepository repository) { this.repository = repository; }

    @Override
    public ConceptoPago save(ConceptoPago concepto) { return toDomain(repository.save(toJpa(concepto))); }

    @Override
    public List<ConceptoPago> findAll() { return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList()); }

    private ConceptoPago toDomain(JpaConceptoPago j) {
        return new ConceptoPago(new Id(j.getId()), j.getNombre(), j.getDescripcion(), j.getMonto(), j.getObligatorio(), j.getEstado());
    }

    private JpaConceptoPago toJpa(ConceptoPago d) {
        return new JpaConceptoPago(d.id() != null ? d.id().getValue() : null, d.nombre(), d.descripcion(), d.monto(), d.obligatorio(), d.estado());
    }
}
