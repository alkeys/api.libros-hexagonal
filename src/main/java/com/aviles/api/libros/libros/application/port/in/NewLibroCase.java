package com.aviles.api.libros.libros.application.port.in;

import com.aviles.api.libros.libros.domain.Libro;

public interface NewLibroCase {
    public Libro createLibro(Libro libro);
    
}
