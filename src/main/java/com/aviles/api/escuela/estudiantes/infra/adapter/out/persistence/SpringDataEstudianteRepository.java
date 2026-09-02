package com.aviles.api.escuela.estudiantes.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataEstudianteRepository extends JpaRepository<JpaEstudiante, Long> {
}
