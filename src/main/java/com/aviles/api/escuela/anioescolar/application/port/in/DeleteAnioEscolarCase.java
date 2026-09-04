package com.aviles.api.escuela.anioescolar.application.port.in;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface DeleteAnioEscolarCase {
    void deleteAnioEscolar(Id id);
}