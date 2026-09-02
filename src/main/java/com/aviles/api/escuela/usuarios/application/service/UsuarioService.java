package com.aviles.api.escuela.usuarios.application.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.aviles.api.escuela.usuarios.application.port.in.*;
import com.aviles.api.escuela.usuarios.application.port.out.*;
import com.aviles.api.escuela.usuarios.domain.*;

@Service
public class UsuarioService implements CreateUsuarioCase, GetAllUsuariosCase,
        CreateRolCase, GetAllRolesCase, CreatePermisoCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final RolRepositoryPort rolRepositoryPort;
    private final PermisoRepositoryPort permisoRepositoryPort;

    public UsuarioService(UsuarioRepositoryPort usuarioRepositoryPort, RolRepositoryPort rolRepositoryPort,
                          PermisoRepositoryPort permisoRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.rolRepositoryPort = rolRepositoryPort;
        this.permisoRepositoryPort = permisoRepositoryPort;
    }

    @Override
    public Usuario create(Usuario usuario) { return usuarioRepositoryPort.save(usuario); }

    @Override
    public List<Usuario> getAllUsuarios() { return usuarioRepositoryPort.findAllUsuarios(); }

    @Override
    public Rol create(Rol rol) { return rolRepositoryPort.save(rol); }

    @Override
    public List<Rol> getAllRoles() { return rolRepositoryPort.findAllRoles(); }

    @Override
    public Permiso create(Permiso permiso) { return permisoRepositoryPort.save(permiso); }
}
