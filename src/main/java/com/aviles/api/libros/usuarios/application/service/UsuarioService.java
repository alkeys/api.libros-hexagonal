package com.aviles.api.libros.usuarios.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.aviles.api.libros.usuarios.application.port.in.CreateUserCase;
import com.aviles.api.libros.usuarios.application.port.in.GetAlluserCase;
import com.aviles.api.libros.usuarios.application.port.in.UpdatePassUserCase;
import com.aviles.api.libros.usuarios.application.port.in.UpdateUserCase;
import com.aviles.api.libros.usuarios.application.port.out.UsuarioRepositoryPort;
import com.aviles.api.libros.usuarios.domain.Usuario;
import com.aviles.api.libros.utils.Code.GenerateCode;

@Service
public class UsuarioService implements CreateUserCase ,GetAlluserCase, UpdateUserCase,UpdatePassUserCase {
    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final GenerateCode generateCode;

    public UsuarioService(UsuarioRepositoryPort usuarioRepositoryPort, GenerateCode generateCode) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.generateCode = generateCode;
    }

    @Override
    public Usuario createUser(Usuario user) {
        return usuarioRepositoryPort.save(user);
    }

    @Override
    public List<Usuario> getAllUsers() {
        return usuarioRepositoryPort.findAll();
    }

    @Override
    public  Usuario updateUser(Usuario usuario,UUID id,String password) {
        return usuarioRepositoryPort.update(usuario,id,password);
    }

    @Override
    public void updatePassword(String userId, String oldPassword, String newPassword) {
    
    }

    @Override
    public void updatePasswordLost(String userId, String newPassword,String CodigoConfirmacion) {
        //se implementa el codigo de confirmacion para poder cambiar la contraseña del usuario  
        //y se guarda en cache para poder validar que el codigo de 
        // confirmacion es el mismo que se envio al correo del usuario
        String generatedCode = generateCode.generateConfirmationCode();
        System.out.println("Codigo de confirmacion: " + generatedCode);
        System.out.println("Codigo de confirmacion recibido: " + CodigoConfirmacion);

    }
    
}
