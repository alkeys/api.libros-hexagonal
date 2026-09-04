package com.aviles.api.escuela.usuarios.infra.adapter.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataUsuarioEstudianteRepository extends JpaRepository<JpaUsuarioEstudiante, Long> {
    Optional<JpaUsuarioEstudiante> findByIdUsuario(Long idUsuario);
}