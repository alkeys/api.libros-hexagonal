package com.aviles.api.escuela.materias.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear una materia")
public record MateriaRequest(
    @Schema(description = "Código de la materia", example = "MAT-001")
    String codigoMateria,
    @Schema(description = "Nombre", example = "Matemáticas")
    String nombreMateria,
    @Schema(description = "Descripción")
    String descripcion,
    @Schema(description = "Horas semanales", example = "5")
    Integer horasSemanales,
    @Schema(description = "Tipo", example = "OBLIGATORIA")
    String tipo
) {}
