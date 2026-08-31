package com.aviles.api.libros;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aviles.api.libros.libros.application.port.in.NewLibroCase;
import com.aviles.api.libros.libros.domain.Libro;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private NewLibroCase newLibroCase;

	@Test
	void contextLoads() {
	}


	/**
	 * Crear un libro test
	 */
	@Test
	void crearLibroTest() {
		// Implementar la lógica para crear un libro de prueba
		Libro libro = new Libro(null,"Titulo de prueba", "Autor de prueba",
		 "Descripción de prueba", "URL de imagen de prueba",10);
		Libro libroCreado = newLibroCase.createLibro(libro);
		// Verificar que el libro se haya creado correctamente
		assert libroCreado.id().getValue() != null;
		assert libroCreado.titulo().getValue().equals("Titulo de prueba");
		assert libroCreado.autor().getValue().equals("Autor de prueba");
		assert libroCreado.descripcion().getValue().equals("Descripción de prueba");
		assert libroCreado.cantidad().getValue() == 10;
		
	}
}
