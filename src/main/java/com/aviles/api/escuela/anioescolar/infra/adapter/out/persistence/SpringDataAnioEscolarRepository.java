package com.aviles.api.escuela.anioescolar.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataAnioEscolarRepository extends JpaRepository<JpaAnioEscolar, Long> {
}
