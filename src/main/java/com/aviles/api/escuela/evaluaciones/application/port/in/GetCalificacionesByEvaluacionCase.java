package com.aviles.api.escuela.evaluaciones.application.port.in;

import java.util.List;
import com.aviles.api.escuela.evaluaciones.domain.Calificacion;
import com.aviles.api.escuela.shared.domain.values.Id;

public interface GetCalificacionesByEvaluacionCase {
    List<Calificacion> getByEvaluacion(Id idEvaluacion);
}
