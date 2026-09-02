package com.aviles.api.escuela.disciplina.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un incidente disciplinario de un estudiante.
 */
public record IncidenteDisciplinario(
    Id id,
    Id idEstudiante,
    java.time.LocalDate fecha,
    String tipo,
    String motivo,
    String descripcion,
    String medidaTomada,
    String estado,
    Id idProfesor
) {
    public IncidenteDisciplinario {
        if (idEstudiante == null) throw new IllegalArgumentException("El estudiante es obligatorio");
        if (tipo == null || tipo.isBlank()) throw new IllegalArgumentException("El tipo es obligatorio");
        if (motivo == null || motivo.isBlank()) throw new IllegalArgumentException("El motivo es obligatorio");
    }

    public static IncidenteDisciplinario nuevo(Id idEstudiante, java.time.LocalDate fecha, String tipo, String motivo, String descripcion) {
        return new IncidenteDisciplinario(null, idEstudiante, fecha, tipo, motivo, descripcion, null, "ABIERTO", null);
    }
}
