package com.aviles.api.escuela.profesores.infra.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "departamento")
public class JpaDepartamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    public JpaDepartamento() {}
    public JpaDepartamento(Long id, String nombre, String descripcion) {
        this.id = id; this.nombre = nombre; this.descripcion = descripcion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
