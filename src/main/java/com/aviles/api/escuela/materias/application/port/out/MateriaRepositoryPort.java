package com.aviles.api.escuela.materias.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.materias.domain.Materia;

public interface MateriaRepositoryPort {
    Materia save(Materia materia);
    List<Materia> findAll();
    Optional<Materia> findById(Long id);
    void deleteById(Long id);
}