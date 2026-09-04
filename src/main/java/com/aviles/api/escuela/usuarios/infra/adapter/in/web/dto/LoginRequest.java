package com.aviles.api.escuela.usuarios.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud de inicio de sesión")
public record LoginRequest(
    @Schema(description = "Nombre de usuario", example = "jperez")
    String username,
    @Schema(description = "Contraseña", example = "123456")
    String password
) {}