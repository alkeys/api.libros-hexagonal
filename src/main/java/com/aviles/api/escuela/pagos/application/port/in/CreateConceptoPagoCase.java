package com.aviles.api.escuela.pagos.application.port.in;

import com.aviles.api.escuela.pagos.domain.ConceptoPago;

public interface CreateConceptoPagoCase {
    ConceptoPago create(ConceptoPago concepto);
}
