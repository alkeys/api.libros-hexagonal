package com.aviles.api.escuela.niveles.application.port.in;

import java.util.List;
import com.aviles.api.escuela.niveles.domain.Grado;

public interface GetAllGradosCase {
    List<Grado> getAllGrados();
}
