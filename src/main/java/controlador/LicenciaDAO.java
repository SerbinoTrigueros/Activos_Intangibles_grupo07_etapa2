/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Licencia;
import servicio.ConexionBD;
/**
 *
 * @author serbi
 */

public class LicenciaDAO {
    
    private Connection conexion;

    // ✔ Constructor vacío que sí conecta
    public LicenciaDAO() {
        this.conexion = ConexionBD.conectar();
    }

    // Constructor con conexión manual
    public LicenciaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Agregar una licencia
    public boolean agregarLicencia(Licencia l) throws SQLException {
        String sql = "INSERT INTO licencia (tipolicencia, costo, fechacompra, fechafin, vidautil, valorenlibros, valorpendientes, idusuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, l.getTipoLicencia());
        ps.setDouble(2, l.getCosto());
        ps.setDate(3, new java.sql.Date(l.getFechaCompra().getTime()));
        ps.setDate(4, new java.sql.Date(l.getFechaFin().getTime()));
        ps.setInt(5, l.getVidaUtil());
        ps.setInt(6, l.getValorEnLibros());
        ps.setDouble(7, l.getValorPendiente());
        ps.setInt(8, l.getIdUsuario());
        return ps.executeUpdate() > 0;
    }

    // Listar licencias por usuario
    public List<Licencia> listarLicenciasPorUsuario(int idUsuario) throws SQLException {
        List<Licencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM licencia WHERE idusuario = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Licencia(
                rs.getInt("idlicencia"),
                rs.getString("tipolicencia"),
                rs.getDouble("costo"),
                rs.getDate("fechacompra"),
                rs.getDate("fechafin"),
                rs.getInt("vidautil"),
                rs.getInt("valorenlibros"),
                rs.getDouble("valorpendientes"),
                rs.getInt("idusuario")
            ));
        }
        return lista;
    }

    // Buscar por ID
    public Licencia buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM licencia WHERE idlicencia=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Licencia(
                rs.getInt("idlicencia"),
                rs.getString("tipolicencia"),
                rs.getDouble("costo"),
                rs.getDate("fechacompra"),
                rs.getDate("fechafin"),
                rs.getInt("vidautil"),
                rs.getInt("valorenlibros"),
                rs.getDouble("valorpendientes"),
                rs.getInt("idusuario")
            );
        }
        return null;
    }

    // Eliminar licencia
    public boolean eliminarLicencia(int id) throws SQLException {
        String sql = "DELETE FROM licencia WHERE idlicencia=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }

}


