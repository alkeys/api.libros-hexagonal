package com.aviles.api.escuela.usuarios.infra.adapter.in.web.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de inicio de sesión")
public record LoginResponse(
    @Schema(description = "ID del usuario")
    Long id,
    @Schema(description = "Username", example = "jperez")
    String username,
    @Schema(description = "Correo electrónico")
    String correo,
    @Schema(description = "Estado", example = "ACTIVO")
    String estado,
    @Schema(description = "Token JWT para autenticar las siguientes peticiones")
    String token,
    @Schema(description = "Roles del usuario", example = "[\"ADMIN\"]")
    List<String> roles,
    @Schema(description = "ID del profesor vinculado (si el usuario es un profesor)")
    Long idProfesor,
    @Schema(description = "ID del estudiante vinculado (si el usuario es un estudiante)")
    Long idEstudiante
) {}