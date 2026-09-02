package com.aviles.api.escuela.profesores.application.port.in;

import com.aviles.api.escuela.profesores.domain.Departamento;

public interface CreateDepartamentoCase {
    Departamento create(Departamento departamento);
}
