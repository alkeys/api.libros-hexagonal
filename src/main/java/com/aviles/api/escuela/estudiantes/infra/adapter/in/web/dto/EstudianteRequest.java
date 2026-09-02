package com.aviles.api.escuela.estudiantes.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear un estudiante")
public record EstudianteRequest(
    @Schema(description = "Código del estudiante", example = "EST-001")
    String codigoEstudiante,
    @Schema(description = "Nombres", example = "Juan Carlos")
    String nombres,
    @Schema(description = "Apellidos", example = "Pérez López")
    String apellidos,
    @Schema(description = "Fecha de nacimiento (YYYY-MM-DD)", example = "2010-05-15")
    String fechaNacimiento,
    @Schema(description = "Género", example = "MASCULINO")
    String genero,
    @Schema(description = "Nacionalidad", example = "Salvadoreño")
    String nacionalidad,
    @Schema(description = "DUI", example = "01234567-8")
    String dui,
    @Schema(description = "NIE", example = "123456789")
    String nie,
    @Schema(description = "Correo electrónico", example = "juan.perez@escuela.edu.sv")
    String correoElectronico,
    @Schema(description = "Teléfono", example = "+503 7777-8888")
    String telefono,
    @Schema(description = "Dirección", example = "San Salvador")
    String direccion,
    @Schema(description = "Fecha de ingreso (YYYY-MM-DD)")
    String fechaIngreso
) {}
