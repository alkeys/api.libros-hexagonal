package com.aviles.api.libros.libros.domain.values;

import lombok.Value;

@Value
public class Cantidad {
    private final int value;

    public Cantidad(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.value = value;
    }

    // restar libros
    public Cantidad restar(Cantidad otraCantidad) {
        if (otraCantidad == null) {
            throw new IllegalArgumentException("La cantidad a restar no puede ser nula");
        }
        if (this.value < otraCantidad.value) {
            throw new IllegalArgumentException("No hay stock suficiente");
        }
        return new Cantidad(this.value - otraCantidad.value);
    }
}
