package com.aviles.api.escuela.usuarios.infra.adapter.in.web;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import com.aviles.api.escuela.usuarios.application.port.in.*;
import com.aviles.api.escuela.usuarios.domain.*;
import com.aviles.api.escuela.usuarios.infra.adapter.in.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Gestión de usuarios, roles y permisos")
public class UsuarioController {

    private final CreateUsuarioCase createUsuarioCase;
    private final GetAllUsuariosCase getAllUsuariosCase;

    public UsuarioController(CreateUsuarioCase createUsuarioCase, GetAllUsuariosCase getAllUsuariosCase) {
        this.createUsuarioCase = createUsuarioCase;
        this.getAllUsuariosCase = getAllUsuariosCase;
    }

    @Operation(summary = "Crear usuario")
    @PostMapping
    public UsuarioResponse create(@RequestBody UsuarioRequest request) {
        Usuario usuario = Usuario.nuevo(request.username(), request.passwordHash(), request.correo());
        return toResponse(createUsuarioCase.create(usuario));
    }

    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public List<UsuarioResponse> getAll() {
        return getAllUsuariosCase.getAllUsuarios().stream().map(this::toResponse).collect(Collectors.toList());
    }

    private UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(u.id().getValue(), u.username(), u.correo(), u.estado());
    }
}
