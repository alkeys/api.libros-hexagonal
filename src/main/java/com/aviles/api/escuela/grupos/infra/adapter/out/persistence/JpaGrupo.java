package com.aviles.api.escuela.grupos.infra.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "grupo")
public class JpaGrupo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long id;

    @Column(name = "id_grado", nullable = false)
    private Long idGrado;

    @Column(name = "id_seccion", nullable = false)
    private Long idSeccion;

    @Column(name = "id_anio_escolar", nullable = false)
    private Long idAnioEscolar;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "turno")
    private String turno;

    @Column(name = "estado")
    private String estado;

    public JpaGrupo() {}
    public JpaGrupo(Long id, Long idGrado, Long idSeccion, Long idAnioEscolar, String nombre, Integer capacidad, String turno, String estado) {
        this.id = id; this.idGrado = idGrado; this.idSeccion = idSeccion; this.idAnioEscolar = idAnioEscolar;
        this.nombre = nombre; this.capacidad = capacidad; this.turno = turno; this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdGrado() { return idGrado; }
    public void setIdGrado(Long idGrado) { this.idGrado = idGrado; }
    public Long getIdSeccion() { return idSeccion; }
    public void setIdSeccion(Long idSeccion) { this.idSeccion = idSeccion; }
    public Long getIdAnioEscolar() { return idAnioEscolar; }
    public void setIdAnioEscolar(Long idAnioEscolar) { this.idAnioEscolar = idAnioEscolar; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
