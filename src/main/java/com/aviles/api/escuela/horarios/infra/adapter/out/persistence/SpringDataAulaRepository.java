package com.aviles.api.escuela.horarios.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataAulaRepository extends JpaRepository<JpaAula, Long> {
}
