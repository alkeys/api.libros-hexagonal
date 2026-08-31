package com.aviles.api.libros.usuarios.application.port.in;

public interface UpdatePassUserCase {
    void updatePassword(String userId, String oldPassword, String newPassword);   
    void updatePasswordLost(String userId, String newPassword,String CodigoConfirmacion); 
}
