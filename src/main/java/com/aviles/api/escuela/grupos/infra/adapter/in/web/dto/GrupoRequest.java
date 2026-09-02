package com.aviles.api.escuela.grupos.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear un grupo escolar")
public record GrupoRequest(
    @Schema(description = "ID del grado")
    Long idGrado,
    @Schema(description = "ID de la sección")
    Long idSeccion,
    @Schema(description = "ID del año escolar")
    Long idAnioEscolar,
    @Schema(description = "Nombre del grupo", example = "1° Grado Sección A")
    String nombre,
    @Schema(description = "Capacidad máxima", example = "40")
    Integer capacidad,
    @Schema(description = "Turno", example = "MATUTINO")
    String turno
) {}
