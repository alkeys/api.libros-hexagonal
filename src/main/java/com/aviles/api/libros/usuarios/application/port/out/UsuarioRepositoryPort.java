package com.aviles.api.libros.usuarios.application.port.out;

import java.util.List;
import java.util.UUID;

import com.aviles.api.libros.usuarios.domain.Usuario;

public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
    Usuario  update(Usuario usuario,UUID id,String password);
    Usuario findById(UUID id);    
}
