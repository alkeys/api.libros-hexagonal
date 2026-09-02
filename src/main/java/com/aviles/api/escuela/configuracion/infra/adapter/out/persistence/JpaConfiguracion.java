package com.aviles.api.escuela.configuracion.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Entidad JPA que representa la tabla 'configuracion' en la base de datos.
 * Almacena la configuración general del sistema escolar.
 */
@Entity
@Table(name = "configuracion")
public class JpaConfiguracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion")
    private Long id;

    @Column(name = "nombre_institucion", nullable = false, length = 150)
    private String nombreInstitucion;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "telefono", length = 30)
    private String telefono;

    @Column(name = "correo", length = 150)
    private String correo;

    @Column(name = "sitio_web", length = 255)
    private String sitioWeb;

    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @Column(name = "escala_minima", precision = 4, scale = 2)
    private BigDecimal escalaMinima;

    @Column(name = "escala_maxima", precision = 4, scale = 2)
    private BigDecimal escalaMaxima;

    @Column(name = "nota_aprobacion", precision = 4, scale = 2)
    private BigDecimal notaAprobacion;

    @Column(name = "moneda", length = 10)
    private String moneda;

    @Column(name = "zona_horaria", length = 100)
    private String zonaHoraria;

    @Column(name = "fecha_creacion")
    private OffsetDateTime fechaCreacion;

    public JpaConfiguracion() {}

    public JpaConfiguracion(Long id, String nombreInstitucion, String direccion, String telefono,
                            String correo, String sitioWeb, String logoUrl, BigDecimal escalaMinima,
                            BigDecimal escalaMaxima, BigDecimal notaAprobacion, String moneda,
                            String zonaHoraria, OffsetDateTime fechaCreacion) {
        this.id = id;
        this.nombreInstitucion = nombreInstitucion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.sitioWeb = sitioWeb;
        this.logoUrl = logoUrl;
        this.escalaMinima = escalaMinima;
        this.escalaMaxima = escalaMaxima;
        this.notaAprobacion = notaAprobacion;
        this.moneda = moneda;
        this.zonaHoraria = zonaHoraria;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombreInstitucion() { return nombreInstitucion; }
    public void setNombreInstitucion(String nombreInstitucion) { this.nombreInstitucion = nombreInstitucion; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getSitioWeb() { return sitioWeb; }
    public void setSitioWeb(String sitioWeb) { this.sitioWeb = sitioWeb; }
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public BigDecimal getEscalaMinima() { return escalaMinima; }
    public void setEscalaMinima(BigDecimal escalaMinima) { this.escalaMinima = escalaMinima; }
    public BigDecimal getEscalaMaxima() { return escalaMaxima; }
    public void setEscalaMaxima(BigDecimal escalaMaxima) { this.escalaMaxima = escalaMaxima; }
    public BigDecimal getNotaAprobacion() { return notaAprobacion; }
    public void setNotaAprobacion(BigDecimal notaAprobacion) { this.notaAprobacion = notaAprobacion; }
    public String getMoneda() { return moneda; }
    public void setMoneda(String moneda) { this.moneda = moneda; }
    public String getZonaHoraria() { return zonaHoraria; }
    public void setZonaHoraria(String zonaHoraria) { this.zonaHoraria = zonaHoraria; }
    public OffsetDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(OffsetDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
