package com.aviles.api.escuela.niveles.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear un nivel educativo")
public record NivelRequest(
    @Schema(description = "Nombre del nivel", example = "Educación Básica")
    String nombre,
    @Schema(description = "Descripción", example = "Educación básica de 1° a 9° grado")
    String descripcion
) {}
