package com.aviles.api.libros.usuarios.infra.adapter.out.persistence.adpter;

import java.util.List;
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
    return new UsuarioEntity(
        null,
        usuario.nombre_usuario().getNombre(),
        usuario.correo().getCorreo(),
        usuario.contrasena_hash().getContrasena(),
        usuario.fecha_creacion().getFecha(),
        usuario.fecha_actualizacion().getFecha()
    );
}
}
