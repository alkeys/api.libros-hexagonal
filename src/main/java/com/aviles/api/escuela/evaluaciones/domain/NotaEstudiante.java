package com.aviles.api.escuela.evaluaciones.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Vista de una calificación de un estudiante enriquecida con los datos
 * de su evaluación, para mostrar en el portal del alumno.
 */
public record NotaEstudiante(
    Id idCalificacion,
    Id idEvaluacion,
    String nombreEvaluacion,
    String descripcionEvaluacion,
    LocalDate fechaEvaluacion,
    BigDecimal porcentaje,
    BigDecimal notaMaxima,
    Id idAsignacion,
    Id idPeriodo,
    BigDecimal notaObtenida,
    String observacion,
    String estadoEvaluacion,
    OffsetDateTime fechaRegistro
) {}