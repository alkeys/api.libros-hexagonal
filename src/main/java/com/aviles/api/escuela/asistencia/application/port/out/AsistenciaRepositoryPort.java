package com.aviles.api.escuela.asistencia.application.port.out;

import java.util.List;
import com.aviles.api.escuela.asistencia.domain.Asistencia;
import com.aviles.api.escuela.shared.domain.values.Id;

public interface AsistenciaRepositoryPort {
    Asistencia save(Asistencia asistencia);
    List<Asistencia> findByAsignacion(Id idAsignacion);
}
