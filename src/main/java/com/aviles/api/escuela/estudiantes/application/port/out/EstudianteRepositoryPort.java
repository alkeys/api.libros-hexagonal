package com.aviles.api.escuela.estudiantes.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.estudiantes.domain.Estudiante;

public interface EstudianteRepositoryPort {
    Estudiante save(Estudiante estudiante);
    List<Estudiante> findAll();
    Optional<Estudiante> findById(Long id);
    void deleteById(Long id);
}
