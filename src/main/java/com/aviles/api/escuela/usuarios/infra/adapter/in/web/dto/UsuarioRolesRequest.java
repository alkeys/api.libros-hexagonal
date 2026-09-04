package com.aviles.api.escuela.usuarios.infra.adapter.in.web.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Roles a asignar a un usuario (reemplaza los actuales)")
public record UsuarioRolesRequest(
    @Schema(description = "Nombres de roles", example = "[\"PROFESOR\"]")
    List<String> roles
) {}
