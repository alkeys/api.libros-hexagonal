package com.aviles.api.escuela.estudiantes.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear una matrícula")
public record MatriculaRequest(
    @Schema(description = "ID del estudiante")
    Long idEstudiante,
    @Schema(description = "ID del grupo")
    Long idGrupo,
    @Schema(description = "Tipo de matrícula", example = "NUEVO")
    String tipoMatricula,
    @Schema(description = "Observaciones")
    String observaciones
) {}
