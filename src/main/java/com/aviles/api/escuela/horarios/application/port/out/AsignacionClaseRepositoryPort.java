package com.aviles.api.escuela.horarios.application.port.out;

import java.util.List;
import com.aviles.api.escuela.horarios.domain.AsignacionClase;

public interface AsignacionClaseRepositoryPort {
    AsignacionClase save(AsignacionClase asignacion);
    List<AsignacionClase> findAllAsignaciones();
}
