package com.aviles.api.escuela.materias.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa una materia o asignatura del sistema escolar.
 */
public record Materia(
    Id id,
    String codigoMateria,
    String nombreMateria,
    String descripcion,
    Integer horasSemanales,
    String tipo,
    String estado
) {
    public Materia {
        if (codigoMateria == null || codigoMateria.isBlank()) throw new IllegalArgumentException("El código es obligatorio");
        if (nombreMateria == null || nombreMateria.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
        if (horasSemanales == null || horasSemanales <= 0) throw new IllegalArgumentException("Las horas semanales deben ser mayores a 0");
    }

    public static Materia nueva(String codigoMateria, String nombreMateria, String descripcion, Integer horasSemanales, String tipo) {
        return new Materia(null, codigoMateria, nombreMateria, descripcion, horasSemanales, tipo, "ACTIVA");
    }
}
