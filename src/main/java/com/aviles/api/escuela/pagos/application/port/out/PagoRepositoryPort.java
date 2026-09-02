package com.aviles.api.escuela.pagos.application.port.out;

import com.aviles.api.escuela.pagos.domain.Pago;

public interface PagoRepositoryPort {
    Pago save(Pago pago);
    java.util.List<Pago> findAll();
}
