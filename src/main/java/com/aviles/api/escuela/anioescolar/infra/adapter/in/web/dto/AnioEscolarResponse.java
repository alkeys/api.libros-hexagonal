package com.aviles.api.escuela.anioescolar.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de un año escolar")
public record AnioEscolarResponse(
    @Schema(description = "ID del año escolar")
    Long id,
    @Schema(description = "Nombre", example = "Año Escolar 2026")
    String nombre,
    @Schema(description = "Año", example = "2026")
    Integer anio,
    @Schema(description = "Fecha de inicio")
    String fechaInicio,
    @Schema(description = "Fecha de fin")
    String fechaFin,
    @Schema(description = "Estado", example = "PLANIFICADO")
    String estado
) {}
