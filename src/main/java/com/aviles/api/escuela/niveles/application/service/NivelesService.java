package com.aviles.api.escuela.niveles.application.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aviles.api.escuela.niveles.application.port.in.*;
import com.aviles.api.escuela.niveles.application.port.out.*;
import com.aviles.api.escuela.niveles.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

@Service
public class NivelesService implements CreateNivelCase, GetAllNivelesCase,
        CreateGradoCase, GetGradosByNivelCase, GetAllGradosCase, CreateSeccionCase, GetAllSeccionesCase,
        UpdateNivelCase, DeleteNivelCase, UpdateGradoCase, DeleteGradoCase,
        UpdateSeccionCase, DeleteSeccionCase {

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
    public List<Grado> getAllGrados() { return gradoRepositoryPort.findAllGrados(); }

    @Override
    public NivelEducativo update(NivelEducativo nivel) {
        NivelEducativo existente = nivelRepositoryPort.findNivelById(nivel.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nivel no encontrado"));
        return nivelRepositoryPort.save(new NivelEducativo(existente.id(), nivel.nombre(), nivel.descripcion()));
    }

    @Override
    public void deleteNivel(Id id) {
        if (nivelRepositoryPort.findNivelById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nivel no encontrado");
        }
        nivelRepositoryPort.deleteNivelById(id.getValue());
    }

    @Override
    public Grado update(Grado grado) {
        Grado existente = gradoRepositoryPort.findGradoById(grado.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grado no encontrado"));
        return gradoRepositoryPort.save(new Grado(existente.id(), grado.idNivel(), grado.nombreGrado(), grado.descripcion()));
    }

    @Override
    public void deleteGrado(Id id) {
        if (gradoRepositoryPort.findGradoById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grado no encontrado");
        }
        gradoRepositoryPort.deleteGradoById(id.getValue());
    }

    @Override
    public Seccion update(Seccion seccion) {
        Seccion existente = seccionRepositoryPort.findSeccionById(seccion.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sección no encontrada"));
        return seccionRepositoryPort.save(new Seccion(existente.id(), seccion.nombre(), seccion.descripcion()));
    }

    @Override
    public void deleteSeccion(Id id) {
        if (seccionRepositoryPort.findSeccionById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sección no encontrada");
        }
        seccionRepositoryPort.deleteSeccionById(id.getValue());
    }

    @Override
    public Seccion create(Seccion seccion) { return seccionRepositoryPort.save(seccion); }

    @Override
    public List<Seccion> getAllSecciones() { return seccionRepositoryPort.findAllSecciones(); }
}
