package com.aviles.api.libros.libros.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aviles.api.libros.libros.application.port.in.*;
import com.aviles.api.libros.libros.application.port.out.LibroRepositoryPort;
import com.aviles.api.libros.libros.domain.Libro;

@Service
public class LibrosService implements NewLibroCase, GetAllLibrosCase, RestarLibroCase {
    private final LibroRepositoryPort libroRepositoryPort;

    public LibrosService(LibroRepositoryPort libroRepositoryPort) {
        this.libroRepositoryPort = libroRepositoryPort;
    }

    @Override
    public Libro createLibro(Libro libro) {
        return libroRepositoryPort.save(libro);
    }

    @Override
    public Page<Libro> getAllLibros(Pageable pageable) {
        return libroRepositoryPort.findAll(pageable);
    }

    @Override
    public boolean restarLibro(String id) {
        return libroRepositoryPort.restarLibro(id);
    }
}
