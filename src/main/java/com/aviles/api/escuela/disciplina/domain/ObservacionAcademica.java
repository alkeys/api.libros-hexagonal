package com.aviles.api.escuela.disciplina.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa una observación académica de un estudiante.
 */
public record ObservacionAcademica(
    Id id,
    Id idEstudiante,
    Id idProfesor,
    Id idPeriodo,
    java.time.LocalDate fecha,
    String tipo,
    String descripcion
) {
    public ObservacionAcademica {
        if (idEstudiante == null) throw new IllegalArgumentException("El estudiante es obligatorio");
        if (tipo == null || tipo.isBlank()) throw new IllegalArgumentException("El tipo es obligatorio");
        if (descripcion == null || descripcion.isBlank()) throw new IllegalArgumentException("La descripción es obligatoria");
    }

    public static ObservacionAcademica nueva(Id idEstudiante, java.time.LocalDate fecha, String tipo, String descripcion) {
        return new ObservacionAcademica(null, idEstudiante, null, null, fecha, tipo, descripcion);
    }
}
