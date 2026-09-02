package com.aviles.api.escuela.profesores.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de un profesor")
public record ProfesorResponse(
    @Schema(description = "ID del profesor")
    Long id,
    @Schema(description = "Código", example = "PRO-001")
    String codigoProfesor,
    @Schema(description = "Nombres")
    String nombres,
    @Schema(description = "Apellidos")
    String apellidos,
    @Schema(description = "DUI")
    String dui,
    @Schema(description = "Especialidad")
    String especialidad,
    @Schema(description = "Correo electrónico")
    String correoElectronico,
    @Schema(description = "Teléfono")
    String telefono,
    @Schema(description = "Estado", example = "ACTIVO")
    String estado
) {}
