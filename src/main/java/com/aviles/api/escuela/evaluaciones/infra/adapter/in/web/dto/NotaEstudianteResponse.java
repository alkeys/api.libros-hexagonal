package com.aviles.api.escuela.evaluaciones.infra.adapter.in.web.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Nota de un estudiante en una evaluación (portal del alumno)")
public record NotaEstudianteResponse(
    @Schema(description = "ID de la calificación")
    Long id,
    @Schema(description = "ID de la evaluación")
    Long idEvaluacion,
    @Schema(description = "Nombre de la evaluación")
    String nombreEvaluacion,
    @Schema(description = "Descripción de la evaluación")
    String descripcionEvaluacion,
    @Schema(description = "Fecha de la evaluación")
    String fechaEvaluacion,
    @Schema(description = "Porcentaje que vale la evaluación")
    BigDecimal porcentaje,
    @Schema(description = "Nota máxima de la evaluación")
    BigDecimal notaMaxima,
    @Schema(description = "ID de la asignación de clase")
    Long idAsignacion,
    @Schema(description = "Nota obtenida")
    BigDecimal notaObtenida,
    @Schema(description = "Observación del profesor")
    String observacion,
    @Schema(description = "Estado de la evaluación")
    String estado,
    @Schema(description = "Fecha de registro de la nota")
    String fechaRegistro
) {}