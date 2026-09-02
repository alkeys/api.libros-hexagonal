package com.aviles.api.escuela.evaluaciones.infra.adapter.in.web.dto;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de una calificación")
public record CalificacionResponse(
    @Schema(description = "ID de la calificación")
    Long id,
    @Schema(description = "ID de la evaluación")
    Long idEvaluacion,
    @Schema(description = "ID del estudiante")
    Long idEstudiante,
    @Schema(description = "Nota obtenida")
    BigDecimal notaObtenida,
    @Schema(description = "Observación")
    String observacion,
    @Schema(description = "Fecha de registro")
    String fechaRegistro
) {}
