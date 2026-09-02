package com.aviles.api.escuela.actividades.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataActividadRepository extends JpaRepository<JpaActividad, Long> {
}
