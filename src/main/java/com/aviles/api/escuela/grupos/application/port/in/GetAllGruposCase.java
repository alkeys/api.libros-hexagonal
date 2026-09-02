package com.aviles.api.escuela.grupos.application.port.in;

import java.util.List;
import com.aviles.api.escuela.grupos.domain.Grupo;

public interface GetAllGruposCase {
    List<Grupo> getAll();
}
