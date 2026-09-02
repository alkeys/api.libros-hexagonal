package com.aviles.api.escuela.anioescolar.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear un período académico")
public record PeriodoRequest(
    @Schema(description = "ID del año escolar")
    Long idAnioEscolar,
    @Schema(description = "Nombre del período", example = "Primer Trimestre")
    String nombre,
    @Schema(description = "Número del período", example = "1")
    Integer numeroPeriodo,
    @Schema(description = "Fecha de inicio (YYYY-MM-DD)")
    String fechaInicio,
    @Schema(description = "Fecha de fin (YYYY-MM-DD)")
    String fechaFin
) {}
