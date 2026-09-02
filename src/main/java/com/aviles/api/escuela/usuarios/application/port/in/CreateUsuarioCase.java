package com.aviles.api.escuela.usuarios.application.port.in;

import com.aviles.api.escuela.usuarios.domain.Usuario;

public interface CreateUsuarioCase {
    Usuario create(Usuario usuario);
}
