package com.aviles.api.escuela.usuarios.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de un rol del sistema")
public record RolResponse(
    @Schema(description = "ID del rol")
    Long id,
    @Schema(description = "Nombre del rol", example = "PROFESOR")
    String nombre,
    @Schema(description = "Descripción del rol")
    String descripcion
) {}
