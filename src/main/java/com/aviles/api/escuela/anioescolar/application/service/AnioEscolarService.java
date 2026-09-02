package com.aviles.api.escuela.anioescolar.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
        CreatePeriodoCase, GetPeriodosByAnioCase {

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
}
