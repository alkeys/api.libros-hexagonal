package com.aviles.api.escuela.usuarios.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataRolRepository extends JpaRepository<JpaRol, Long> {
    java.util.Optional<JpaRol> findByNombre(String nombre);
}
