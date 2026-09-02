package com.aviles.api.escuela.usuarios.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear un usuario")
public record UsuarioRequest(
    @Schema(description = "Username", example = "jperez")
    String username,
    @Schema(description = "Password (hash)", example = "hashed_password")
    String passwordHash,
    @Schema(description = "Correo electrónico", example = "jperez@escuela.edu.sv")
    String correo
) {}
