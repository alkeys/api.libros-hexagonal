package com.aviles.api.libros.usuarios.infra.adapter.out.persistence.adpter;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.aviles.api.libros.usuarios.application.port.out.UsuarioRepositoryPort;
import com.aviles.api.libros.usuarios.domain.Usuario;
import com.aviles.api.libros.usuarios.domain.values.*;
import com.aviles.api.libros.usuarios.infra.adapter.out.persistence.entity.UsuarioEntity;
import com.aviles.api.libros.usuarios.infra.adapter.out.persistence.repository.SpringDataUsuariosRepository;

@Repository
public class JpaUsuariosRepositoryAdapter  implements UsuarioRepositoryPort {

    private final SpringDataUsuariosRepository springDataUsuariosRepository;

    public JpaUsuariosRepositoryAdapter(SpringDataUsuariosRepository springDataUsuariosRepository) {
        this.springDataUsuariosRepository = springDataUsuariosRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return toDomain(springDataUsuariosRepository.save(toEntity(usuario)));
    }


    @Override
    public List<Usuario> findAll() {
        return springDataUsuariosRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }


    @Override
    public Usuario update(Usuario usuario, UUID id, String password) {
        UsuarioEntity entity = toEntity(usuario);
        entity.setId(id);
        //verificar si la contraseña es la misma que la que se tiene en la 
        // base de datos para poder actualizar el usuario
        if(!entity.getContrasenaHash().equals(password)){
            throw new IllegalArgumentException("La contraseña proporcionada no coincide con la contraseña actual del usuario.");
        }
        UsuarioEntity updatedEntity = springDataUsuariosRepository.save(entity);
        return toDomain(updatedEntity);
    }


    @Override
    public Usuario findById(UUID id) {
        return springDataUsuariosRepository.findById(id)
                .map(this::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el ID: " + id));
    }









    private Usuario toDomain(UsuarioEntity entity) {
       return new Usuario(
           entity.getId().toString(), 
           new Nombre(entity.getNombreUsuario()),
           new Correo(entity.getCorreo()), 
           new Contrasema(entity.getContrasenaHash()),
           new Fecha(entity.getFechaCreacion()),
           new Fecha(entity.getFechaActualizacion())
       );
    }

private UsuarioEntity toEntity(Usuario usuario) {
    UUID id = usuario.id() != null
            ? UUID.fromString(usuario.id())
            : null;

    return new UsuarioEntity(
        id,
        usuario.nombre_usuario().getNombre(),
        usuario.correo().getCorreo(),
        usuario.contrasena_hash().getContrasena(),
        usuario.fecha_creacion().getFecha(),
        usuario.fecha_actualizacion().getFecha()
    );
}
}
