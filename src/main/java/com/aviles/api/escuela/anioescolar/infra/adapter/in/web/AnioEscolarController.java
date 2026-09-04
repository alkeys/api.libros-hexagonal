package com.aviles.api.escuela.anioescolar.infra.adapter.in.web;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.aviles.api.escuela.anioescolar.application.port.in.*;
import com.aviles.api.escuela.anioescolar.domain.AnioEscolar;
import com.aviles.api.escuela.anioescolar.domain.PeriodoAcademico;
import com.aviles.api.escuela.anioescolar.infra.adapter.in.web.dto.*;
import com.aviles.api.escuela.shared.domain.values.Id;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/anios-escolares")
@Tag(name = "Años Escolares", description = "Gestión de años escolares y períodos académicos")
public class AnioEscolarController {

    private final CreateAnioEscolarCase createAnioCase;
    private final GetAllAniosEscolaresCase getAllAniosCase;
    private final UpdateAnioEscolarCase updateAnioCase;
    private final DeleteAnioEscolarCase deleteAnioCase;
    private final CreatePeriodoCase createPeriodoCase;
    private final GetPeriodosByAnioCase getPeriodosCase;
    private final UpdatePeriodoCase updatePeriodoCase;
    private final DeletePeriodoCase deletePeriodoCase;

    public AnioEscolarController(CreateAnioEscolarCase createAnioCase, GetAllAniosEscolaresCase getAllAniosCase,
                                  UpdateAnioEscolarCase updateAnioCase, DeleteAnioEscolarCase deleteAnioCase,
                                  CreatePeriodoCase createPeriodoCase, GetPeriodosByAnioCase getPeriodosCase,
                                  UpdatePeriodoCase updatePeriodoCase, DeletePeriodoCase deletePeriodoCase) {
        this.createAnioCase = createAnioCase;
        this.getAllAniosCase = getAllAniosCase;
        this.updateAnioCase = updateAnioCase;
        this.deleteAnioCase = deleteAnioCase;
        this.createPeriodoCase = createPeriodoCase;
        this.getPeriodosCase = getPeriodosCase;
        this.updatePeriodoCase = updatePeriodoCase;
        this.deletePeriodoCase = deletePeriodoCase;
    }

    @Operation(summary = "Crear año escolar")
    @PostMapping
    public AnioEscolarResponse create(@RequestBody AnioEscolarRequest request) {
        AnioEscolar anio = AnioEscolar.nuevo(request.nombre(), request.anio(),
                LocalDate.parse(request.fechaInicio()), LocalDate.parse(request.fechaFin()));
        return toResponse(createAnioCase.create(anio));
    }

    @Operation(summary = "Obtener todos los años escolares")
    @GetMapping
    public List<AnioEscolarResponse> getAll() {
        return getAllAniosCase.getAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Actualizar año escolar")
    @PutMapping("/{id}")
    public AnioEscolarResponse update(@PathVariable Long id, @RequestBody AnioEscolarRequest request) {
        AnioEscolar anio = new AnioEscolar(new Id(id), request.nombre(), request.anio(),
                LocalDate.parse(request.fechaInicio()), LocalDate.parse(request.fechaFin()), null);
        return toResponse(updateAnioCase.update(anio));
    }

    @Operation(summary = "Eliminar año escolar")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteAnioCase.deleteAnioEscolar(new Id(id));
    }

    @Operation(summary = "Crear período académico")
    @PostMapping("/periodos")
    public PeriodoResponse createPeriodo(@RequestBody PeriodoRequest request) {
        PeriodoAcademico periodo = PeriodoAcademico.nuevo(new Id(request.idAnioEscolar()), request.nombre(),
                request.numeroPeriodo(), LocalDate.parse(request.fechaInicio()), LocalDate.parse(request.fechaFin()));
        return toResponsePeriodo(createPeriodoCase.create(periodo));
    }

    @Operation(summary = "Obtener períodos de un año escolar")
    @GetMapping("/{idAnio}/periodos")
    public List<PeriodoResponse> getPeriodos(@PathVariable Long idAnio) {
        return getPeriodosCase.getByAnioEscolar(new Id(idAnio)).stream()
                .map(this::toResponsePeriodo).collect(Collectors.toList());
    }

    @Operation(summary = "Actualizar período académico")
    @PutMapping("/periodos/{id}")
    public PeriodoResponse updatePeriodo(@PathVariable Long id, @RequestBody PeriodoRequest request) {
        PeriodoAcademico periodo = new PeriodoAcademico(new Id(id), new Id(request.idAnioEscolar()), request.nombre(),
                request.numeroPeriodo(), LocalDate.parse(request.fechaInicio()), LocalDate.parse(request.fechaFin()), null);
        return toResponsePeriodo(updatePeriodoCase.update(periodo));
    }

    @Operation(summary = "Eliminar período académico")
    @DeleteMapping("/periodos/{id}")
    public void deletePeriodo(@PathVariable Long id) {
        deletePeriodoCase.deletePeriodo(new Id(id));
    }

    private AnioEscolarResponse toResponse(AnioEscolar a) {
        return new AnioEscolarResponse(a.id().getValue(), a.nombre(), a.anio(),
                a.fechaInicio().toString(), a.fechaFin().toString(), a.estado());
    }

    private PeriodoResponse toResponsePeriodo(PeriodoAcademico p) {
        return new PeriodoResponse(p.id().getValue(), p.idAnioEscolar().getValue(), p.nombre(),
                p.numeroPeriodo(), p.fechaInicio().toString(), p.fechaFin().toString(), p.estado());
    }
}