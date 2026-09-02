package com.aviles.api.escuela.niveles.application.port.out;

import java.util.List;
import com.aviles.api.escuela.niveles.domain.Seccion;

public interface SeccionRepositoryPort {
    Seccion save(Seccion seccion);
    List<Seccion> findAllSecciones();
}
