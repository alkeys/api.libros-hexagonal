package com.aviles.api.escuela.representantes.application.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.aviles.api.escuela.representantes.application.port.in.*;
import com.aviles.api.escuela.representantes.application.port.out.RepresentanteRepositoryPort;
import com.aviles.api.escuela.representantes.domain.Representante;

@Service
public class RepresentanteService implements CreateRepresentanteCase, GetAllRepresentantesCase {
    private final RepresentanteRepositoryPort representanteRepositoryPort;

    public RepresentanteService(RepresentanteRepositoryPort representanteRepositoryPort) {
        this.representanteRepositoryPort = representanteRepositoryPort;
    }

    @Override
    public Representante create(Representante representante) { return representanteRepositoryPort.save(representante); }

    @Override
    public List<Representante> getAll() { return representanteRepositoryPort.findAll(); }
}
