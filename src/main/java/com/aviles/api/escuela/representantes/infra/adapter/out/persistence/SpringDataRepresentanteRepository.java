package com.aviles.api.escuela.representantes.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataRepresentanteRepository extends JpaRepository<JpaRepresentante, Long> {
}
