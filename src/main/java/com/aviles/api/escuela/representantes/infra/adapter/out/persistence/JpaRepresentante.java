package com.aviles.api.escuela.representantes.infra.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "representante")
public class JpaRepresentante {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_representante")
    private Long id;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "dui", unique = true, length = 20)
    private String dui;

    @Column(name = "correo_electronico", length = 150)
    private String correoElectronico;

    @Column(name = "telefono", length = 30)
    private String telefono;

    @Column(name = "telefono_alternativo", length = 30)
    private String telefonoAlternativo;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "ocupacion", length = 100)
    private String ocupacion;

    @Column(name = "estado")
    private String estado;

    public JpaRepresentante() {}
    public JpaRepresentante(Long id, String nombres, String apellidos, String dui, String correoElectronico,
                             String telefono, String telefonoAlternativo, String direccion, String ocupacion, String estado) {
        this.id = id; this.nombres = nombres; this.apellidos = apellidos; this.dui = dui; this.correoElectronico = correoElectronico;
        this.telefono = telefono; this.telefonoAlternativo = telefonoAlternativo; this.direccion = direccion;
        this.ocupacion = ocupacion; this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDui() { return dui; }
    public void setDui(String dui) { this.dui = dui; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getTelefonoAlternativo() { return telefonoAlternativo; }
    public void setTelefonoAlternativo(String telefonoAlternativo) { this.telefonoAlternativo = telefonoAlternativo; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
