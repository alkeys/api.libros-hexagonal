package com.aviles.api.escuela.usuarios.application.port.in;

import com.aviles.api.escuela.shared.domain.values.Id;

public interface GetVinculoUsuarioCase {
    VinculoUsuario getVinculo(Id idUsuario);
}