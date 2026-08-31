package com.aviles.api.libros.usuarios.application.port.in;

import java.util.UUID;

import com.aviles.api.libros.usuarios.domain.Usuario;

public interface UpdateUserCase {
    Usuario updateUser(Usuario usuario,UUID id,String password);
}
