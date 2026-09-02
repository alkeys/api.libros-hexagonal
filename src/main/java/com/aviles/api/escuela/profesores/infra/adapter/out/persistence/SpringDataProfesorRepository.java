package com.aviles.api.escuela.profesores.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataProfesorRepository extends JpaRepository<JpaProfesor, Long> {
}
