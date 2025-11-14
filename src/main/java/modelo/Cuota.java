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
public class Cuota {
     private int idCuota;
    private int numeroCuota;
    private double monto;
    private String estado;
    private int idLicencia;
    private String tipo;
    private Date fechaRegistro;

    // Constructor vacío
    public Cuota() {}

    // Constructor completo
    public Cuota(int idCuota, int numeroCuota, double monto, String estado, int idLicencia, String tipo, Date fechaRegistro) {
        this.idCuota = idCuota;
        this.numeroCuota = numeroCuota;
        this.monto = monto;
        this.estado = estado;
        this.idLicencia = idLicencia;
        this.tipo = tipo;
        this.fechaRegistro = fechaRegistro;
    }

    //setters and getters
    public int getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(int idLicencia) {
        this.idLicencia = idLicencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
    
    }

