package com.aviles.api.escuela.pagos.application.service;

import org.springframework.stereotype.Service;
import com.aviles.api.escuela.pagos.application.port.in.CreateConceptoPagoCase;
import com.aviles.api.escuela.pagos.application.port.out.ConceptoPagoRepositoryPort;
import com.aviles.api.escuela.pagos.domain.ConceptoPago;

@Service
public class PagoService implements CreateConceptoPagoCase {
    private final ConceptoPagoRepositoryPort conceptoPagoRepositoryPort;

    public PagoService(ConceptoPagoRepositoryPort conceptoPagoRepositoryPort) {
        this.conceptoPagoRepositoryPort = conceptoPagoRepositoryPort;
    }

    @Override
    public ConceptoPago create(ConceptoPago concepto) { return conceptoPagoRepositoryPort.save(concepto); }
}
