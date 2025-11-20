/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Licencia;
import servicio.ConexionBD;

/**
 *
 * @author serbi
 */
public class LicenciaDAO {

    
    private Connection conn;

    public LicenciaDAO(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Licencia> listar() throws Exception {
        ArrayList<Licencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM licencia";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Licencia l = new Licencia();
                l.setIdLicencia(rs.getInt("idlicencia"));
                l.setTipoLicencia(rs.getString("tipolicencia"));
                l.setCosto(rs.getDouble("costo"));
                l.setFechaCompra(rs.getDate("fechacompra"));
                l.setFechaFin(rs.getDate("fechafin"));
                l.setVidaUtil(rs.getInt("vidautil"));
                l.setValorEnLibros((int) rs.getDouble("valorenlibros"));
                l.setValorPendiente(rs.getDouble("valorpendientes"));
                l.setIdUsuario(rs.getInt("idusuario"));
                lista.add(l);
            }
        }
        return lista;
    }

    public Licencia buscar(int id) throws Exception {
        Licencia l = null;
        String sql = "SELECT * FROM licencia WHERE idlicencia=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    l = new Licencia();
                    l.setIdLicencia(rs.getInt("idlicencia"));
                    l.setTipoLicencia(rs.getString("tipolicencia"));
                    l.setCosto(rs.getDouble("costo"));
                    l.setFechaCompra(rs.getDate("fechacompra"));
                    l.setFechaFin(rs.getDate("fechafin"));
                    l.setVidaUtil(rs.getInt("vidautil"));
                    l.setValorEnLibros((int) rs.getDouble("valorenlibros"));
                    l.setValorPendiente(rs.getDouble("valorpendientes"));
                    l.setIdUsuario(rs.getInt("idusuario"));
                }
            }
        }
        return l;
    }

    public boolean insertar(Licencia l) throws Exception {
        String sql = "INSERT INTO licencia(tipolicencia, costo, fechacompra, fechafin, vidautil, valorenlibros, valorpendientes, idusuario) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, l.getTipoLicencia());
            ps.setDouble(2, l.getCosto());
            ps.setDate(3, l.getFechaCompra());
            ps.setDate(4, l.getFechaFin());
            ps.setInt(5, l.getVidaUtil());
            ps.setDouble(6, l.getValorEnLibros());
            ps.setDouble(7, l.getValorPendiente());
            ps.setInt(8, l.getIdUsuario());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Licencia l) throws Exception {
        String sql = "UPDATE licencia SET tipolicencia=?, costo=?, fechacompra=?, fechafin=?, vidautil=?, valorenlibros=?, valorpendientes=?, idusuario=? WHERE idlicencia=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, l.getTipoLicencia());
            ps.setDouble(2, l.getCosto());
            ps.setDate(3, l.getFechaCompra());
            ps.setDate(4, l.getFechaFin());
            ps.setInt(5, l.getVidaUtil());
            ps.setDouble(6, l.getValorEnLibros());
            ps.setDouble(7, l.getValorPendiente());
            ps.setInt(8, l.getIdUsuario());
            ps.setInt(9, l.getIdLicencia());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws Exception {
        String sql = "DELETE FROM licencia WHERE idlicencia=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public ArrayList<Licencia> listarLicenciasPorUsuario(int idUsuario) throws Exception {
        ArrayList<Licencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM licencia WHERE idusuario=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Licencia l = new Licencia();
                    l.setIdLicencia(rs.getInt("idlicencia"));
                    l.setTipoLicencia(rs.getString("tipolicencia"));
                    l.setCosto(rs.getDouble("costo"));
                    l.setFechaCompra(rs.getDate("fechacompra"));
                    l.setFechaFin(rs.getDate("fechafin"));
                    l.setVidaUtil(rs.getInt("vidautil"));
                    l.setValorEnLibros((int) rs.getDouble("valorenlibros"));
                    l.setValorPendiente(rs.getDouble("valorpendientes"));
                    l.setIdUsuario(rs.getInt("idusuario"));
                    lista.add(l);
                }
            }
        }
        return lista;
    }
}