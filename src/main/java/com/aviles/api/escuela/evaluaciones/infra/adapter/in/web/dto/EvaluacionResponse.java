package com.aviles.api.escuela.evaluaciones.infra.adapter.in.web.dto;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de una evaluación")
public record EvaluacionResponse(
    @Schema(description = "ID de la evaluación")
    Long id,
    @Schema(description = "ID de la asignación")
    Long idAsignacion,
    @Schema(description = "ID del período")
    Long idPeriodo,
    @Schema(description = "ID del tipo de evaluación")
    Long idTipoEvaluacion,
    @Schema(description = "Nombre")
    String nombre,
    @Schema(description = "Descripción")
    String descripcion,
    @Schema(description = "Fecha")
    String fechaEvaluacion,
    @Schema(description = "Porcentaje")
    BigDecimal porcentaje,
    @Schema(description = "Nota máxima")
    BigDecimal notaMaxima,
    @Schema(description = "Estado", example = "PROGRAMADA")
    String estado
) {}
