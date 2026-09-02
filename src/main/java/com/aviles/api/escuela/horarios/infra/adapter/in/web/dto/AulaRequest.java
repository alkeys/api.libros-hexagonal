package com.aviles.api.escuela.horarios.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear un aula")
public record AulaRequest(
    @Schema(description = "Código del aula", example = "AULA-01")
    String codigo,
    @Schema(description = "Nombre", example = "Salón de Matemáticas")
    String nombre,
    @Schema(description = "Edificio", example = "Edificio A")
    String edificio,
    @Schema(description = "Piso", example = "1")
    String piso,
    @Schema(description = "Capacidad", example = "40")
    Integer capacidad,
    @Schema(description = "Tipo", example = "AULA")
    String tipo
) {}
