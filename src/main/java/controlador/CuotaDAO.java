/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Cuota;

/**
 *
 * @author serbi
 */
public class CuotaDAO {
    
    private Connection conexion;

    public CuotaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Agregar cuota
    public boolean agregarCuota(Cuota c) throws SQLException {
        String sql = "INSERT INTO cuota (numerocuota, monto, estado, idlicencia, tipo, fecharegistro) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, c.getNumeroCuota());
        ps.setDouble(2, c.getMonto());
        ps.setString(3, c.getEstado());
        ps.setInt(4, c.getIdLicencia());
        ps.setString(5, c.getTipo());
        ps.setDate(6, new java.sql.Date(c.getFechaRegistro().getTime()));
        return ps.executeUpdate() > 0;
    }

    // Listar todas las cuotas
    public List<Cuota> listarCuotas() throws SQLException {
        List<Cuota> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuota";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            lista.add(new Cuota(
                rs.getInt("idcuota"),
                rs.getInt("numerocuota"),
                rs.getDouble("monto"),
                rs.getString("estado"),
                rs.getInt("idlicencia"),
                rs.getString("tipo"),
                rs.getDate("fecharegistro")
            ));
        }
        return lista;
    }

    // Buscar cuota por id
    public Cuota buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM cuota WHERE idcuota=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Cuota(
                rs.getInt("idcuota"),
                rs.getInt("numerocuota"),
                rs.getDouble("monto"),
                rs.getString("estado"),
                rs.getInt("idlicencia"),
                rs.getString("tipo"),
                rs.getDate("fecharegistro")
            );
        }
        return null;
    }
}
