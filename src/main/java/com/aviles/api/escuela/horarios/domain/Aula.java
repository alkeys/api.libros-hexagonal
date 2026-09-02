package com.aviles.api.escuela.horarios.domain;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un aula del sistema escolar.
 */
public record Aula(
    Id id,
    String codigo,
    String nombre,
    String edificio,
    String piso,
    Integer capacidad,
    String tipo,
    String estado
) {
    public Aula {
        if (codigo == null || codigo.isBlank()) throw new IllegalArgumentException("El código es obligatorio");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
        if (capacidad == null || capacidad <= 0) throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
    }

    public static Aula nueva(String codigo, String nombre, String edificio, String piso, Integer capacidad, String tipo) {
        return new Aula(null, codigo, nombre, edificio, piso, capacidad, tipo, "DISPONIBLE");
    }
}
