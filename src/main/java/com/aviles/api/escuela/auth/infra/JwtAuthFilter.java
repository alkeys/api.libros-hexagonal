package com.aviles.api.escuela.auth.infra;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aviles.api.escuela.auth.application.AuthContext;
import com.aviles.api.escuela.auth.application.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro que lee el token JWT del header Authorization (Bearer) y,
 * si es válido, lo coloca en el AuthContext para la petición actual.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith(BEARER_PREFIX)) {
            try {
                AuthContext.set(jwtService.parseToken(header.substring(BEARER_PREFIX.length())));
            } catch (Exception e) {
                // Token inválido: se deja sin autenticar; el interceptor responde 401/403.
                AuthContext.clear();
            }
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            AuthContext.clear();
        }
    }
}