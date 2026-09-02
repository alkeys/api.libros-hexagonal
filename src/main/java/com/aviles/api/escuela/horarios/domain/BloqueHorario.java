package com.aviles.api.escuela.horarios.domain;

import java.time.LocalTime;

import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Entidad de dominio que representa un bloque horario (día y hora).
 */
public record BloqueHorario(
    Id id,
    String diaSemana,
    LocalTime horaInicio,
    LocalTime horaFin
) {
    public BloqueHorario {
        if (diaSemana == null || diaSemana.isBlank()) throw new IllegalArgumentException("El día de la semana es obligatorio");
        if (horaInicio == null || horaFin == null) throw new IllegalArgumentException("Las horas son obligatorias");
        if (!horaInicio.isBefore(horaFin)) throw new IllegalArgumentException("La hora de inicio debe ser anterior a la de fin");
    }

    public static BloqueHorario nuevo(String diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        return new BloqueHorario(null, diaSemana, horaInicio, horaFin);
    }
}
