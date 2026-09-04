package com.aviles.api.escuela.anioescolar.application.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aviles.api.escuela.anioescolar.application.port.in.*;
import com.aviles.api.escuela.anioescolar.application.port.out.AnioEscolarRepositoryPort;
import com.aviles.api.escuela.anioescolar.application.port.out.PeriodoRepositoryPort;
import com.aviles.api.escuela.anioescolar.domain.AnioEscolar;
import com.aviles.api.escuela.anioescolar.domain.PeriodoAcademico;
import com.aviles.api.escuela.shared.domain.values.Id;

/**
 * Servicio que implementa los casos de uso de años escolares y períodos académicos.
 * Maneja la lógica de negocio para la gestión del calendario escolar.
 */
@Service
public class AnioEscolarService implements CreateAnioEscolarCase, GetAllAniosEscolaresCase,
        CreatePeriodoCase, GetPeriodosByAnioCase,
        UpdateAnioEscolarCase, DeleteAnioEscolarCase, UpdatePeriodoCase, DeletePeriodoCase {

    private final AnioEscolarRepositoryPort anioEscolarRepositoryPort;
    private final PeriodoRepositoryPort periodoRepositoryPort;

    public AnioEscolarService(AnioEscolarRepositoryPort anioEscolarRepositoryPort,
                               PeriodoRepositoryPort periodoRepositoryPort) {
        this.anioEscolarRepositoryPort = anioEscolarRepositoryPort;
        this.periodoRepositoryPort = periodoRepositoryPort;
    }

    @Override
    public AnioEscolar create(AnioEscolar anioEscolar) {
        return anioEscolarRepositoryPort.save(anioEscolar);
    }

    @Override
    public List<AnioEscolar> getAll() {
        return anioEscolarRepositoryPort.findAll();
    }

    @Override
    public PeriodoAcademico create(PeriodoAcademico periodo) {
        return periodoRepositoryPort.save(periodo);
    }

    @Override
    public List<PeriodoAcademico> getByAnioEscolar(Id idAnioEscolar) {
        return periodoRepositoryPort.findByAnioEscolar(idAnioEscolar);
    }

    @Override
    public AnioEscolar update(AnioEscolar anioEscolar) {
        AnioEscolar existente = anioEscolarRepositoryPort.findAnioEscolarById(anioEscolar.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Año escolar no encontrado"));
        AnioEscolar actualizado = new AnioEscolar(existente.id(), anioEscolar.nombre(), anioEscolar.anio(),
                anioEscolar.fechaInicio(), anioEscolar.fechaFin(), existente.estado());
        return anioEscolarRepositoryPort.save(actualizado);
    }

    @Override
    public void deleteAnioEscolar(Id id) {
        if (anioEscolarRepositoryPort.findAnioEscolarById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Año escolar no encontrado");
        }
        anioEscolarRepositoryPort.deleteAnioEscolarById(id.getValue());
    }

    @Override
    public PeriodoAcademico update(PeriodoAcademico periodo) {
        PeriodoAcademico existente = periodoRepositoryPort.findPeriodoById(periodo.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Período no encontrado"));
        PeriodoAcademico actualizado = new PeriodoAcademico(existente.id(), periodo.idAnioEscolar(),
                periodo.nombre(), periodo.numeroPeriodo(), periodo.fechaInicio(), periodo.fechaFin(), existente.estado());
        return periodoRepositoryPort.save(actualizado);
    }

    @Override
    public void deletePeriodo(Id id) {
        if (periodoRepositoryPort.findPeriodoById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Período no encontrado");
        }
        periodoRepositoryPort.deletePeriodoById(id.getValue());
    }
}
