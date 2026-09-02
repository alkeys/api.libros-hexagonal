package com.aviles.api.escuela.estudiantes.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "estudiante")
public class JpaEstudiante {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Long id;

    @Column(name = "codigo_estudiante", nullable = false, unique = true, length = 30)
    private String codigoEstudiante;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "genero")
    private String genero;

    @Column(name = "nacionalidad", length = 80)
    private String nacionalidad;

    @Column(name = "dui", unique = true, length = 20)
    private String dui;

    @Column(name = "nie", unique = true, length = 30)
    private String nie;

    @Column(name = "correo_electronico", length = 150)
    private String correoElectronico;

    @Column(name = "telefono", length = 30)
    private String telefono;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "estado")
    private String estado;

    @Column(name = "foto_url", length = 500)
    private String fotoUrl;

    @Column(name = "fecha_creacion")
    private OffsetDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private OffsetDateTime fechaActualizacion;

    public JpaEstudiante() {}

    public JpaEstudiante(Long id, String codigoEstudiante, String nombres, String apellidos,
                          LocalDate fechaNacimiento, String genero, String nacionalidad, String dui, String nie,
                          String correoElectronico, String telefono, String direccion, LocalDate fechaIngreso,
                          String estado, String fotoUrl, OffsetDateTime fechaCreacion, OffsetDateTime fechaActualizacion) {
        this.id = id; this.codigoEstudiante = codigoEstudiante; this.nombres = nombres; this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento; this.genero = genero; this.nacionalidad = nacionalidad;
        this.dui = dui; this.nie = nie; this.correoElectronico = correoElectronico; this.telefono = telefono;
        this.direccion = direccion; this.fechaIngreso = fechaIngreso; this.estado = estado; this.fotoUrl = fotoUrl;
        this.fechaCreacion = fechaCreacion; this.fechaActualizacion = fechaActualizacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigoEstudiante() { return codigoEstudiante; }
    public void setCodigoEstudiante(String codigoEstudiante) { this.codigoEstudiante = codigoEstudiante; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public String getDui() { return dui; }
    public void setDui(String dui) { this.dui = dui; }
    public String getNie() { return nie; }
    public void setNie(String nie) { this.nie = nie; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }
    public OffsetDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(OffsetDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public OffsetDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(OffsetDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
}
