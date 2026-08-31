package com.aviles.api.libros.libros.infra.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.aviles.api.libros.libros.infra.out.persistence.enitity.LibroEntity;

public interface SpringDataLibrosRepository extends JpaRepository<LibroEntity, UUID> {

    /**
     * Resta 1 a la cantidad de un libro de forma atómica.
     * Retorna 1 si se actualizó, 0 si no existía o ya estaba en 0.
     * Solo 1 query en vez de 3.
     */
    @Modifying
    @Transactional
    @Query("UPDATE LibroEntity l SET l.cantidad = l.cantidad - 1, l.fecha_actualizacion = CURRENT_TIMESTAMP WHERE l.id = :id AND l.cantidad > 0")
    int restarLibroAtomico(@Param("id") UUID id);

    /**
     * Paginación para findAll
     */
    Page<LibroEntity> findAll(Pageable pageable);

    /**
     * Búsqueda por título (para futuro uso)
     */
    Page<LibroEntity> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}
