package com.aviles.api.libros.usuarios.infra.adapter.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aviles.api.libros.usuarios.infra.adapter.out.persistence.entity.UsuarioEntity;

public interface SpringDataUsuariosRepository extends JpaRepository<UsuarioEntity, UUID> {
    
}
