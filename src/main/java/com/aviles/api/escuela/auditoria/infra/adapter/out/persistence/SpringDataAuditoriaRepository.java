package com.aviles.api.escuela.auditoria.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataAuditoriaRepository extends JpaRepository<JpaAuditoria, Long> {
}
