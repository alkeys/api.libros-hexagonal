package com.aviles.api.escuela.evaluaciones.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataNotaFinalRepository extends JpaRepository<JpaNotaFinal, Long> {
}
