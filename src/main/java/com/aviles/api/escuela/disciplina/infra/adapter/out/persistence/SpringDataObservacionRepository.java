package com.aviles.api.escuela.disciplina.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataObservacionRepository extends JpaRepository<JpaObservacionAcademica, Long> {
}
