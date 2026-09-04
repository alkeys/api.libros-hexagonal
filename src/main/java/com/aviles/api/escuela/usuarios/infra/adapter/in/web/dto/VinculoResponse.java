package com.aviles.api.escuela.usuarios.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vínculo de un usuario con profesor o estudiante")
public record VinculoResponse(
    @Schema(description = "ID del profesor vinculado (null si no es profesor)")
    Long idProfesor,
    @Schema(description = "ID del estudiante vinculado (null si no es estudiante)")
    Long idEstudiante
) {}