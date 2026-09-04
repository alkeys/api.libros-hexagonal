package com.aviles.api.escuela.grupos.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.grupos.domain.Grupo;

public interface GrupoRepositoryPort {
    Grupo save(Grupo grupo);
    List<Grupo> findAll();
    Optional<Grupo> findById(Long id);
    void deleteById(Long id);
}