package com.aviles.api.libros.libros.application.port.in;

public interface RestarLibroCase {
    /**
     * Resta 1 a la cantidad del libro. Retorna true si se pudo, false si no hay stock.
     */
    boolean restarLibro(String id);
}
