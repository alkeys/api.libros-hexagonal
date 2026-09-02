package com.aviles.api.escuela.configuracion.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa la configuración general del sistema escolar.
 * Contiene datos de la institución, escala de calificaciones y preferencias del sistema.
 */
public record Configuracion(
    Id id,
    String nombreInstitucion,
    String direccion,
    String telefono,
    String correo,
    String sitioWeb,
    String logoUrl,
    BigDecimal escalaMinima,
    BigDecimal escalaMaxima,
    BigDecimal notaAprobacion,
    String moneda,
    String zonaHoraria,
    OffsetDateTime fechaCreacion
) {
    public Configuracion {
        if (nombreInstitucion == null || nombreInstitucion.isBlank()) {
            throw new IllegalArgumentException("El nombre de la institución es obligatorio");
        }
    }

    /** Crea una nueva configuración con valores por defecto. */
    public static Configuracion nueva(String nombreInstitucion) {
        return new Configuracion(
            null,
            nombreInstitucion,
            null, null, null, null, null,
            BigDecimal.ZERO,
            new BigDecimal("10.00"),
            new BigDecimal("6.00"),
            "USD",
            "America/El_Salvador",
            OffsetDateTime.now()
        );
    }
}
