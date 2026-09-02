package com.aviles.api.escuela.profesores.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "profesor")
public class JpaProfesor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private Long id;

    @Column(name = "codigo_profesor", nullable = false, unique = true, length = 30)
    private String codigoProfesor;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "dui", unique = true, length = 20)
    private String dui;

    @Column(name = "especialidad", length = 150)
    private String especialidad;

    @Column(name = "correo_electronico", length = 150)
    private String correoElectronico;

    @Column(name = "telefono", length = 30)
    private String telefono;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "foto_url", length = 500)
    private String fotoUrl;

    @Column(name = "fecha_creacion")
    private OffsetDateTime fechaCreacion;

    public JpaProfesor() {}

    public JpaProfesor(Long id, String codigoProfesor, String nombres, String apellidos, String dui, String especialidad,
                        String correoElectronico, String telefono, String direccion, LocalDate fechaContratacion,
                        String estado, String fotoUrl, OffsetDateTime fechaCreacion) {
        this.id = id; this.codigoProfesor = codigoProfesor; this.nombres = nombres; this.apellidos = apellidos;
        this.dui = dui; this.especialidad = especialidad; this.correoElectronico = correoElectronico;
        this.telefono = telefono; this.direccion = direccion; this.fechaContratacion = fechaContratacion;
        this.estado = estado; this.fotoUrl = fotoUrl; this.fechaCreacion = fechaCreacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigoProfesor() { return codigoProfesor; }
    public void setCodigoProfesor(String codigoProfesor) { this.codigoProfesor = codigoProfesor; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDui() { return dui; }
    public void setDui(String dui) { this.dui = dui; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }
    public OffsetDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(OffsetDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
