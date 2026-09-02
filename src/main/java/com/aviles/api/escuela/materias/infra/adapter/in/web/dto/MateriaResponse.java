package com.aviles.api.escuela.materias.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de una materia")
public record MateriaResponse(
    @Schema(description = "ID de la materia")
    Long id,
    @Schema(description = "Código", example = "MAT-001")
    String codigoMateria,
    @Schema(description = "Nombre", example = "Matemáticas")
    String nombreMateria,
    @Schema(description = "Descripción")
    String descripcion,
    @Schema(description = "Horas semanales", example = "5")
    Integer horasSemanales,
    @Schema(description = "Tipo", example = "OBLIGATORIA")
    String tipo,
    @Schema(description = "Estado", example = "ACTIVA")
    String estado
) {}
