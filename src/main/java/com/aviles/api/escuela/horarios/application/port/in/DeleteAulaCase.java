package com.aviles.api.escuela.horarios.application.port.in;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface DeleteAulaCase {
    void deleteAula(Id id);
}