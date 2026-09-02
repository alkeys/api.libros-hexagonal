package com.aviles.api.escuela.horarios.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de un aula")
public record AulaResponse(
    @Schema(description = "ID del aula")
    Long id,
    @Schema(description = "Código", example = "AULA-01")
    String codigo,
    @Schema(description = "Nombre", example = "Salón de Matemáticas")
    String nombre,
    @Schema(description = "Edificio")
    String edificio,
    @Schema(description = "Piso")
    String piso,
    @Schema(description = "Capacidad", example = "40")
    Integer capacidad,
    @Schema(description = "Tipo", example = "AULA")
    String tipo,
    @Schema(description = "Estado", example = "DISPONIBLE")
    String estado
) {}
