package com.aviles.api.escuela.usuarios.infra.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class JpaRol {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    public JpaRol() {}
    public JpaRol(Long id, String nombre, String descripcion) {
        this.id = id; this.nombre = nombre; this.descripcion = descripcion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
