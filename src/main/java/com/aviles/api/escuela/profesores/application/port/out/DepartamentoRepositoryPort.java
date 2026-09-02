package com.aviles.api.escuela.profesores.application.port.out;

import java.util.List;
import com.aviles.api.escuela.profesores.domain.Departamento;

public interface DepartamentoRepositoryPort {
    Departamento save(Departamento departamento);
    List<Departamento> findAllDepartamentos();
}
