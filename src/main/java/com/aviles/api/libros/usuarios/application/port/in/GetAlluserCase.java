package com.aviles.api.libros.usuarios.application.port.in;

import java.util.List;

import com.aviles.api.libros.usuarios.domain.Usuario;

public interface GetAlluserCase {
    List<Usuario> getAllUsers();
}
