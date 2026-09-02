package com.aviles.api.escuela.niveles.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear una sección")
public record SeccionRequest(
    @Schema(description = "Nombre de la sección", example = "A")
    String nombre,
    @Schema(description = "Descripción", example = "Sección A")
    String descripcion
) {}
