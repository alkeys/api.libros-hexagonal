package com.aviles.api.escuela.profesores.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aviles.api.escuela.profesores.application.port.in.*;
import com.aviles.api.escuela.profesores.application.port.out.*;
import com.aviles.api.escuela.profesores.domain.*;

@Service
public class ProfesorService implements CreateProfesorCase, GetAllProfesoresCase,
        CreateDepartamentoCase, GetAllDepartamentosCase {

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
    public Departamento create(Departamento departamento) { return departamentoRepositoryPort.save(departamento); }

    @Override
    public List<Departamento> getAllDepartamentos() { return departamentoRepositoryPort.findAllDepartamentos(); }
}
