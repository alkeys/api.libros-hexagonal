package com.aviles.api.escuela.pagos.application.port.out;

import com.aviles.api.escuela.pagos.domain.ConceptoPago;

public interface ConceptoPagoRepositoryPort {
    ConceptoPago save(ConceptoPago concepto);
    java.util.List<ConceptoPago> findAll();
}
