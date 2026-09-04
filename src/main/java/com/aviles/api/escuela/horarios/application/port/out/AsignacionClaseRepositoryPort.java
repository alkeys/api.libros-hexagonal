package com.aviles.api.escuela.horarios.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.horarios.domain.AsignacionClase;

public interface AsignacionClaseRepositoryPort {
    AsignacionClase save(AsignacionClase asignacion);
    List<AsignacionClase> findAllAsignaciones();
    Optional<AsignacionClase> findAsignacionById(Long id);
    void deleteAsignacionById(Long id);
}