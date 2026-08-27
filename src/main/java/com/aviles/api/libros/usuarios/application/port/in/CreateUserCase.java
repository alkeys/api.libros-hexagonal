package com.aviles.api.libros.usuarios.application.port.in;

import com.aviles.api.libros.usuarios.domain.Usuario;

public interface CreateUserCase {
    Usuario createUser(Usuario user);
}
