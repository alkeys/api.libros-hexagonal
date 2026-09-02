package com.aviles.api.escuela.auditoria.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.net.InetAddress;
import java.time.OffsetDateTime;

@Entity
@Table(name = "auditoria")
public class JpaAuditoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Long id;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "tabla_afectada", nullable = false, length = 100)
    private String tablaAfectada;

    @Column(name = "id_registro", length = 100)
    private String idRegistro;

    @Column(name = "accion", nullable = false)
    private String accion;

    @Column(name = "datos_anteriores", columnDefinition = "jsonb")
    private String datosAnteriores;

    @Column(name = "datos_nuevos", columnDefinition = "jsonb")
    private String datosNuevos;

    @Column(name = "ip")
    private InetAddress ip;

    @Column(name = "fecha")
    private OffsetDateTime fecha;

    public JpaAuditoria() {}
    public JpaAuditoria(Long id, Long idUsuario, String tablaAfectada, String idRegistro, String accion, String datosAnteriores, String datosNuevos, InetAddress ip, OffsetDateTime fecha) {
        this.id = id; this.idUsuario = idUsuario; this.tablaAfectada = tablaAfectada; this.idRegistro = idRegistro;
        this.accion = accion; this.datosAnteriores = datosAnteriores; this.datosNuevos = datosNuevos; this.ip = ip; this.fecha = fecha;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public String getTablaAfectada() { return tablaAfectada; }
    public void setTablaAfectada(String tablaAfectada) { this.tablaAfectada = tablaAfectada; }
    public String getIdRegistro() { return idRegistro; }
    public void setIdRegistro(String idRegistro) { this.idRegistro = idRegistro; }
    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }
    public String getDatosAnteriores() { return datosAnteriores; }
    public void setDatosAnteriores(String datosAnteriores) { this.datosAnteriores = datosAnteriores; }
    public String getDatosNuevos() { return datosNuevos; }
    public void setDatosNuevos(String datosNuevos) { this.datosNuevos = datosNuevos; }
    public InetAddress getIp() { return ip; }
    public void setIp(InetAddress ip) { this.ip = ip; }
    public OffsetDateTime getFecha() { return fecha; }
    public void setFecha(OffsetDateTime fecha) { this.fecha = fecha; }
}
