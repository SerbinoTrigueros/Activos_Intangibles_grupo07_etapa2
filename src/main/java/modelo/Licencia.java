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
public class Licencia {
    
    private int idLicencia;
    private String tipoLicencia;
    private double costo;
    private Date fechaCompra;
    private Date fechaFin;
    private int vidaUtil;
    private double valorEnLibros;
    private double valorPendiente;
    private int idUsuario;

    // Constructor vacío
    public Licencia() {}

    // Constructor completo
    public Licencia(int idLicencia, String tipoLicencia, double costo, Date fechaCompra, Date fechaFin,
                    int vidaUtil, int valorEnLibros, double valorPendiente, int idUsuario) {
        this.idLicencia = idLicencia;
        this.tipoLicencia = tipoLicencia;
        this.costo = costo;
        this.fechaCompra = fechaCompra;
        this.fechaFin = fechaFin;
        this.vidaUtil = vidaUtil;
        this.valorEnLibros = valorEnLibros;
        this.valorPendiente = valorPendiente;
        this.idUsuario = idUsuario;
    }

    //getter and setters
    public int getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(int idLicencia) {
        this.idLicencia = idLicencia;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public double getValorEnLibros() {
        return valorEnLibros;
    }

    public void setValorEnLibros(int valorEnLibros) {
        this.valorEnLibros = valorEnLibros;
    }

    public double getValorPendiente() {
        return valorPendiente;
    }

    public void setValorPendiente(double valorPendiente) {
        this.valorPendiente = valorPendiente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
