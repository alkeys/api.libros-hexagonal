package com.aviles.api.escuela.niveles.application.port.in;

import java.util.List;
import com.aviles.api.escuela.niveles.domain.Seccion;

public interface GetAllSeccionesCase {
    List<Seccion> getAllSecciones();
}
