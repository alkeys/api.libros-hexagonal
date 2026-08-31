package com.aviles.api.libros.usuarios.infra.adapter.in.web;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aviles.api.libros.usuarios.application.port.in.CreateUserCase;
import com.aviles.api.libros.usuarios.application.port.in.GetAlluserCase;
import com.aviles.api.libros.usuarios.application.port.in.UpdateUserCase;
import com.aviles.api.libros.usuarios.domain.Usuario;
import com.aviles.api.libros.usuarios.domain.values.Contrasema;
import com.aviles.api.libros.usuarios.domain.values.Correo;
import com.aviles.api.libros.usuarios.domain.values.Nombre;
import com.aviles.api.libros.usuarios.infra.adapter.in.web.dto.UsuariosReponse;
import com.aviles.api.libros.usuarios.infra.adapter.in.web.dto.UsuariosRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Operaciones para la gestion de usuarios")
public class UsuarioController {
    private final CreateUserCase createUserCase;
    private final GetAlluserCase getAlluserCase;
    private final UpdateUserCase updateUserCase;

    public UsuarioController(CreateUserCase createUserCase, GetAlluserCase getAlluserCase, 
        UpdateUserCase updateUserCase) {
        this.createUserCase = createUserCase;
        this.getAlluserCase = getAlluserCase;
        this.updateUserCase = updateUserCase;
    }


    /**
     * Crea un nuevo usuario en el sistema.
     */
    @Operation(summary = "Crear un usuario", description = "Registra un nuevo usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado correctamente", content = @Content(schema = @Schema(implementation = UsuariosReponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos", content = @Content())
    })
    @PostMapping
    public UsuariosReponse createUser(@RequestBody UsuariosRequest request ) {
        // Lógica para crear un usuario utilizando createUserCase
        Usuario usuario = createUserCase.createUser(toDomain(request));
        return toReponse(usuario);
    }


    /**
     * Obtiene todos los usuarios registrados en el sistema.
     * @param nada solo logiado ajaa
     * @return
     */
    @Operation(summary = "Obtener todos los usuarios", description = "Recupera una lista de todos los usuarios registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente", content = @Content(schema = @Schema(implementation = UsuariosReponse.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron usuarios", content = @Content())
    })
    @GetMapping("/all")
    public List<UsuariosReponse> getAllUsers() {
        var usuarios = getAlluserCase.getAllUsers();
        return usuarios.stream().map(this::toReponse).collect(Collectors.toList());
    }





    /**
     * Actualiza un usuario existente en el sistema.
     */
    @Operation(summary = "Actualizar un usuario", description = "Modifica los datos de un usuario existente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente", content = @Content(schema = @Schema(implementation = UsuariosReponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content())
    })
    @PutMapping("/{id}")
    public UsuariosReponse updateUser(@PathVariable String id, @RequestBody UsuariosRequest request, @RequestParam String password) {
        // Lógica para actualizar un usuario utilizando updateUserCase
        Usuario usuario = updateUserCase.updateUser(toDomain(request),UUID.fromString(id),password);
        return toReponse(usuario);
    }


private Usuario toDomain(UsuariosRequest request) {
    return new Usuario(
        null,
        new Nombre(request.nombre_usuario()),
        new Correo(request.correo()),
        new Contrasema(request.contrasena_hash())
    );
}

    private UsuariosReponse toReponse(Usuario usuario) {
        return new UsuariosReponse(
                usuario.id(),
                usuario.nombre_usuario().getNombre(),
                usuario.correo().getCorreo()
        );
    }
    
    
}
