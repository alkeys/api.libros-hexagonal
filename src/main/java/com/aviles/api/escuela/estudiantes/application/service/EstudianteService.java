package com.aviles.api.escuela.estudiantes.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
        CreateMatriculaCase, GetMatriculasByGrupoCase, CreateContactoEmergenciaCase {

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
    public Matricula create(Matricula matricula) { return matriculaRepositoryPort.save(matricula); }

    @Override
    public List<Matricula> getByGrupo(Id idGrupo) { return matriculaRepositoryPort.findByGrupo(idGrupo); }

    @Override
    public ContactoEmergencia create(ContactoEmergencia contacto) { return contactoRepositoryPort.save(contacto); }
}
