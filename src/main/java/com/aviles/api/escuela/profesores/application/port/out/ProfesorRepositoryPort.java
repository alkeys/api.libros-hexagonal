package com.aviles.api.escuela.profesores.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.profesores.domain.Profesor;

public interface ProfesorRepositoryPort {
    Profesor save(Profesor profesor);
    List<Profesor> findAll();
    Optional<Profesor> findById(Long id);
    void deleteById(Long id);
}