package com.aviles.api.escuela.materias.application.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.aviles.api.escuela.materias.application.port.in.*;
import com.aviles.api.escuela.materias.application.port.out.MateriaRepositoryPort;
import com.aviles.api.escuela.materias.domain.Materia;

@Service
public class MateriaService implements CreateMateriaCase, GetAllMateriasCase {
    private final MateriaRepositoryPort materiaRepositoryPort;

    public MateriaService(MateriaRepositoryPort materiaRepositoryPort) {
        this.materiaRepositoryPort = materiaRepositoryPort;
    }

    @Override
    public Materia create(Materia materia) { return materiaRepositoryPort.save(materia); }

    @Override
    public List<Materia> getAll() { return materiaRepositoryPort.findAll(); }
}
