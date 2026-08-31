package com.aviles.api.libros.libros.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aviles.api.libros.libros.domain.Libro;

public interface GetAllLibrosCase {
    Page<Libro> getAllLibros(Pageable pageable);
}
