package com.aviles.api.libros.libros.domain;

import java.time.OffsetDateTime;

import com.aviles.api.libros.libros.domain.values.Cantidad;
import com.aviles.api.libros.libros.domain.values.DataString;
import com.aviles.api.libros.libros.domain.values.Id;

public record Libro(
        Id id,
        DataString titulo,
        DataString autor,
        DataString descripcion,
        DataString url_imagen,
        OffsetDateTime fecha_creacion,
        OffsetDateTime fecha_actualizacion,
        Cantidad cantidad

) {
    public Libro(Id id, DataString titulo, DataString autor, DataString descripcion, DataString url_imagen,
            Cantidad cantidad) {
        this(id, titulo, autor, descripcion, url_imagen, OffsetDateTime.now(), OffsetDateTime.now(), cantidad);
    }


    public Libro(String id,String titulo, String autor, String descripcion, String url_imagen, int cantidad) {
        this(new Id(id), new DataString(titulo), new DataString(autor), new DataString(descripcion),
                new DataString(url_imagen), OffsetDateTime.now(), OffsetDateTime.now(), new Cantidad(cantidad));
    }



    public Libro prestarLibro() {
        return new Libro(
                this.id,
                this.titulo,
                this.autor,
                this.descripcion,
                this.url_imagen,
                this.fecha_creacion,
                OffsetDateTime.now(),
                this.cantidad.restar(new Cantidad(1)));
    }

}
