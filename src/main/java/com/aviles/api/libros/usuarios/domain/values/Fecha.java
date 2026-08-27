package com.aviles.api.libros.usuarios.domain.values;

import java.time.OffsetDateTime;

import lombok.Value;


@Value
public class Fecha {
     private final  OffsetDateTime fecha;

    public Fecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public static Fecha now() {
        return new Fecha(OffsetDateTime.now());
    }
}
