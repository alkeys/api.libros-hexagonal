package com.aviles.api.escuela.utils.Code;

import org.springframework.stereotype.Component;

/**
 * Generador de códigos únicos para el sistema escolar.
 * Utilizado para generar códigos de estudiante, profesor, materia, etc.
 */
@Component
public class GenerateCode {

    /**
     * Genera un código de confirmación aleatorio de 6 dígitos.
     */
    public static String generateConfirmationCode() {
        int code = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(code);
    }

    /**
     * Genera un código de estudiante con prefijo y número aleatorio.
     * @param prefix Prefijo del código (ej: "EST", "PRO")
     */
    public static String generateEntityCode(String prefix) {
        int code = (int) (Math.random() * 900000) + 100000;
        return prefix + "-" + code;
    }
}
