package com.aviles.api.escuela.usuarios.infra.adapter.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataUsuarioRepository extends JpaRepository<JpaUsuario, Long> {
    Optional<JpaUsuario> findByUsername(String username);
    Optional<JpaUsuario> findByCorreo(String correo);
}