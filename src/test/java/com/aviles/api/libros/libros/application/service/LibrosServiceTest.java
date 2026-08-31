package com.aviles.api.libros.libros.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.aviles.api.libros.libros.application.port.out.LibroRepositoryPort;
import com.aviles.api.libros.libros.domain.Libro;

@ExtendWith(MockitoExtension.class)
class LibrosServiceTest {

    @Mock
    private LibroRepositoryPort libroRepositoryPort;

    @InjectMocks
    private LibrosService librosService;

    private Libro libroEjemplo;
    private String uuidEjemplo;

    @BeforeEach
    void setUp() {
        uuidEjemplo = UUID.randomUUID().toString();
        libroEjemplo = new Libro(
            null,
            "El Principito",
            "Antoine de Saint-Exupéry",
            "Un clásico de la literatura universal",
            "https://imagen.test/principito.jpg",
            5
        );
    }

    // =========================================================
    // TESTS PARA createLibro
    // =========================================================

    @Nested
    @DisplayName("📚 Tests de createLibro")
    class CreateLibroTests {

        @Test
        @DisplayName("Debería crear un libro correctamente")
        void deberiaCrearUnLibroCorrectamente() {
            // Arrange
            Libro libroConId = new Libro(
                uuidEjemplo,
                "El Principito",
                "Antoine de Saint-Exupéry",
                "Un clásico de la literatura universal",
                "https://imagen.test/principito.jpg",
                5
            );
            when(libroRepositoryPort.save(any(Libro.class))).thenReturn(libroConId);

            // Act
            Libro libroCreado = librosService.createLibro(libroEjemplo);

            // Assert
            assertNotNull(libroCreado, "El libro creado no debe ser nulo");
            assertNotNull(libroCreado.id(), "El libro debe tener un ID");
            assertEquals("El Principito", libroCreado.titulo().getValue());
            assertEquals("Antoine de Saint-Exupéry", libroCreado.autor().getValue());
            assertEquals(5, libroCreado.cantidad().getValue());

            verify(libroRepositoryPort, times(1)).save(libroEjemplo);
        }

        @Test
        @DisplayName("Debería crear un libro con cantidad 0")
        void deberiaCrearLibroConCantidadCero() {
            // Arrange
            Libro libroSinStock = new Libro(null, "Libro Sin Stock", "Autor", "Desc", "url", 0);
            Libro libroConId = new Libro(uuidEjemplo, "Libro Sin Stock", "Autor", "Desc", "url", 0);
            when(libroRepositoryPort.save(any(Libro.class))).thenReturn(libroConId);

            // Act
            Libro libroCreado = librosService.createLibro(libroSinStock);

            // Assert
            assertEquals(0, libroCreado.cantidad().getValue());
        }

        @Test
        @DisplayName("Debería propagar error cuando el repositorio falla")
        void deberiaPropagarErrorCuandoRepositorioFalla() {
            // Arrange
            when(libroRepositoryPort.save(any(Libro.class)))
                .thenThrow(new RuntimeException("Error de base de datos"));

            // Act & Assert
            assertThrows(RuntimeException.class,
                () -> librosService.createLibro(libroEjemplo),
                "Debe propagar la excepción del repositorio");
        }
    }

    // =========================================================
    // TESTS PARA getAllLibros paginado
    // =========================================================

    @Nested
    @DisplayName("📖 Tests de getAllLibros paginado")
    class GetAllLibrosTests {

        @Test
        @DisplayName("Debería retornar la primera página de libros")
        void deberiaRetornarPrimeraPagina() {
            // Arrange
            Pageable pageable = PageRequest.of(0, 10);
            List<Libro> libros = List.of(
                new Libro(uuidEjemplo, "Libro 1", "Autor 1", "Desc 1", "url1", 5),
                new Libro(UUID.randomUUID().toString(), "Libro 2", "Autor 2", "Desc 2", "url2", 3)
            );
            Page<Libro> pagina = new PageImpl<>(libros, pageable, 2);
            when(libroRepositoryPort.findAll(pageable)).thenReturn(pagina);

            // Act
            Page<Libro> resultado = librosService.getAllLibros(pageable);

            // Assert
            assertNotNull(resultado);
            assertEquals(2, resultado.getContent().size());
            assertEquals(0, resultado.getNumber());
            assertEquals(10, resultado.getSize());
            assertEquals(2, resultado.getTotalElements());

            verify(libroRepositoryPort, times(1)).findAll(pageable);
        }

        @Test
        @DisplayName("Debería retornar página vacía cuando no hay libros")
        void deberiaRetornarPaginaVacia() {
            // Arrange
            Pageable pageable = PageRequest.of(0, 10);
            Page<Libro> paginaVacia = new PageImpl<>(List.of(), pageable, 0);
            when(libroRepositoryPort.findAll(pageable)).thenReturn(paginaVacia);

            // Act
            Page<Libro> resultado = librosService.getAllLibros(pageable);

            // Assert
            assertNotNull(resultado);
            assertTrue(resultado.isEmpty(), "La página debe estar vacía");
            assertEquals(0, resultado.getTotalElements());
        }

        @Test
        @DisplayName("Debería manejar segunda página correctamente")
        void deberiaManejarSegundaPagina() {
            // Arrange
            Pageable pageable = PageRequest.of(1, 10);
            List<Libro> libros = List.of(
                new Libro(UUID.randomUUID().toString(), "Libro 11", "Autor", "Desc", "url", 2)
            );
            Page<Libro> pagina = new PageImpl<>(libros, pageable, 11);
            when(libroRepositoryPort.findAll(pageable)).thenReturn(pagina);

            // Act
            Page<Libro> resultado = librosService.getAllLibros(pageable);

            // Assert
            assertEquals(1, resultado.getNumber());
            assertEquals(11, resultado.getTotalElements());
        }

        @Test
        @DisplayName("Debería manejar tamaño de página personalizado")
        void deberiaManejarTamanoPaginaPersonalizado() {
            // Arrange
            Pageable pageable = PageRequest.of(0, 5);
            List<Libro> libros = List.of(
                new Libro(UUID.randomUUID().toString(), "Libro 1", "Autor", "Desc", "url", 1),
                new Libro(UUID.randomUUID().toString(), "Libro 2", "Autor", "Desc", "url", 1),
                new Libro(UUID.randomUUID().toString(), "Libro 3", "Autor", "Desc", "url", 1),
                new Libro(UUID.randomUUID().toString(), "Libro 4", "Autor", "Desc", "url", 1),
                new Libro(UUID.randomUUID().toString(), "Libro 5", "Autor", "Desc", "url", 1)
            );
            Page<Libro> pagina = new PageImpl<>(libros, pageable, 25);
            when(libroRepositoryPort.findAll(pageable)).thenReturn(pagina);

            // Act
            Page<Libro> resultado = librosService.getAllLibros(pageable);

            // Assert
            assertEquals(5, resultado.getSize());
            assertEquals(5, resultado.getContent().size());
            assertEquals(25, resultado.getTotalElements());
            assertEquals(5, resultado.getTotalPages()); // 25 / 5 = 5
        }
    }

    // =========================================================
    // TESTS PARA restarLibro atómico
    // =========================================================

    @Nested
    @DisplayName("📉 Tests de restarLibro atómico")
    class RestarLibroTests {

        @Test
        @DisplayName("Debería restar 1 libro exitosamente")
        void deberiaRestarUnLibroExitosamente() {
            // Arrange
            when(libroRepositoryPort.restarLibro(uuidEjemplo)).thenReturn(true);

            // Act
            boolean resultado = librosService.restarLibro(uuidEjemplo);

            // Assert
            assertTrue(resultado, "Debe retornar true cuando se pudo restar");
            verify(libroRepositoryPort, times(1)).restarLibro(uuidEjemplo);
        }

        @Test
        @DisplayName("Debería retornar false cuando no hay stock")
        void deberiaRetornarFalseCuandoNoHayStock() {
            // Arrange
            when(libroRepositoryPort.restarLibro(uuidEjemplo)).thenReturn(false);

            // Act
            boolean resultado = librosService.restarLibro(uuidEjemplo);

            // Assert
            assertFalse(resultado, "Debe retornar false cuando no hay stock");
        }

        @Test
        @DisplayName("Debería retornar false cuando el libro no existe")
        void deberiaRetornarFalseCuandoLibroNoExiste() {
            // Arrange
            String idInexistente = UUID.randomUUID().toString();
            when(libroRepositoryPort.restarLibro(idInexistente)).thenReturn(false);

            // Act
            boolean resultado = librosService.restarLibro(idInexistente);

            // Assert
            assertFalse(resultado);
        }

        @Test
        @DisplayName("Debería funcionar con múltiples restas consecutivas")
        void deberiaFuncionarConMultiplesRestas() {
            // Arrange
            when(libroRepositoryPort.restarLibro(uuidEjemplo))
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false); // 3ra vez sin stock

            // Act & Assert
            assertTrue(librosService.restarLibro(uuidEjemplo), "1ra resta: OK");
            assertTrue(librosService.restarLibro(uuidEjemplo), "2da resta: OK");
            assertFalse(librosService.restarLibro(uuidEjemplo), "3ra resta: sin stock");

            verify(libroRepositoryPort, times(3)).restarLibro(uuidEjemplo);
        }

        @Test
        @DisplayName("Debería propagar error cuando el repositorio falla en restarLibro")
        void deberiaPropagarErrorCuandoRepositorioFallaEnRestar() {
            // Arrange
            when(libroRepositoryPort.restarLibro(uuidEjemplo))
                .thenThrow(new RuntimeException("Error de base de datos"));

            // Act & Assert
            assertThrows(RuntimeException.class,
                () -> librosService.restarLibro(uuidEjemplo),
                "Debe propagar la excepción del repositorio");
        }
    }
}
