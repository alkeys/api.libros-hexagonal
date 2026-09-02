package com.aviles.api.escuela.evaluaciones.infra.adapter.in.web.dto;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para registrar una calificación")
public record CalificacionRequest(
    @Schema(description = "ID de la evaluación")
    Long idEvaluacion,
    @Schema(description = "ID del estudiante")
    Long idEstudiante,
    @Schema(description = "Nota obtenida", example = "8.5")
    BigDecimal notaObtenida,
    @Schema(description = "Observación")
    String observacion
) {}
