package com.aviles.api.escuela.asistencia.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para registrar asistencia")
public record AsistenciaRequest(
    @Schema(description = "ID del estudiante")
    Long idEstudiante,
    @Schema(description = "ID de la asignación de clase")
    Long idAsignacion,
    @Schema(description = "Fecha (YYYY-MM-DD)", example = "2026-03-15")
    String fecha,
    @Schema(description = "Estado", example = "PRESENTE")
    String estado,
    @Schema(description = "Observación")
    String observacion
) {}
