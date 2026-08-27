package com.aviles.api.libros.usuarios.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud de la API para crear un usuario")
public record UsuariosRequest(
    @Schema(description = "Nombre del usuario", example = "Juan Pérez")
    String nombre_usuario, 
    @Schema(description = "Email del usuario", example = "juan.perez@example.com")
    String correo, 
    @Schema(description = "Contraseña del usuario", example = "hashed_password")
    String contrasena_hash) {
    
}
