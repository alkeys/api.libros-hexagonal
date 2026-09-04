package com.aviles.api.escuela.usuarios.infra.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SpringDataUsuarioRolRepository extends JpaRepository<JpaUsuarioRol, JpaUsuarioRolId> {

    List<JpaUsuarioRol> findByIdUsuario(Long idUsuario);

    @Query("SELECT r.nombre FROM JpaUsuarioRol ur JOIN JpaRol r ON r.id = ur.idRol WHERE ur.idUsuario = :idUsuario")
    List<String> findRolNamesByUsuario(@Param("idUsuario") Long idUsuario);

    Optional<JpaUsuarioRol> findByIdUsuarioAndIdRol(Long idUsuario, Long idRol);

    @Transactional
    @Modifying
    @Query("DELETE FROM JpaUsuarioRol ur WHERE ur.idUsuario = :idUsuario AND ur.idRol = :idRol")
    void deleteByIdUsuarioAndIdRol(@Param("idUsuario") Long idUsuario, @Param("idRol") Long idRol);
}