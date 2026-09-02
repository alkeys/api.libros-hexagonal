package com.aviles.api.escuela.evaluaciones.application.port.out;

import java.util.List;
import com.aviles.api.escuela.evaluaciones.domain.NotaFinal;

public interface NotaFinalRepositoryPort {
    NotaFinal save(NotaFinal notaFinal);
    List<NotaFinal> findAllNotasFinales();
}
