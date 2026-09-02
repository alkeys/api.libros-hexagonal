package com.aviles.api.escuela.estudiantes.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de un estudiante")
public record EstudianteResponse(
    @Schema(description = "ID del estudiante")
    Long id,
    @Schema(description = "Código", example = "EST-001")
    String codigoEstudiante,
    @Schema(description = "Nombres", example = "Juan Carlos")
    String nombres,
    @Schema(description = "Apellidos", example = "Pérez López")
    String apellidos,
    @Schema(description = "Fecha de nacimiento")
    String fechaNacimiento,
    @Schema(description = "Género", example = "MASCULINO")
    String genero,
    @Schema(description = "Nacionalidad")
    String nacionalidad,
    @Schema(description = "DUI")
    String dui,
    @Schema(description = "NIE")
    String nie,
    @Schema(description = "Correo electrónico")
    String correoElectronico,
    @Schema(description = "Teléfono")
    String telefono,
    @Schema(description = "Dirección")
    String direccion,
    @Schema(description = "Fecha de ingreso")
    String fechaIngreso,
    @Schema(description = "Estado", example = "ACTIVO")
    String estado,
    @Schema(description = "Foto URL")
    String fotoUrl
) {}
