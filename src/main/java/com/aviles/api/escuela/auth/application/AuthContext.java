package com.aviles.api.escuela.auth.application;

import com.aviles.api.escuela.auth.domain.AuthUser;

/**
 * Portador del usuario autenticado en el hilo de la petición actual.
 */
public final class AuthContext {

    private static final ThreadLocal<AuthUser> HOLDER = new ThreadLocal<>();

    private AuthContext() {}

    public static void set(AuthUser user) {
        HOLDER.set(user);
    }

    public static AuthUser get() {
        return HOLDER.get();
    }

    public static void clear() {
        HOLDER.remove();
    }
}