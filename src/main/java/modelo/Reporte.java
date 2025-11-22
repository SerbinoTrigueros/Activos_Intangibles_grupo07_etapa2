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
public class Reporte {

    private int idReporte;
    private String descripcion;
    private Date fechaGenerada;
    private String tipo;
    private int idLicencia;

    // cktor vacio
    public Reporte() {
    }

    // cktor completo
    public Reporte(int idReporte, String descripcion, Date fechaGenerada, String tipo, int idLicencia) {
        this.idReporte = idReporte;
        this.descripcion = descripcion;
        this.fechaGenerada = fechaGenerada;
        this.tipo = tipo;
        this.idLicencia = idLicencia;
    }

    // getters y setters
    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaGenerada() {
        return fechaGenerada;
    }

    public void setFechaGenerada(Date fechaGenerada) {
        this.fechaGenerada = fechaGenerada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(int idLicencia) {
        this.idLicencia = idLicencia;
    }
}
