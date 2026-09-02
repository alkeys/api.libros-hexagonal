package com.aviles.api.escuela.anioescolar.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear un año escolar")
public record AnioEscolarRequest(
    @Schema(description = "Nombre del año escolar", example = "Año Escolar 2026")
    String nombre,
    @Schema(description = "Año", example = "2026")
    Integer anio,
    @Schema(description = "Fecha de inicio (YYYY-MM-DD)", example = "2026-01-15")
    String fechaInicio,
    @Schema(description = "Fecha de fin (YYYY-MM-DD)", example = "2026-11-30")
    String fechaFin
) {}
