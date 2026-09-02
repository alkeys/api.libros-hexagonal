package com.aviles.api.escuela.estudiantes.application.port.in;

import com.aviles.api.escuela.estudiantes.domain.ContactoEmergencia;

public interface CreateContactoEmergenciaCase {
    ContactoEmergencia create(ContactoEmergencia contacto);
}
