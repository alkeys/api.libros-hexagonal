package com.aviles.api.escuela.horarios.application.port.out;

import java.util.List;
import com.aviles.api.escuela.horarios.domain.BloqueHorario;

public interface BloqueHorarioRepositoryPort {
    BloqueHorario save(BloqueHorario bloque);
    List<BloqueHorario> findAllBloques();
}
