package com.aviles.api.escuela.horarios.application.port.in;

import java.util.List;
import com.aviles.api.escuela.horarios.domain.Aula;

public interface GetAllAulasCase {
    List<Aula> getAllAulas();
}
