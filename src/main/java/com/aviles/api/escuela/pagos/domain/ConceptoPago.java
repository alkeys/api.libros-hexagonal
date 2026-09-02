package com.aviles.api.escuela.pagos.domain;

import java.math.BigDecimal;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un concepto de pago (matrícula, materiales, etc.).
 */
public record ConceptoPago(
    Id id,
    String nombre,
    String descripcion,
    BigDecimal monto,
    Boolean obligatorio,
    String estado
) {
    public ConceptoPago {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
        if (monto == null) throw new IllegalArgumentException("El monto es obligatorio");
    }

    public static ConceptoPago nuevo(String nombre, String descripcion, BigDecimal monto, Boolean obligatorio) {
        return new ConceptoPago(null, nombre, descripcion, monto, obligatorio, "ACTIVO");
    }
}
