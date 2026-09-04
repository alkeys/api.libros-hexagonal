package com.aviles.api.escuela.representantes.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.representantes.domain.Representante;

public interface RepresentanteRepositoryPort {
    Representante save(Representante representante);
    List<Representante> findAll();
    Optional<Representante> findById(Long id);
    void deleteById(Long id);
}