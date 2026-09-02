package com.aviles.api.escuela.profesores.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear un departamento")
public record DepartamentoRequest(
    @Schema(description = "Nombre del departamento", example = "Ciencias Naturales")
    String nombre,
    @Schema(description = "Descripción", example = "Departamento de ciencias naturales y exactas")
    String descripcion
) {}
