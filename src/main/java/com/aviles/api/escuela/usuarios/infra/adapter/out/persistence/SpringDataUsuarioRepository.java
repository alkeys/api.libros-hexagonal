package com.aviles.api.escuela.usuarios.infra.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataUsuarioRepository extends JpaRepository<JpaUsuario, Long> {
}
