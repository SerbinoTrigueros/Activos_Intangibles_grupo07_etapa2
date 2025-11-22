/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Reporte;
/**
 *
 * @author serbi
 */
public class ReporteDAO {
    
    private Connection conexion;

    public ReporteDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // agregamos un reporte
    public boolean agregarReporte(Reporte r) throws SQLException {
        String sql = "INSERT INTO reporte (descripcion, fechagenerada, tipo, idlicencia) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, r.getDescripcion());
        ps.setDate(2, new java.sql.Date(r.getFechaGenerada().getTime()));
        ps.setString(3, r.getTipo());
        ps.setInt(4, r.getIdLicencia());
        return ps.executeUpdate() > 0;
    }

    // listamos todos los reportes
    public List<Reporte> listarReportes() throws SQLException {
        List<Reporte> lista = new ArrayList<>();
        String sql = "SELECT * FROM reporte";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            lista.add(new Reporte(
                rs.getInt("idreporte"),
                rs.getString("descripcion"),
                rs.getDate("fechagenerada"),
                rs.getString("tipo"),
                rs.getInt("idlicencia")
            ));
        }
        return lista;
    }

    // buscamos un reporte por el id
    public Reporte buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM reporte WHERE idreporte=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Reporte(
                rs.getInt("idreporte"),
                rs.getString("descripcion"),
                rs.getDate("fechagenerada"),
                rs.getString("tipo"),
                rs.getInt("idlicencia")
            );
        }
        return null;
    }
}
