package com.aviles.api.libros.usuarios.application.port.out;

import java.util.List;

import com.aviles.api.libros.usuarios.domain.Usuario;

public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
}
