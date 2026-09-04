package com.aviles.api.escuela.profesores.application.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aviles.api.escuela.profesores.application.port.in.*;
import com.aviles.api.escuela.profesores.application.port.out.*;
import com.aviles.api.escuela.profesores.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

@Service
public class ProfesorService implements CreateProfesorCase, GetAllProfesoresCase,
        CreateDepartamentoCase, GetAllDepartamentosCase, UpdateProfesorCase, DeleteProfesorCase {

    private final ProfesorRepositoryPort profesorRepositoryPort;
    private final DepartamentoRepositoryPort departamentoRepositoryPort;

    public ProfesorService(ProfesorRepositoryPort profesorRepositoryPort, DepartamentoRepositoryPort departamentoRepositoryPort) {
        this.profesorRepositoryPort = profesorRepositoryPort;
        this.departamentoRepositoryPort = departamentoRepositoryPort;
    }

    @Override
    public Profesor create(Profesor profesor) { return profesorRepositoryPort.save(profesor); }

    @Override
    public List<Profesor> getAll() { return profesorRepositoryPort.findAll(); }

    @Override
    public Profesor update(Profesor profesor) {
        Profesor existente = profesorRepositoryPort.findById(profesor.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado"));
        Profesor actualizado = new Profesor(existente.id(), profesor.codigoProfesor(), profesor.nombres(),
                profesor.apellidos(), profesor.dui(), profesor.especialidad(), profesor.correoElectronico(),
                profesor.telefono(), profesor.direccion(), existente.fechaContratacion(), existente.estado(),
                existente.fotoUrl(), existente.fechaCreacion());
        return profesorRepositoryPort.save(actualizado);
    }

    @Override
    public void delete(Id id) {
        if (profesorRepositoryPort.findById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado");
        }
        profesorRepositoryPort.deleteById(id.getValue());
    }

    @Override
    public Departamento create(Departamento departamento) { return departamentoRepositoryPort.save(departamento); }

    @Override
    public List<Departamento> getAllDepartamentos() { return departamentoRepositoryPort.findAllDepartamentos(); }
}
