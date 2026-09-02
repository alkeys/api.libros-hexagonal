package com.aviles.api.escuela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación Sistema Escolar.
 * <p>
 * Arquitectura Hexagonal Modular - Monolito Vertical Slices.
 * Cada módulo es una vertical slice completamente independiente con su propio
 * dominio, aplicación e infraestructura.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
