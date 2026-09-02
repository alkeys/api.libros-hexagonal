package com.aviles.api.escuela.horarios.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear un bloque horario")
public record BloqueHorarioRequest(
    @Schema(description = "Día de la semana", example = "LUNES")
    String diaSemana,
    @Schema(description = "Hora de inicio (HH:mm)", example = "07:00")
    String horaInicio,
    @Schema(description = "Hora de fin (HH:mm)", example = "08:00")
    String horaFin
) {}
