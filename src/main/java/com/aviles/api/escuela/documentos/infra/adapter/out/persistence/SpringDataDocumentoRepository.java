package com.aviles.api.escuela.documentos.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataDocumentoRepository extends JpaRepository<JpaDocumentoEstudiante, Long> {
}
