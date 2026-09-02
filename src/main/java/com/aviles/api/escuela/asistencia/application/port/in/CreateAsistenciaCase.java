package com.aviles.api.escuela.asistencia.application.port.in;

import com.aviles.api.escuela.asistencia.domain.Asistencia;

public interface CreateAsistenciaCase {
    Asistencia create(Asistencia asistencia);
}
