package com.aviles.api.escuela.horarios.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.horarios.domain.Aula;

public interface AulaRepositoryPort {
    Aula save(Aula aula);
    List<Aula> findAll();
    Optional<Aula> findAulaById(Long id);
    void deleteAulaById(Long id);
}