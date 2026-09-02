package com.aviles.api.escuela.grupos.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aviles.api.escuela.grupos.application.port.in.*;
import com.aviles.api.escuela.grupos.application.port.out.GrupoRepositoryPort;
import com.aviles.api.escuela.grupos.domain.Grupo;

@Service
public class GrupoService implements CreateGrupoCase, GetAllGruposCase {

    private final GrupoRepositoryPort grupoRepositoryPort;

    public GrupoService(GrupoRepositoryPort grupoRepositoryPort) {
        this.grupoRepositoryPort = grupoRepositoryPort;
    }

    @Override
    public Grupo create(Grupo grupo) { return grupoRepositoryPort.save(grupo); }

    @Override
    public List<Grupo> getAll() { return grupoRepositoryPort.findAll(); }
}
