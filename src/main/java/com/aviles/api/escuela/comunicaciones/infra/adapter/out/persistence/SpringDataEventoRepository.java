package com.aviles.api.escuela.comunicaciones.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataEventoRepository extends JpaRepository<JpaEvento, Long> {
}
