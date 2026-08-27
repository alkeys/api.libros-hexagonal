package com.aviles.api.libros.usuarios.domain;

import com.aviles.api.libros.usuarios.domain.values.Contrasema;
import com.aviles.api.libros.usuarios.domain.values.Correo;
import com.aviles.api.libros.usuarios.domain.values.Fecha;
import com.aviles.api.libros.usuarios.domain.values.Nombre;

public record Usuario(
    String id,
    Nombre nombre_usuario,
    Correo correo,
    Contrasema contrasena_hash,
    Fecha fecha_creacion,
    Fecha fecha_actualizacion
) {
 public Usuario(
     String id,
     Nombre nombre_usuario,
     Correo correo,
     Contrasema contrasena_hash
 ) {
     this(id, nombre_usuario, correo, contrasena_hash, 
        Fecha.now(), Fecha.now());
 }

    
}
