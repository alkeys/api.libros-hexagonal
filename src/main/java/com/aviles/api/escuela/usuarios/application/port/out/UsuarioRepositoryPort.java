package com.aviles.api.escuela.usuarios.application.port.out;

import java.util.List;
import java.util.Optional;
import com.aviles.api.escuela.usuarios.domain.Usuario;

public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);
    List<Usuario> findAllUsuarios();
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findById(Long id);
    void deleteById(Long id);
}