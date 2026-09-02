package com.aviles.api.escuela.horarios.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear una asignación de clase")
public record AsignacionClaseRequest(
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
    @Schema(description = "Observaciones")
    String observaciones
) {}
