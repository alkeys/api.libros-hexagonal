package com.aviles.api.escuela.profesores.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de un departamento")
public record DepartamentoResponse(
    @Schema(description = "ID del departamento")
    Long id,
    @Schema(description = "Nombre", example = "Ciencias Naturales")
    String nombre,
    @Schema(description = "Descripción")
    String descripcion
) {}
