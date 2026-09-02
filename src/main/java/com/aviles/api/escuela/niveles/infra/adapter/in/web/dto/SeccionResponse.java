package com.aviles.api.escuela.niveles.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de una sección")
public record SeccionResponse(
    @Schema(description = "ID de la sección")
    Long id,
    @Schema(description = "Nombre", example = "A")
    String nombre,
    @Schema(description = "Descripción")
    String descripcion
) {}
