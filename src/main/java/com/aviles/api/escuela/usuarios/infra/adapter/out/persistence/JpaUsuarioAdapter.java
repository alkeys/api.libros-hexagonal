package com.aviles.api.escuela.usuarios.infra.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.usuarios.application.port.out.*;
import com.aviles.api.escuela.usuarios.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaUsuarioAdapter implements UsuarioRepositoryPort, RolRepositoryPort, PermisoRepositoryPort {

    private final SpringDataUsuarioRepository usuarioRepo;
    private final SpringDataRolRepository rolRepo;
    private final SpringDataPermisoRepository permisoRepo;

    public JpaUsuarioAdapter(SpringDataUsuarioRepository usuarioRepo, SpringDataRolRepository rolRepo, SpringDataPermisoRepository permisoRepo) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
        this.permisoRepo = permisoRepo;
    }

    @Override
    public Usuario save(Usuario u) { return toDomainUsuario(usuarioRepo.save(toJpaUsuario(u))); }
    @Override
    public List<Usuario> findAllUsuarios() { return usuarioRepo.findAll().stream().map(this::toDomainUsuario).collect(Collectors.toList()); }
    @Override
    public Rol save(Rol r) { return toDomainRol(rolRepo.save(toJpaRol(r))); }
    @Override
    public List<Rol> findAllRoles() { return rolRepo.findAll().stream().map(this::toDomainRol).collect(Collectors.toList()); }
    @Override
    public Permiso save(Permiso p) { return toDomainPermiso(permisoRepo.save(toJpaPermiso(p))); }
    @Override
    public List<Permiso> findAllPermisos() { return permisoRepo.findAll().stream().map(this::toDomainPermiso).collect(Collectors.toList()); }

    private Usuario toDomainUsuario(JpaUsuario j) { return new Usuario(new Id(j.getId()), j.getUsername(), j.getPasswordHash(), j.getCorreo(), j.getEstado(), j.getIntentosFallidos(), j.getFechaCreacion(), j.getFechaActualizacion()); }
    private JpaUsuario toJpaUsuario(Usuario d) { return new JpaUsuario(d.id() != null ? d.id().getValue() : null, d.username(), d.passwordHash(), d.correo(), d.estado(), d.intentosFallidos(), d.fechaCreacion(), d.fechaActualizacion()); }
    private Rol toDomainRol(JpaRol j) { return new Rol(new Id(j.getId()), j.getNombre(), j.getDescripcion()); }
    private JpaRol toJpaRol(Rol d) { return new JpaRol(d.id() != null ? d.id().getValue() : null, d.nombre(), d.descripcion()); }
    private Permiso toDomainPermiso(JpaPermiso j) { return new Permiso(new Id(j.getId()), j.getNombre(), j.getDescripcion()); }
    private JpaPermiso toJpaPermiso(Permiso d) { return new JpaPermiso(d.id() != null ? d.id().getValue() : null, d.nombre(), d.descripcion()); }
}
