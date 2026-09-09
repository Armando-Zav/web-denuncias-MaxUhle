package com.indecopi.denuncias_web.model;

import java.time.LocalDateTime;

public class Denuncia {

    private Long id;
    private String tipoDenuncia;
    private String descripcion;
    private String nombreDenunciante;
    private String nombreAfectado;
    private LocalDateTime fechaRegistro;
    private String estado;
    private Boolean protocoloActivado;

    public Denuncia() {
    }

    public Denuncia(Long id, String tipoDenuncia, String descripcion, String nombreDenunciante,            String nombreAfectado, LocalDateTime fechaRegistro, String estado, Boolean protocoloActivado) {
        this.id = id;
        this.tipoDenuncia = tipoDenuncia;
        this.descripcion = descripcion;
        this.nombreDenunciante = nombreDenunciante;
        this.nombreAfectado = nombreAfectado;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.protocoloActivado = protocoloActivado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(String tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreDenunciante() {
        return nombreDenunciante;
    }

    public void setNombreDenunciante(String nombreDenunciante) {
        this.nombreDenunciante = nombreDenunciante;
    }

    public String getNombreAfectado() {
        return nombreAfectado;
    }

    public void setNombreAfectado(String nombreAfectado) {
        this.nombreAfectado = nombreAfectado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getProtocoloActivado() {
        return protocoloActivado;
    }

    public void setProtocoloActivado(Boolean protocoloActivado) {
        this.protocoloActivado = protocoloActivado;
    }
}