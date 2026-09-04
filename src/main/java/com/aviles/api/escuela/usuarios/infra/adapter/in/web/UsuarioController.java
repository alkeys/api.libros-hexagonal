package com.aviles.api.escuela.usuarios.infra.adapter.in.web;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import com.aviles.api.escuela.usuarios.application.port.in.*;
import com.aviles.api.escuela.usuarios.domain.*;
import com.aviles.api.escuela.usuarios.infra.adapter.in.web.dto.*;
import com.aviles.api.escuela.auth.application.JwtService;
import com.aviles.api.escuela.auth.domain.AuthUser;
import com.aviles.api.escuela.shared.domain.values.Id;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Gestión de usuarios, roles, permisos e inicio de sesión")
public class UsuarioController {

    private final CreateUsuarioCase createUsuarioCase;
    private final GetAllUsuariosCase getAllUsuariosCase;
    private final UpdateUsuarioCase updateUsuarioCase;
    private final DeleteUsuarioCase deleteUsuarioCase;
    private final LoginCase loginCase;
    private final GetVinculoUsuarioCase getVinculoUsuarioCase;
    private final GetRolesUsuarioCase getRolesUsuarioCase;
    private final UpdateRolesUsuarioCase updateRolesUsuarioCase;
    private final GetAllRolesCase getAllRolesCase;
    private final JwtService jwtService;

    public UsuarioController(CreateUsuarioCase createUsuarioCase, GetAllUsuariosCase getAllUsuariosCase,
                             UpdateUsuarioCase updateUsuarioCase, DeleteUsuarioCase deleteUsuarioCase,
                             LoginCase loginCase, GetVinculoUsuarioCase getVinculoUsuarioCase,
                             GetRolesUsuarioCase getRolesUsuarioCase, UpdateRolesUsuarioCase updateRolesUsuarioCase,
                             GetAllRolesCase getAllRolesCase, JwtService jwtService) {
        this.createUsuarioCase = createUsuarioCase;
        this.getAllUsuariosCase = getAllUsuariosCase;
        this.updateUsuarioCase = updateUsuarioCase;
        this.deleteUsuarioCase = deleteUsuarioCase;
        this.loginCase = loginCase;
        this.getVinculoUsuarioCase = getVinculoUsuarioCase;
        this.getRolesUsuarioCase = getRolesUsuarioCase;
        this.updateRolesUsuarioCase = updateRolesUsuarioCase;
        this.getAllRolesCase = getAllRolesCase;
        this.jwtService = jwtService;
    }

    @Operation(summary = "Crear usuario")
    @PostMapping
    public UsuarioResponse create(@RequestBody UsuarioRequest request) {
        Usuario usuario = Usuario.nuevo(request.username(), request.password(), request.correo());
        Usuario creado = createUsuarioCase.create(usuario);
        return toResponse(creado);
    }

    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public List<UsuarioResponse> getAll() {
        return getAllUsuariosCase.getAllUsuarios().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Operation(summary = "Actualizar usuario")
    @PutMapping("/{id}")
    public UsuarioResponse update(@PathVariable Long id, @RequestBody UsuarioRequest request) {
        Usuario usuario = new Usuario(new Id(id), request.username(), request.password(), request.correo(),
                null, null, null, null);
        Usuario actualizado = updateUsuarioCase.update(usuario);
        return toResponse(actualizado);
    }

    @Operation(summary = "Eliminar usuario")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteUsuarioCase.delete(new Id(id));
    }

    @Operation(summary = "Listar todos los roles del sistema")
    @GetMapping("/roles")
    public List<RolResponse> getAllRoles() {
        return getAllRolesCase.getAllRoles().stream()
                .map(r -> new RolResponse(r.id().getValue(), r.nombre(), r.descripcion()))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener los roles asignados a un usuario")
    @GetMapping("/{id}/roles")
    public List<String> getRolesUsuario(@PathVariable Long id) {
        return getRolesUsuarioCase.getRolesAsignados(new Id(id));
    }

    @Operation(summary = "Reemplazar los roles de un usuario")
    @PutMapping("/{id}/roles")
    public List<String> updateRolesUsuario(@PathVariable Long id, @RequestBody UsuarioRolesRequest request) {
        return updateRolesUsuarioCase.updateRoles(new Id(id), request.roles());
    }

    @Operation(summary = "Iniciar sesión")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Usuario usuario = loginCase.login(request.username(), request.password());
        VinculoUsuario vinculo = getVinculoUsuarioCase.getVinculo(usuario.id());
        List<String> roles = getRolesUsuarioCase.getRoles(usuario.id());
        AuthUser authUser = new AuthUser(usuario.id().getValue(), usuario.username(), roles,
                vinculo.idProfesor(), vinculo.idEstudiante());
        String token = jwtService.generateToken(authUser);
        return new LoginResponse(usuario.id().getValue(), usuario.username(), usuario.correo(),
                usuario.estado(), token, roles, vinculo.idProfesor(), vinculo.idEstudiante());
    }

    @Operation(summary = "Obtener vínculo del usuario con profesor o estudiante")
    @GetMapping("/{id}/vinculo")
    public VinculoResponse getVinculo(@PathVariable Long id) {
        VinculoUsuario vinculo = getVinculoUsuarioCase.getVinculo(new Id(id));
        return new VinculoResponse(vinculo.idProfesor(), vinculo.idEstudiante());
    }

    private UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(u.id().getValue(), u.username(), u.correo(), u.estado(),
                getRolesUsuarioCase.getRolesAsignados(u.id()));
    }
}