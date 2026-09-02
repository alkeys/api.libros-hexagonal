package com.aviles.api.escuela.niveles.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataNivelRepository extends JpaRepository<JpaNivelEducativo, Long> {
}
