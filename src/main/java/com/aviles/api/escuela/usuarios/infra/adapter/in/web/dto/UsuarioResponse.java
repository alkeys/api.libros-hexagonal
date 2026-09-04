package com.aviles.api.escuela.usuarios.infra.adapter.in.web.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de un usuario")
public record UsuarioResponse(
    @Schema(description = "ID del usuario")
    Long id,
    @Schema(description = "Username", example = "jperez")
    String username,
    @Schema(description = "Correo electrónico")
    String correo,
    @Schema(description = "Estado", example = "ACTIVO")
    String estado,
    @Schema(description = "Roles asignados al usuario", example = "[\"PROFESOR\"]")
    List<String> roles
) {}
