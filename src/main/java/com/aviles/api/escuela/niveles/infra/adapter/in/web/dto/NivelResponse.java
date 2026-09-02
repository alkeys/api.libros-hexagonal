package com.aviles.api.escuela.niveles.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de un nivel educativo")
public record NivelResponse(
    @Schema(description = "ID del nivel")
    Long id,
    @Schema(description = "Nombre", example = "Educación Básica")
    String nombre,
    @Schema(description = "Descripción")
    String descripcion
) {}
