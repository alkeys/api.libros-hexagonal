package com.aviles.api.escuela.asistencia.application.port.in;

import java.util.List;
import com.aviles.api.escuela.asistencia.domain.Asistencia;
import com.aviles.api.escuela.shared.domain.values.Id;

public interface GetAsistenciaByAsignacionCase {
    List<Asistencia> getByAsignacion(Id idAsignacion);
}
