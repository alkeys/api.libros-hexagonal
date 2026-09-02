package com.aviles.api.escuela.documentos.infra.adapter.out.persistence;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "documento_estudiante")
public class JpaDocumentoEstudiante {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Long id;

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "tipo_documento", nullable = false)
    private String tipoDocumento;

    @Column(name = "nombre_archivo", length = 255)
    private String nombreArchivo;

    @Column(name = "url_archivo", length = 500)
    private String urlArchivo;

    @Column(name = "fecha_subida")
    private OffsetDateTime fechaSubida;

    @Column(name = "estado")
    private String estado;

    @Column(name = "observacion", length = 255)
    private String observacion;

    public JpaDocumentoEstudiante() {}
    public JpaDocumentoEstudiante(Long id, Long idEstudiante, String tipoDocumento, String nombreArchivo, String urlArchivo, OffsetDateTime fechaSubida, String estado, String observacion) {
        this.id = id; this.idEstudiante = idEstudiante; this.tipoDocumento = tipoDocumento; this.nombreArchivo = nombreArchivo;
        this.urlArchivo = urlArchivo; this.fechaSubida = fechaSubida; this.estado = estado; this.observacion = observacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Long idEstudiante) { this.idEstudiante = idEstudiante; }
    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }
    public String getNombreArchivo() { return nombreArchivo; }
    public void setNombreArchivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; }
    public String getUrlArchivo() { return urlArchivo; }
    public void setUrlArchivo(String urlArchivo) { this.urlArchivo = urlArchivo; }
    public OffsetDateTime getFechaSubida() { return fechaSubida; }
    public void setFechaSubida(OffsetDateTime fechaSubida) { this.fechaSubida = fechaSubida; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}
