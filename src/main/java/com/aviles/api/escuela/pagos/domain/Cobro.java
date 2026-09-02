package com.aviles.api.escuela.pagos.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un cobro a un estudiante.
 */
public record Cobro(
    Id id,
    Id idEstudiante,
    Id idConcepto,
    Id idAnioEscolar,
    LocalDate fechaVencimiento,
    BigDecimal monto,
    String estado,
    String observacion
) {
    public Cobro {
        if (idEstudiante == null) throw new IllegalArgumentException("El estudiante es obligatorio");
        if (idConcepto == null) throw new IllegalArgumentException("El concepto es obligatorio");
        if (idAnioEscolar == null) throw new IllegalArgumentException("El año escolar es obligatorio");
    }

    public static Cobro nuevo(Id idEstudiante, Id idConcepto, Id idAnioEscolar, BigDecimal monto, LocalDate fechaVencimiento) {
        return new Cobro(null, idEstudiante, idConcepto, idAnioEscolar, fechaVencimiento, monto, "PENDIENTE", null);
    }
}
