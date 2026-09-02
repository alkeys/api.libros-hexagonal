package com.aviles.api.escuela.grupos.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataGrupoRepository extends JpaRepository<JpaGrupo, Long> {
}
