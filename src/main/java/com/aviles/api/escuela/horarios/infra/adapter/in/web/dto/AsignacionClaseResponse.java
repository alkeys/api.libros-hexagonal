package com.aviles.api.escuela.horarios.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de una asignación de clase")
public record AsignacionClaseResponse(
    @Schema(description = "ID de la asignación")
    Long id,
    @Schema(description = "ID del grupo")
    Long idGrupo,
    @Schema(description = "ID de la materia")
    Long idMateria,
    @Schema(description = "ID del profesor")
    Long idProfesor,
    @Schema(description = "ID del horario")
    Long idHorario,
    @Schema(description = "ID del aula")
    Long idAula,
    @Schema(description = "Modalidad", example = "PRESENCIAL")
    String modalidad,
    @Schema(description = "Estado", example = "ACTIVA")
    String estado,
    @Schema(description = "Observaciones")
    String observaciones
) {}
