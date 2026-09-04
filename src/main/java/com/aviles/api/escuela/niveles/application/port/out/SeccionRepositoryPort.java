package com.aviles.api.escuela.niveles.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.niveles.domain.Seccion;

public interface SeccionRepositoryPort {
    Seccion save(Seccion seccion);
    List<Seccion> findAllSecciones();
    Optional<Seccion> findSeccionById(Long id);
    void deleteSeccionById(Long id);
}