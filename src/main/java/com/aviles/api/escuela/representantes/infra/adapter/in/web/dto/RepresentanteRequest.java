package com.aviles.api.escuela.representantes.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear un representante")
public record RepresentanteRequest(
    @Schema(description = "Nombres", example = "Carlos Alberto")
    String nombres,
    @Schema(description = "Apellidos", example = "Pérez López")
    String apellidos,
    @Schema(description = "DUI", example = "01234567-8")
    String dui,
    @Schema(description = "Correo electrónico")
    String correoElectronico,
    @Schema(description = "Teléfono", example = "+503 7777-8888")
    String telefono
) {}
