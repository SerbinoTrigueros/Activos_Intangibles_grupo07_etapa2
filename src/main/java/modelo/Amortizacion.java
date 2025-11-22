/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author serbi
 */
public class Amortizacion {
    
    private int idAmortizacion;
    private String tipoCartera;
    private double monto;
    private Date fechaRegistro;
    private int idCuota;
    private int idLicencia;
    private String estado;

    // cktor vacio
    public Amortizacion() {}

    // cktor completo
    public Amortizacion(int idAmortizacion, String tipoCartera, double monto, Date fechaRegistro, int idCuota, int idLicencia, String estado) {
        this.idAmortizacion = idAmortizacion;
        this.tipoCartera = tipoCartera;
        this.monto = monto;
        this.fechaRegistro = fechaRegistro;
        this.idCuota = idCuota;
        this.idLicencia = idLicencia;
        this.estado = estado;
    }

    // getters y setters

    public int getIdAmortizacion() {
        return idAmortizacion;
    }

    public void setIdAmortizacion(int idAmortizacion) {
        this.idAmortizacion = idAmortizacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipoCartera() {
        return tipoCartera;
    }

    public void setTipoCartera(String tipoCartera) {
        this.tipoCartera = tipoCartera;
    }

    public int getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
    }

    public int getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(int idLicencia) {
        this.idLicencia = idLicencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }  
}
