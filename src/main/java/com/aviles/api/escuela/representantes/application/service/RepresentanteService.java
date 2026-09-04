package com.aviles.api.escuela.representantes.application.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.aviles.api.escuela.representantes.application.port.in.*;
import com.aviles.api.escuela.representantes.application.port.out.RepresentanteRepositoryPort;
import com.aviles.api.escuela.representantes.domain.Representante;
import com.aviles.api.escuela.shared.domain.values.Id;

@Service
public class RepresentanteService implements CreateRepresentanteCase, GetAllRepresentantesCase,
        UpdateRepresentanteCase, DeleteRepresentanteCase {
    private final RepresentanteRepositoryPort representanteRepositoryPort;

    public RepresentanteService(RepresentanteRepositoryPort representanteRepositoryPort) {
        this.representanteRepositoryPort = representanteRepositoryPort;
    }

    @Override
    public Representante create(Representante representante) { return representanteRepositoryPort.save(representante); }

    @Override
    public List<Representante> getAll() { return representanteRepositoryPort.findAll(); }

    @Override
    public Representante update(Representante representante) {
        Representante existente = representanteRepositoryPort.findById(representante.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Representante no encontrado"));
        Representante actualizado = new Representante(existente.id(), representante.nombres(), representante.apellidos(),
                representante.dui(), representante.correoElectronico(), representante.telefono(),
                representante.telefonoAlternativo(), representante.direccion(), representante.ocupacion(), existente.estado());
        return representanteRepositoryPort.save(actualizado);
    }

    @Override
    public void delete(Id id) {
        if (representanteRepositoryPort.findById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Representante no encontrado");
        }
        representanteRepositoryPort.deleteById(id.getValue());
    }
}
