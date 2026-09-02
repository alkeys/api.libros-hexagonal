package com.aviles.api.escuela.representantes.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de un representante")
public record RepresentanteResponse(
    @Schema(description = "ID del representante")
    Long id,
    @Schema(description = "Nombres")
    String nombres,
    @Schema(description = "Apellidos")
    String apellidos,
    @Schema(description = "DUI")
    String dui,
    @Schema(description = "Correo electrónico")
    String correoElectronico,
    @Schema(description = "Teléfono")
    String telefono,
    @Schema(description = "Estado")
    String estado
) {}
