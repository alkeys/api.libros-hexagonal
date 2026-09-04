package com.aviles.api.escuela.evaluaciones.application.port.in;

import com.aviles.api.escuela.evaluaciones.domain.Calificacion;

/**
 * Crea o actualiza la calificación de un estudiante en una evaluación.
 * Usa la combinación (evaluación, estudiante) como clave natural.
 */
public interface UpsertCalificacionCase {
    Calificacion upsert(Calificacion calificacion);
}