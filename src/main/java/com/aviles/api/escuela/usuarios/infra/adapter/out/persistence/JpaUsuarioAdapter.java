package com.aviles.api.escuela.usuarios.infra.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.aviles.api.escuela.usuarios.application.port.out.*;
import com.aviles.api.escuela.usuarios.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;

@Component
public class JpaUsuarioAdapter implements UsuarioRepositoryPort, RolRepositoryPort, PermisoRepositoryPort, UsuarioVinculoRepositoryPort, UsuarioRolRepositoryPort {

    private final SpringDataUsuarioRepository usuarioRepo;
    private final SpringDataRolRepository rolRepo;
    private final SpringDataPermisoRepository permisoRepo;
    private final SpringDataUsuarioProfesorRepository usuarioProfesorRepo;
    private final SpringDataUsuarioEstudianteRepository usuarioEstudianteRepo;
    private final SpringDataUsuarioRolRepository usuarioRolRepo;

    public JpaUsuarioAdapter(SpringDataUsuarioRepository usuarioRepo, SpringDataRolRepository rolRepo, SpringDataPermisoRepository permisoRepo,
                             SpringDataUsuarioProfesorRepository usuarioProfesorRepo, SpringDataUsuarioEstudianteRepository usuarioEstudianteRepo,
                             SpringDataUsuarioRolRepository usuarioRolRepo) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
        this.permisoRepo = permisoRepo;
        this.usuarioProfesorRepo = usuarioProfesorRepo;
        this.usuarioEstudianteRepo = usuarioEstudianteRepo;
        this.usuarioRolRepo = usuarioRolRepo;
    }

    @Override
    public Usuario save(Usuario u) { return toDomainUsuario(usuarioRepo.save(toJpaUsuario(u))); }
    @Override
    public List<Usuario> findAllUsuarios() { return usuarioRepo.findAll().stream().map(this::toDomainUsuario).collect(Collectors.toList()); }
    @Override
    public Optional<Usuario> findByUsername(String username) { return usuarioRepo.findByUsername(username).map(this::toDomainUsuario); }
    @Override
    public Optional<Usuario> findByCorreo(String correo) { return usuarioRepo.findByCorreo(correo).map(this::toDomainUsuario); }
    @Override
    public Optional<Usuario> findById(Long id) { return usuarioRepo.findById(id).map(this::toDomainUsuario); }
    @Override
    public void deleteById(Long id) { usuarioRepo.deleteById(id); }

    @Override
    public Optional<Long> findProfesorIdByUsuario(Id idUsuario) {
        return usuarioProfesorRepo.findByIdUsuario(idUsuario.getValue()).map(JpaUsuarioProfesor::getIdProfesor);
    }
    @Override
    public Optional<Long> findEstudianteIdByUsuario(Id idUsuario) {
        return usuarioEstudianteRepo.findByIdUsuario(idUsuario.getValue()).map(JpaUsuarioEstudiante::getIdEstudiante);
    }
    @Override
    public void saveVinculoProfesor(Id idUsuario, Long idProfesor) {
        usuarioProfesorRepo.findByIdUsuario(idUsuario.getValue())
                .ifPresentOrElse(v -> { v.setIdProfesor(idProfesor); usuarioProfesorRepo.save(v); },
                        () -> usuarioProfesorRepo.save(new JpaUsuarioProfesor(idUsuario.getValue(), idProfesor)));
    }
    @Override
    public void saveVinculoEstudiante(Id idUsuario, Long idEstudiante) {
        usuarioEstudianteRepo.findByIdUsuario(idUsuario.getValue())
                .ifPresentOrElse(v -> { v.setIdEstudiante(idEstudiante); usuarioEstudianteRepo.save(v); },
                        () -> usuarioEstudianteRepo.save(new JpaUsuarioEstudiante(idUsuario.getValue(), idEstudiante)));
    }
    @Override
    public List<String> findRolNamesByUsuario(Id idUsuario) {
        return usuarioRolRepo.findRolNamesByUsuario(idUsuario.getValue());
    }
    @Override
    public void assignRol(Id idUsuario, Id idRol) {
        if (usuarioRolRepo.findByIdUsuarioAndIdRol(idUsuario.getValue(), idRol.getValue()).isEmpty()) {
            usuarioRolRepo.save(new JpaUsuarioRol(idUsuario.getValue(), idRol.getValue()));
        }
    }
    @Override
    public void removeRol(Id idUsuario, Id idRol) {
        usuarioRolRepo.deleteByIdUsuarioAndIdRol(idUsuario.getValue(), idRol.getValue());
    }
    @Override
    public Rol save(Rol r) { return toDomainRol(rolRepo.save(toJpaRol(r))); }
    @Override
    public List<Rol> findAllRoles() { return rolRepo.findAll().stream().map(this::toDomainRol).collect(Collectors.toList()); }
    @Override
    public Optional<Rol> findRolByNombre(String nombre) { return rolRepo.findByNombre(nombre).map(this::toDomainRol); }
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
