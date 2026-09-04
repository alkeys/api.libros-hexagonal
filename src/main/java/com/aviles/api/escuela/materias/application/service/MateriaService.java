package com.aviles.api.escuela.materias.application.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.aviles.api.escuela.materias.application.port.in.*;
import com.aviles.api.escuela.materias.application.port.out.MateriaRepositoryPort;
import com.aviles.api.escuela.materias.domain.Materia;
import com.aviles.api.escuela.shared.domain.values.Id;

@Service
public class MateriaService implements CreateMateriaCase, GetAllMateriasCase, UpdateMateriaCase, DeleteMateriaCase {
    private final MateriaRepositoryPort materiaRepositoryPort;

    public MateriaService(MateriaRepositoryPort materiaRepositoryPort) {
        this.materiaRepositoryPort = materiaRepositoryPort;
    }

    @Override
    public Materia create(Materia materia) { return materiaRepositoryPort.save(materia); }

    @Override
    public List<Materia> getAll() { return materiaRepositoryPort.findAll(); }

    @Override
    public Materia update(Materia materia) {
        Materia existente = materiaRepositoryPort.findById(materia.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Materia no encontrada"));
        Materia actualizada = new Materia(existente.id(), materia.codigoMateria(), materia.nombreMateria(),
                materia.descripcion(), materia.horasSemanales(), materia.tipo(), existente.estado());
        return materiaRepositoryPort.save(actualizada);
    }

    @Override
    public void delete(Id id) {
        if (materiaRepositoryPort.findById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Materia no encontrada");
        }
        materiaRepositoryPort.deleteById(id.getValue());
    }
}
