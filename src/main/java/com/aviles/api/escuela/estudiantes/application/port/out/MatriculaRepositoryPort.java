package com.aviles.api.escuela.estudiantes.application.port.out;

import java.util.List;
import com.aviles.api.escuela.estudiantes.domain.Matricula;
import com.aviles.api.escuela.shared.domain.values.Id;

public interface MatriculaRepositoryPort {
    Matricula save(Matricula matricula);
    List<Matricula> findByGrupo(Id idGrupo);
}
