package com.aviles.api.escuela.estudiantes.application.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aviles.api.escuela.estudiantes.application.port.in.*;
import com.aviles.api.escuela.estudiantes.application.port.out.*;
import com.aviles.api.escuela.estudiantes.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Servicio que implementa los casos de uso del módulo de estudiantes.
 * Maneja la lógica de negocio para la gestión de estudiantes, matrículas y contactos de emergencia.
 */
@Service
public class EstudianteService implements CreateEstudianteCase, GetAllEstudiantesCase, GetEstudianteByIdCase,
        CreateMatriculaCase, GetMatriculasByGrupoCase, CreateContactoEmergenciaCase,
        UpdateEstudianteCase, DeleteEstudianteCase {

    private final EstudianteRepositoryPort estudianteRepositoryPort;
    private final MatriculaRepositoryPort matriculaRepositoryPort;
    private final ContactoEmergenciaRepositoryPort contactoRepositoryPort;

    public EstudianteService(EstudianteRepositoryPort estudianteRepositoryPort,
                              MatriculaRepositoryPort matriculaRepositoryPort,
                              ContactoEmergenciaRepositoryPort contactoRepositoryPort) {
        this.estudianteRepositoryPort = estudianteRepositoryPort;
        this.matriculaRepositoryPort = matriculaRepositoryPort;
        this.contactoRepositoryPort = contactoRepositoryPort;
    }

    @Override
    public Estudiante create(Estudiante estudiante) { return estudianteRepositoryPort.save(estudiante); }

    @Override
    public List<Estudiante> getAll() { return estudianteRepositoryPort.findAll(); }

    @Override
    public Optional<Estudiante> getById(Id id) { return estudianteRepositoryPort.findById(id.getValue()); }

    @Override
    public Estudiante update(Estudiante estudiante) {
        Estudiante existente = estudianteRepositoryPort.findById(estudiante.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado"));
        Estudiante actualizado = new Estudiante(existente.id(), estudiante.codigoEstudiante(), estudiante.nombres(),
                estudiante.apellidos(), estudiante.fechaNacimiento(), estudiante.genero(), estudiante.nacionalidad(),
                estudiante.dui(), estudiante.nie(), estudiante.correoElectronico(), estudiante.telefono(),
                estudiante.direccion(), estudiante.fechaIngreso(), existente.estado(), existente.fotoUrl(),
                existente.fechaCreacion(), OffsetDateTime.now());
        return estudianteRepositoryPort.save(actualizado);
    }

    @Override
    public void delete(Id id) {
        if (estudianteRepositoryPort.findById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado");
        }
        estudianteRepositoryPort.deleteById(id.getValue());
    }

    @Override
    public Matricula create(Matricula matricula) { return matriculaRepositoryPort.save(matricula); }

    @Override
    public List<Matricula> getByGrupo(Id idGrupo) { return matriculaRepositoryPort.findByGrupo(idGrupo); }

    @Override
    public ContactoEmergencia create(ContactoEmergencia contacto) { return contactoRepositoryPort.save(contacto); }
}
