package com.aviles.api.escuela.estudiantes.application.port.in;

import java.util.List;
import com.aviles.api.escuela.estudiantes.domain.Matricula;
import com.aviles.api.escuela.shared.domain.values.Id;

public interface GetMatriculasByGrupoCase {
    List<Matricula> getByGrupo(Id idGrupo);
}
