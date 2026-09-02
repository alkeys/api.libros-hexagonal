package com.aviles.api.escuela.evaluaciones.infra.adapter.in.web.dto;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear una evaluación")
public record EvaluacionRequest(
    @Schema(description = "ID de la asignación de clase")
    Long idAsignacion,
    @Schema(description = "ID del período académico")
    Long idPeriodo,
    @Schema(description = "ID del tipo de evaluación")
    Long idTipoEvaluacion,
    @Schema(description = "Nombre", example = "Primer Examen de Matemáticas")
    String nombre,
    @Schema(description = "Descripción")
    String descripcion,
    @Schema(description = "Fecha (YYYY-MM-DD)", example = "2026-03-15")
    String fechaEvaluacion,
    @Schema(description = "Porcentaje", example = "40")
    BigDecimal porcentaje,
    @Schema(description = "Nota máxima", example = "10")
    BigDecimal notaMaxima
) {}
