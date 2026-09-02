package com.aviles.api.escuela.representantes.application.port.out;

import java.util.List;
import com.aviles.api.escuela.representantes.domain.Representante;

public interface RepresentanteRepositoryPort {
    Representante save(Representante representante);
    List<Representante> findAll();
}
