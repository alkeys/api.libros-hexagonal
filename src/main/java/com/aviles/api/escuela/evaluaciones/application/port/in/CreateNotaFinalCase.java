package com.aviles.api.escuela.evaluaciones.application.port.in;

import com.aviles.api.escuela.evaluaciones.domain.NotaFinal;

public interface CreateNotaFinalCase {
    NotaFinal create(NotaFinal notaFinal);
}
