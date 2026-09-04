package com.aviles.api.escuela.grupos.application.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aviles.api.escuela.grupos.application.port.in.*;
import com.aviles.api.escuela.grupos.application.port.out.GrupoRepositoryPort;
import com.aviles.api.escuela.grupos.domain.Grupo;
import com.aviles.api.escuela.shared.domain.values.Id;

@Service
public class GrupoService implements CreateGrupoCase, GetAllGruposCase, UpdateGrupoCase, DeleteGrupoCase {

    private final GrupoRepositoryPort grupoRepositoryPort;

    public GrupoService(GrupoRepositoryPort grupoRepositoryPort) {
        this.grupoRepositoryPort = grupoRepositoryPort;
    }

    @Override
    public Grupo create(Grupo grupo) { return grupoRepositoryPort.save(grupo); }

    @Override
    public List<Grupo> getAll() { return grupoRepositoryPort.findAll(); }

    @Override
    public Grupo update(Grupo grupo) {
        Grupo existente = grupoRepositoryPort.findById(grupo.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo no encontrado"));
        Grupo actualizado = new Grupo(existente.id(), grupo.idGrado(), grupo.idSeccion(), grupo.idAnioEscolar(),
                grupo.nombre(), grupo.capacidad(), grupo.turno(), existente.estado());
        return grupoRepositoryPort.save(actualizado);
    }

    @Override
    public void delete(Id id) {
        if (grupoRepositoryPort.findById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo no encontrado");
        }
        grupoRepositoryPort.deleteById(id.getValue());
    }
}
