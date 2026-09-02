package com.aviles.api.escuela.configuracion.infra.adapter.in.web.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de respuesta para la configuración del sistema escolar.
 */
@Schema(description = "Respuesta de la configuración del sistema escolar")
public record ConfiguracionResponse(
    @Schema(description = "ID de la configuración", example = "1")
    Long id,
    @Schema(description = "Nombre de la institución", example = "Institución Educativa")
    String nombreInstitucion,
    @Schema(description = "Dirección de la institución", example = "San Salvador, El Salvador")
    String direccion,
    @Schema(description = "Teléfono de contacto", example = "+503 2222-3333")
    String telefono,
    @Schema(description = "Correo electrónico", example = "info@escuela.edu.sv")
    String correo,
    @Schema(description = "Sitio web", example = "https://www.escuela.edu.sv")
    String sitioWeb,
    @Schema(description = "URL del logo", example = "https://cdn.escuela.edu.sv/logo.png")
    String logoUrl,
    @Schema(description = "Escala mínima de calificación", example = "0.00")
    BigDecimal escalaMinima,
    @Schema(description = "Escala máxima de calificación", example = "10.00")
    BigDecimal escalaMaxima,
    @Schema(description = "Nota mínima para aprobar", example = "6.00")
    BigDecimal notaAprobacion,
    @Schema(description = "Moneda utilizada", example = "USD")
    String moneda,
    @Schema(description = "Zona horaria", example = "America/El_Salvador")
    String zonaHoraria
) {}
