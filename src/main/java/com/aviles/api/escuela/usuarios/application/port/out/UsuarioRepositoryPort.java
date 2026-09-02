package com.aviles.api.escuela.usuarios.application.port.out;

import java.util.List;
import com.aviles.api.escuela.usuarios.domain.Usuario;

public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);
    List<Usuario> findAllUsuarios();
}
