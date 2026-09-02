package com.aviles.api.escuela.niveles.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aviles.api.escuela.niveles.application.port.in.*;
import com.aviles.api.escuela.niveles.application.port.out.*;
import com.aviles.api.escuela.niveles.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

@Service
public class NivelesService implements CreateNivelCase, GetAllNivelesCase,
        CreateGradoCase, GetGradosByNivelCase, CreateSeccionCase, GetAllSeccionesCase {

    private final NivelRepositoryPort nivelRepositoryPort;
    private final GradoRepositoryPort gradoRepositoryPort;
    private final SeccionRepositoryPort seccionRepositoryPort;

    public NivelesService(NivelRepositoryPort nivelRepositoryPort, GradoRepositoryPort gradoRepositoryPort,
                          SeccionRepositoryPort seccionRepositoryPort) {
        this.nivelRepositoryPort = nivelRepositoryPort;
        this.gradoRepositoryPort = gradoRepositoryPort;
        this.seccionRepositoryPort = seccionRepositoryPort;
    }

    @Override
    public NivelEducativo create(NivelEducativo nivel) { return nivelRepositoryPort.save(nivel); }

    @Override
    public List<NivelEducativo> getAll() { return nivelRepositoryPort.findAll(); }

    @Override
    public Grado create(Grado grado) { return gradoRepositoryPort.save(grado); }

    @Override
    public List<Grado> getByNivel(Id idNivel) { return gradoRepositoryPort.findByNivel(idNivel); }

    @Override
    public Seccion create(Seccion seccion) { return seccionRepositoryPort.save(seccion); }

    @Override
    public List<Seccion> getAllSecciones() { return seccionRepositoryPort.findAllSecciones(); }
}
