package com.aviles.api.escuela.usuarios.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear un usuario")
public record UsuarioRequest(
    @Schema(description = "Username", example = "jperez")
    String username,
    @Schema(description = "Contraseña en texto plano (se hashea con BCrypt en el servidor)", example = "s3cr3t-password")
    String password,
    @Schema(description = "Correo electrónico", example = "jperez@escuela.edu.sv")
    String correo
) {}
