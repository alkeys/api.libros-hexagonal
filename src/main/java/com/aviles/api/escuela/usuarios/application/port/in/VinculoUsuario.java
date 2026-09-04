package com.aviles.api.escuela.usuarios.application.port.in;

/**
 * Resultado del vínculo de un usuario con un profesor o estudiante.
 */
public record VinculoUsuario(Long idProfesor, Long idEstudiante) {}