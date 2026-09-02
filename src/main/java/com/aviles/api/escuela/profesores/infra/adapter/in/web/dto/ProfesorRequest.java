package com.aviles.api.escuela.profesores.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear un profesor")
public record ProfesorRequest(
    @Schema(description = "Código del profesor", example = "PRO-001")
    String codigoProfesor,
    @Schema(description = "Nombres", example = "María Fernanda")
    String nombres,
    @Schema(description = "Apellidos", example = "García López")
    String apellidos,
    @Schema(description = "DUI", example = "01234567-8")
    String dui,
    @Schema(description = "Especialidad", example = "Matemáticas")
    String especialidad,
    @Schema(description = "Correo electrónico", example = "maria.garcia@escuela.edu.sv")
    String correoElectronico,
    @Schema(description = "Teléfono", example = "+503 7777-8888")
    String telefono,
    @Schema(description = "Dirección", example = "San Salvador")
    String direccion
) {}
