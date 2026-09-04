package com.aviles.api.escuela.horarios.application.port.in;

import com.aviles.api.escuela.horarios.domain.AsignacionClase;

public interface UpdateAsignacionClaseCase {
    AsignacionClase update(AsignacionClase asignacion);
}