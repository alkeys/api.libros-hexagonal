package com.aviles.api.escuela.profesores.application.port.in;

import java.util.List;
import com.aviles.api.escuela.profesores.domain.Departamento;

public interface GetAllDepartamentosCase {
    List<Departamento> getAllDepartamentos();
}
