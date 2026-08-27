package com.aviles.api.libros.usuarios.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aviles.api.libros.usuarios.application.port.in.CreateUserCase;
import com.aviles.api.libros.usuarios.application.port.in.GetAlluserCase;
import com.aviles.api.libros.usuarios.application.port.out.UsuarioRepositoryPort;
import com.aviles.api.libros.usuarios.domain.Usuario;

@Service
public class UsuarioService implements CreateUserCase ,GetAlluserCase {
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public UsuarioService(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario createUser(Usuario user) {
        return usuarioRepositoryPort.save(user);
    }

    @Override
    public List<Usuario> getAllUsers() {
        return usuarioRepositoryPort.findAll();
    }

    
}
