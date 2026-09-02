package com.aviles.api.escuela.estudiantes.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aviles.api.escuela.estudiantes.application.port.out.EstudianteRepositoryPort;
import com.aviles.api.escuela.estudiantes.application.port.out.MatriculaRepositoryPort;
import com.aviles.api.escuela.estudiantes.application.port.out.ContactoEmergenciaRepositoryPort;
import com.aviles.api.escuela.estudiantes.domain.Estudiante;
import com.aviles.api.escuela.shared.domain.values.Id;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {

    @Mock
    private EstudianteRepositoryPort estudianteRepositoryPort;

    @Mock
    private MatriculaRepositoryPort matriculaRepositoryPort;

    @Mock
    private ContactoEmergenciaRepositoryPort contactoRepositoryPort;

    @InjectMocks
    private EstudianteService estudianteService;

    private Estudiante estudianteEjemplo;

    @BeforeEach
    void setUp() {
        estudianteEjemplo = Estudiante.nuevo("EST-001", "Juan Carlos", "Pérez López",
                LocalDate.of(2010, 5, 15), "MASCULINO");
    }

    @Nested
    @DisplayName("Tests de create Estudiante")
    class CreateEstudianteTests {

        @Test
        @DisplayName("Debería crear un estudiante correctamente")
        void deberiaCrearUnEstudianteCorrectamente() {
            Estudiante estudianteConId = new Estudiante(new Id(1L), "EST-001", "Juan Carlos", "Pérez López",
                    LocalDate.of(2010, 5, 15), "MASCULINO", null, null, null, null, null, null, null,
                    "ACTIVO", null, null, null);
            when(estudianteRepositoryPort.save(any(Estudiante.class))).thenReturn(estudianteConId);

            Estudiante estudianteCreado = estudianteService.create(estudianteEjemplo);

            assertNotNull(estudianteCreado);
            assertNotNull(estudianteCreado.id());
            assertEquals("EST-001", estudianteCreado.codigoEstudiante());
            assertEquals("Juan Carlos", estudianteCreado.nombres());

            verify(estudianteRepositoryPort, times(1)).save(estudianteEjemplo);
        }

        @Test
        @DisplayName("Debería retornar todos los estudiantes")
        void deberiaRetornarTodosLosEstudiantes() {
            List<Estudiante> estudiantes = List.of(estudianteEjemplo);
            when(estudianteRepositoryPort.findAll()).thenReturn(estudiantes);

            List<Estudiante> resultado = estudianteService.getAll();

            assertNotNull(resultado);
            assertEquals(1, resultado.size());
            verify(estudianteRepositoryPort, times(1)).findAll();
        }
    }
}
