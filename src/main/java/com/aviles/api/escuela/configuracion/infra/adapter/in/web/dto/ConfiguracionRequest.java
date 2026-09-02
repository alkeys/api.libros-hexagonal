package com.aviles.api.escuela.configuracion.infra.adapter.in.web.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de solicitud para actualizar la configuración del sistema escolar.
 */
@Schema(description = "Solicitud para actualizar la configuración del sistema")
public record ConfiguracionRequest(
    @Schema(description = "Nombre de la institución", example = "Institución Educativa Nueva")
    String nombreInstitucion,
    @Schema(description = "Dirección", example = "San Salvador, El Salvador")
    String direccion,
    @Schema(description = "Teléfono", example = "+503 2222-3333")
    String telefono,
    @Schema(description = "Correo electrónico", example = "info@escuela.edu.sv")
    String correo,
    @Schema(description = "Sitio web", example = "https://www.escuela.edu.sv")
    String sitioWeb,
    @Schema(description = "URL del logo", example = "https://cdn.escuela.edu.sv/logo.png")
    String logoUrl,
    @Schema(description = "Escala mínima", example = "0.00")
    BigDecimal escalaMinima,
    @Schema(description = "Escala máxima", example = "10.00")
    BigDecimal escalaMaxima,
    @Schema(description = "Nota de aprobación", example = "6.00")
    BigDecimal notaAprobacion,
    @Schema(description = "Moneda", example = "USD")
    String moneda,
    @Schema(description = "Zona horaria", example = "America/El_Salvador")
    String zonaHoraria
) {}
