package com.aviles.api.escuela.anioescolar.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de un período académico")
public record PeriodoResponse(
    @Schema(description = "ID del período")
    Long id,
    @Schema(description = "ID del año escolar")
    Long idAnioEscolar,
    @Schema(description = "Nombre", example = "Primer Trimestre")
    String nombre,
    @Schema(description = "Número del período", example = "1")
    Integer numeroPeriodo,
    @Schema(description = "Fecha de inicio")
    String fechaInicio,
    @Schema(description = "Fecha de fin")
    String fechaFin,
    @Schema(description = "Estado")
    String estado
) {}
