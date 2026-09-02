package com.aviles.api.escuela.niveles.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear un grado")
public record GradoRequest(
    @Schema(description = "ID del nivel educativo")
    Long idNivel,
    @Schema(description = "Nombre del grado", example = "Primer Grado")
    String nombreGrado,
    @Schema(description = "Descripción")
    String descripcion
) {}
