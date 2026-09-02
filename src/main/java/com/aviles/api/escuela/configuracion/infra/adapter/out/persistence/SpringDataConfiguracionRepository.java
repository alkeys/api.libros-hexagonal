package com.aviles.api.escuela.configuracion.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio Spring Data JPA para la entidad configuración.
 */
@Repository
public interface SpringDataConfiguracionRepository extends JpaRepository<JpaConfiguracion, Long> {
}
