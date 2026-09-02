package com.aviles.api.escuela.niveles.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de un grado")
public record GradoResponse(
    @Schema(description = "ID del grado")
    Long id,
    @Schema(description = "ID del nivel educativo")
    Long idNivel,
    @Schema(description = "Nombre del grado", example = "Primer Grado")
    String nombreGrado,
    @Schema(description = "Descripción")
    String descripcion
) {}
