package com.aviles.api.escuela.horarios.application.port.in;

import com.aviles.api.escuela.horarios.domain.AsignacionClase;

public interface CreateAsignacionClaseCase {
    AsignacionClase create(AsignacionClase asignacion);
}
