/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Amortizacion;
/**
 *
 * @author serbi
 */
public class AmortizacionDAO {
    
     private Connection conexion;

    public AmortizacionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Agregar una amortización
    public boolean agregarAmortizacion(Amortizacion a) throws SQLException {
        String sql = "INSERT INTO amortizacion (tipocartera, monto, fecharegistro, idcuota, idlicencia, estado) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, a.getTipoCartera());
        ps.setDouble(2, a.getMonto());
        ps.setDate(3, new java.sql.Date(a.getFechaRegistro().getTime()));
        ps.setInt(4, a.getIdCuota());
        ps.setInt(5, a.getIdLicencia());
        ps.setString(6, a.getEstado());
        return ps.executeUpdate() > 0;
    }

    // Listar todas las amortizaciones
    public List<Amortizacion> listarAmortizaciones() throws SQLException {
        List<Amortizacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM amortizacion";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            lista.add(new Amortizacion(
                rs.getInt("idamortizacion"),
                rs.getString("tipocartera"),
                rs.getDouble("monto"),
                rs.getDate("fecharegistro"),
                rs.getInt("idcuota"),
                rs.getInt("idlicencia"),
                rs.getString("estado")
            ));
        }
        return lista;
    }

    // Buscar una amortización por id
    public Amortizacion buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM amortizacion WHERE idamortizacion=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Amortizacion(
                rs.getInt("idamortizacion"),
                rs.getString("tipocartera"),
                rs.getDouble("monto"),
                rs.getDate("fecharegistro"),
                rs.getInt("idcuota"),
                rs.getInt("idlicencia"),
                rs.getString("estado")
            );
        }
        return null;
    }
}
