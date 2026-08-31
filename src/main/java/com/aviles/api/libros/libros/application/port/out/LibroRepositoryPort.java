package com.aviles.api.libros.libros.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aviles.api.libros.libros.domain.Libro;

public interface LibroRepositoryPort {
        Libro save(Libro libro);
        Page<Libro> findAll(Pageable pageable);
        Libro findById(String id);
        void deleteById(String id);
        void updateById(String id, Libro libro);
        boolean restarLibro(String id);
}
