package com.aviles.api.libros.usuarios.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de la API para un usuario")
public record UsuariosReponse(
    @Schema(description = "Identificador único del usuario", example = "123e4567-e89b-12d3-a456-426614174000")
    String id, 
    @Schema(description = "Nombre del usuario", example = "Juan Pérez")
    String nombre, 
    @Schema(description = "Email del usuario", example = "juan.perez@example.com")
    String email) {
    
}
