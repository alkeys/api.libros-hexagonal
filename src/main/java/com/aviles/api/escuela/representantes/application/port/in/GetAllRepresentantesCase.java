package com.aviles.api.escuela.representantes.application.port.in;

import java.util.List;
import com.aviles.api.escuela.representantes.domain.Representante;

public interface GetAllRepresentantesCase {
    List<Representante> getAll();
}
