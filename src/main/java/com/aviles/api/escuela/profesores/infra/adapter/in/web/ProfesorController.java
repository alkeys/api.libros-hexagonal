package com.aviles.api.escuela.profesores.infra.adapter.in.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.aviles.api.escuela.profesores.application.port.in.*;
import com.aviles.api.escuela.profesores.domain.*;
import com.aviles.api.escuela.profesores.infra.adapter.in.web.dto.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/profesores")
@Tag(name = "Profesores", description = "Gestión de profesores y departamentos")
public class ProfesorController {

    private final CreateProfesorCase createProfesorCase;
    private final GetAllProfesoresCase getAllProfesoresCase;
    private final CreateDepartamentoCase createDepartamentoCase;
    private final GetAllDepartamentosCase getAllDepartamentosCase;

    public ProfesorController(CreateProfesorCase createProfesorCase, GetAllProfesoresCase getAllProfesoresCase,
                              CreateDepartamentoCase createDepartamentoCase, GetAllDepartamentosCase getAllDepartamentosCase) {
        this.createProfesorCase = createProfesorCase;
        this.getAllProfesoresCase = getAllProfesoresCase;
        this.createDepartamentoCase = createDepartamentoCase;
        this.getAllDepartamentosCase = getAllDepartamentosCase;
    }

    @Operation(summary = "Crear profesor")
    @PostMapping
    public ProfesorResponse create(@RequestBody ProfesorRequest request) {
        Profesor profesor = Profesor.nuevo(request.codigoProfesor(), request.nombres(), request.apellidos(), request.especialidad());
        return toProfesorResponse(createProfesorCase.create(profesor));
    }

    @Operation(summary = "Obtener todos los profesores")
    @GetMapping
    public List<ProfesorResponse> getAll() {
        return getAllProfesoresCase.getAll().stream().map(this::toProfesorResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Crear departamento")
    @PostMapping("/departamentos")
    public DepartamentoResponse createDepartamento(@RequestBody DepartamentoRequest request) {
        return toDepartamentoResponse(createDepartamentoCase.create(Departamento.nuevo(request.nombre(), request.descripcion())));
    }

    @Operation(summary = "Obtener todos los departamentos")
    @GetMapping("/departamentos")
    public List<DepartamentoResponse> getAllDepartamentos() {
        return getAllDepartamentosCase.getAllDepartamentos().stream().map(this::toDepartamentoResponse).collect(Collectors.toList());
    }

    private ProfesorResponse toProfesorResponse(Profesor p) {
        return new ProfesorResponse(p.id().getValue(), p.codigoProfesor(), p.nombres(), p.apellidos(),
                p.dui(), p.especialidad(), p.correoElectronico(), p.telefono(), p.estado());
    }

    private DepartamentoResponse toDepartamentoResponse(Departamento d) {
        return new DepartamentoResponse(d.id().getValue(), d.nombre(), d.descripcion());
    }
}
