package com.aviles.api.escuela.usuarios.application.port.in;

import java.util.List;
import com.aviles.api.escuela.usuarios.domain.Usuario;

public interface GetAllUsuariosCase {
    List<Usuario> getAllUsuarios();
}
