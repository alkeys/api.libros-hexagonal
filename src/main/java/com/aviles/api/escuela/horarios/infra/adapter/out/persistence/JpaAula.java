package com.aviles.api.escuela.horarios.infra.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "aula")
public class JpaAula {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aula")
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true, length = 30)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "edificio", length = 100)
    private String edificio;

    @Column(name = "piso", length = 50)
    private String piso;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "estado")
    private String estado;

    public JpaAula() {}
    public JpaAula(Long id, String codigo, String nombre, String edificio, String piso, Integer capacidad, String tipo, String estado) {
        this.id = id; this.codigo = codigo; this.nombre = nombre; this.edificio = edificio;
        this.piso = piso; this.capacidad = capacidad; this.tipo = tipo; this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEdificio() { return edificio; }
    public void setEdificio(String edificio) { this.edificio = edificio; }
    public String getPiso() { return piso; }
    public void setPiso(String piso) { this.piso = piso; }
    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
