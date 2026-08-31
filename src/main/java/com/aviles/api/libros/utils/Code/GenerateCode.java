package com.aviles.api.libros.utils.Code;

import org.springframework.stereotype.Component;

/**
 * genera codigos de confirmacion para la recuperacion de contrasena
 * GenerateCode
 */
@SuppressWarnings("unused")
//se inyecta para poder hacer uso de la clase en el servicio de usuario
@Component
public class GenerateCode {

    public static String generateConfirmationCode() {
        // Genera un código de confirmación aleatorio de 6 dígitos
        int code = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(code);
    }
    
}
