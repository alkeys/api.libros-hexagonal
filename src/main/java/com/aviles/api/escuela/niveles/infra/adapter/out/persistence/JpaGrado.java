package com.aviles.api.escuela.niveles.infra.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "grado")
public class JpaGrado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado")
    private Long id;

    @Column(name = "id_nivel", nullable = false)
    private Long idNivel;

    @Column(name = "nombre_grado", nullable = false, unique = true, length = 50)
    private String nombreGrado;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    public JpaGrado() {}
    public JpaGrado(Long id, Long idNivel, String nombreGrado, String descripcion) {
        this.id = id; this.idNivel = idNivel; this.nombreGrado = nombreGrado; this.descripcion = descripcion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdNivel() { return idNivel; }
    public void setIdNivel(Long idNivel) { this.idNivel = idNivel; }
    public String getNombreGrado() { return nombreGrado; }
    public void setNombreGrado(String nombreGrado) { this.nombreGrado = nombreGrado; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
