package com.aviles.api.escuela.materias.infra.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "materia")
public class JpaMateria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Long id;

    @Column(name = "codigo_materia", nullable = false, unique = true, length = 30)
    private String codigoMateria;

    @Column(name = "nombre_materia", nullable = false, unique = true, length = 100)
    private String nombreMateria;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "horas_semanales")
    private Integer horasSemanales;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "estado")
    private String estado;

    public JpaMateria() {}
    public JpaMateria(Long id, String codigoMateria, String nombreMateria, String descripcion, Integer horasSemanales, String tipo, String estado) {
        this.id = id; this.codigoMateria = codigoMateria; this.nombreMateria = nombreMateria;
        this.descripcion = descripcion; this.horasSemanales = horasSemanales; this.tipo = tipo; this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigoMateria() { return codigoMateria; }
    public void setCodigoMateria(String codigoMateria) { this.codigoMateria = codigoMateria; }
    public String getNombreMateria() { return nombreMateria; }
    public void setNombreMateria(String nombreMateria) { this.nombreMateria = nombreMateria; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getHorasSemanales() { return horasSemanales; }
    public void setHorasSemanales(Integer horasSemanales) { this.horasSemanales = horasSemanales; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
