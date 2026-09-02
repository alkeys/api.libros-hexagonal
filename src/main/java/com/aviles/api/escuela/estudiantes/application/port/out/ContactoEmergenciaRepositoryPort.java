package com.aviles.api.escuela.estudiantes.application.port.out;

import com.aviles.api.escuela.estudiantes.domain.ContactoEmergencia;

public interface ContactoEmergenciaRepositoryPort {
    ContactoEmergencia save(ContactoEmergencia contacto);
}
