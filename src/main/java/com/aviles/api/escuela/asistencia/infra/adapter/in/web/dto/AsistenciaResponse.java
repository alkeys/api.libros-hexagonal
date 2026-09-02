package com.aviles.api.escuela.asistencia.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de asistencia")
public record AsistenciaResponse(
    @Schema(description = "ID de la asistencia")
    Long id,
    @Schema(description = "ID del estudiante")
    Long idEstudiante,
    @Schema(description = "ID de la asignación")
    Long idAsignacion,
    @Schema(description = "Fecha")
    String fecha,
    @Schema(description = "Estado", example = "PRESENTE")
    String estado,
    @Schema(description = "Observación")
    String observacion
) {}
