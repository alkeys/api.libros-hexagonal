package com.aviles.api.escuela.estudiantes.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de una matrícula")
public record MatriculaResponse(
    @Schema(description = "ID de la matrícula")
    Long id,
    @Schema(description = "ID del estudiante")
    Long idEstudiante,
    @Schema(description = "ID del grupo")
    Long idGrupo,
    @Schema(description = "Fecha de matrícula")
    String fechaMatricula,
    @Schema(description = "Tipo", example = "NUEVO")
    String tipoMatricula,
    @Schema(description = "Estado", example = "PENDIENTE")
    String estado,
    @Schema(description = "Observaciones")
    String observaciones
) {}
