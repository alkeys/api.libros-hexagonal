package com.aviles.api.escuela.pagos.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataConceptoPagoRepository extends JpaRepository<JpaConceptoPago, Long> {
}
