package com.aviles.api.escuela.horarios.infra.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de un bloque horario")
public record BloqueHorarioResponse(
    @Schema(description = "ID del bloque")
    Long id,
    @Schema(description = "Día de la semana", example = "LUNES")
    String diaSemana,
    @Schema(description = "Hora de inicio", example = "07:00")
    String horaInicio,
    @Schema(description = "Hora de fin", example = "08:00")
    String horaFin
) {}
