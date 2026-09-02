package com.aviles.api.escuela.pagos.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un pago realizado por un estudiante.
 */
public record Pago(
    Id id,
    Id idCobro,
    OffsetDateTime fechaPago,
    BigDecimal monto,
    String metodoPago,
    String referencia,
    String observacion,
    Id idUsuario
) {
    public Pago {
        if (idCobro == null) throw new IllegalArgumentException("El cobro es obligatorio");
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("El monto debe ser mayor a 0");
        if (metodoPago == null || metodoPago.isBlank()) throw new IllegalArgumentException("El método de pago es obligatorio");
    }

    public static Pago nuevo(Id idCobro, BigDecimal monto, String metodoPago, String referencia) {
        return new Pago(null, idCobro, OffsetDateTime.now(), monto, metodoPago, referencia, null, null);
    }
}
