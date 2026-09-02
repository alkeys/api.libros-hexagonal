package com.aviles.api.escuela.horarios.application.port.in;

import java.util.List;
import com.aviles.api.escuela.horarios.domain.AsignacionClase;

public interface GetAllAsignacionesCase {
    List<AsignacionClase> getAllAsignaciones();
}
