package com.aviles.api.escuela.usuarios.application.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.aviles.api.escuela.usuarios.application.port.in.*;
import com.aviles.api.escuela.usuarios.application.port.out.*;
import com.aviles.api.escuela.usuarios.domain.*;
import com.aviles.api.escuela.shared.domain.values.Id;
import com.aviles.api.escuela.auth.application.AuthContext;
import com.aviles.api.escuela.auth.domain.AuthUser;

@Service
public class UsuarioService implements CreateUsuarioCase, GetAllUsuariosCase,
        CreateRolCase, GetAllRolesCase, CreatePermisoCase, LoginCase, GetVinculoUsuarioCase,
        UpdateUsuarioCase, DeleteUsuarioCase, GetRolesUsuarioCase, UpdateRolesUsuarioCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final RolRepositoryPort rolRepositoryPort;
    private final PermisoRepositoryPort permisoRepositoryPort;
    private final UsuarioVinculoRepositoryPort vinculoRepositoryPort;
    private final UsuarioRolRepositoryPort usuarioRolRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepositoryPort usuarioRepositoryPort, RolRepositoryPort rolRepositoryPort,
                          PermisoRepositoryPort permisoRepositoryPort, UsuarioVinculoRepositoryPort vinculoRepositoryPort,
                          UsuarioRolRepositoryPort usuarioRolRepositoryPort,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.rolRepositoryPort = rolRepositoryPort;
        this.permisoRepositoryPort = permisoRepositoryPort;
        this.vinculoRepositoryPort = vinculoRepositoryPort;
        this.usuarioRolRepositoryPort = usuarioRolRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario create(Usuario usuario) {
        Usuario conHash = new Usuario(usuario.id(), usuario.username(),
                passwordEncoder.encode(usuario.passwordHash()),
                usuario.correo(), usuario.estado(), usuario.intentosFallidos(),
                usuario.fechaCreacion(), usuario.fechaActualizacion());
        return usuarioRepositoryPort.save(conHash);
    }

    @Override
    public List<Usuario> getAllUsuarios() { return usuarioRepositoryPort.findAllUsuarios(); }

    @Override
    public Usuario login(String username, String password) {
        Optional<Usuario> usuario = usuarioRepositoryPort.findByUsername(username);
        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }
        Usuario u = usuario.get();
        boolean valida;
        if (esHashBcrypt(u.passwordHash())) {
            valida = passwordEncoder.matches(password, u.passwordHash());
        } else {
            // Migración: contraseñas guardadas en texto plano antes de BCrypt.
            // Se valida contra el hash actual y se actualiza automáticamente a BCrypt.
            valida = password.equals(u.passwordHash());
            if (valida) {
                Usuario migrado = new Usuario(u.id(), u.username(), passwordEncoder.encode(password),
                        u.correo(), u.estado(), u.intentosFallidos(), u.fechaCreacion(), OffsetDateTime.now());
                usuarioRepositoryPort.save(migrado);
            }
        }
        if (!valida) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }
        if (!"ACTIVO".equalsIgnoreCase(u.estado())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El usuario está inactivo");
        }
        return u;
    }

    private boolean esHashBcrypt(String hash) {
        return hash != null && hash.startsWith("$2");
    }

    @Override
    public VinculoUsuario getVinculo(Id idUsuario) {
        Long idProfesor = vinculoRepositoryPort.findProfesorIdByUsuario(idUsuario).orElse(null);
        Long idEstudiante = vinculoRepositoryPort.findEstudianteIdByUsuario(idUsuario).orElse(null);
        return new VinculoUsuario(idProfesor, idEstudiante);
    }

    @Override
    public List<String> getRoles(Id idUsuario) {
        List<String> roles = new ArrayList<>(usuarioRolRepositoryPort.findRolNamesByUsuario(idUsuario));
        // Si no tiene roles asignados, por defecto ESTUDIANTE (acceso básico)
        if (roles.isEmpty()) {
            roles.add("ESTUDIANTE");
        }
        return roles;
    }

    @Override
    public List<String> getRolesAsignados(Id idUsuario) {
        return usuarioRolRepositoryPort.findRolNamesByUsuario(idUsuario);
    }

    @Override
    public List<String> updateRoles(Id idUsuario, List<String> roles) {
        if (usuarioRepositoryPort.findById(idUsuario.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        // Normalizar: mayúsculas, sin espacios vacíos ni duplicados
        Set<String> objetivo = new LinkedHashSet<>();
        if (roles != null) {
            for (String r : roles) {
                String limpio = r == null ? "" : r.trim().toUpperCase();
                if (!limpio.isEmpty()) objetivo.add(limpio);
            }
        }
        // Validar que todos los roles solicitados existan
        for (String nombre : objetivo) {
            if (rolRepositoryPort.findRolByNombre(nombre).isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El rol " + nombre + " no existe");
            }
        }
        // Evitar auto-bloqueo: el admin no puede quitarse su propio rol ADMIN
        AuthUser autenticado = AuthContext.get();
        boolean soyElAdminActual = autenticado != null
                && autenticado.id().equals(idUsuario.getValue())
                && autenticado.hasRole("ADMIN");
        if (soyElAdminActual && !objetivo.contains("ADMIN")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No puedes quitarte tu propio rol de administrador");
        }
        List<String> actuales = usuarioRolRepositoryPort.findRolNamesByUsuario(idUsuario);
        // Quitar roles que ya no corresponden
        for (String actual : actuales) {
            if (!objetivo.contains(actual)) {
                rolRepositoryPort.findRolByNombre(actual)
                        .ifPresent(rol -> usuarioRolRepositoryPort.removeRol(idUsuario, rol.id()));
            }
        }
        // Asignar los nuevos
        for (String nombre : objetivo) {
            if (!actuales.contains(nombre)) {
                rolRepositoryPort.findRolByNombre(nombre)
                        .ifPresent(rol -> usuarioRolRepositoryPort.assignRol(idUsuario, rol.id()));
            }
        }
        return usuarioRolRepositoryPort.findRolNamesByUsuario(idUsuario);
    }

    @Override
    public Rol create(Rol rol) { return rolRepositoryPort.save(rol); }

    @Override
    public List<Rol> getAllRoles() { return rolRepositoryPort.findAllRoles(); }

    @Override
    public Permiso create(Permiso permiso) { return permisoRepositoryPort.save(permiso); }

    @Override
    public Usuario update(Usuario usuario) {
        Usuario existente = usuarioRepositoryPort.findById(usuario.id().getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        String password = (usuario.passwordHash() == null || usuario.passwordHash().isBlank())
                ? existente.passwordHash()
                : passwordEncoder.encode(usuario.passwordHash());
        Usuario actualizado = new Usuario(existente.id(), usuario.username(), password,
                usuario.correo(), existente.estado(), existente.intentosFallidos(),
                existente.fechaCreacion(), OffsetDateTime.now());
        return usuarioRepositoryPort.save(actualizado);
    }

    @Override
    public void delete(Id id) {
        if (usuarioRepositoryPort.findById(id.getValue()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        usuarioRepositoryPort.deleteById(id.getValue());
    }
}
