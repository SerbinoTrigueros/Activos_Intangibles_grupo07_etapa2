/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Amortizacion;
import servicio.ConexionBD;

/**
 *
 * @author serbi
 */
public class AmortizacionDAO {

    private final ConexionBD conexion = new ConexionBD();

    public String generarAmortizaciones(int idLicencia) {

        String sqlVerificar = "SELECT COUNT(*) FROM amortizacion WHERE idlicencia = ?";
        String sqlInsert = "INSERT INTO amortizacion (idlicencia, tipocartera, monto, fecharegistro, estado) VALUES (?, 'mensual', 50.00, CURRENT_DATE, 'pendiente')";

        try (Connection con = conexion.conectar(); PreparedStatement psVer = con.prepareStatement(sqlVerificar)) {

            psVer.setInt(1, idLicencia);
            ResultSet rs = psVer.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                return "Amortizaciones ya existen para esta licencia.";
            }

            try (PreparedStatement psIns = con.prepareStatement(sqlInsert)) {
                psIns.setInt(1, idLicencia);
                psIns.executeUpdate();
            }

            return "Amortizaciones generadas correctamente.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al generar amortizaciones: " + e.getMessage();
        }
    }

    public List<Amortizacion> listarPorLicencia(int idLicencia, String tipo, String estado) {

        List<Amortizacion> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM amortizacion WHERE idlicencia = ?");

        if (!"acumulado".equalsIgnoreCase(tipo)) {
            sql.append(" AND tipocartera = ?");
        }

        if (!"todos".equalsIgnoreCase(estado)) {
            sql.append(" AND estado = ?");
        }

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int i = 1;
            ps.setInt(i++, idLicencia);

            if (!"acumulado".equalsIgnoreCase(tipo)) {
                ps.setString(i++, tipo);
            }

            if (!"todos".equalsIgnoreCase(estado)) {
                ps.setString(i++, estado);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Amortizacion a = new Amortizacion();

                a.setIdAmortizacion(rs.getInt("idamortizacion"));
                a.setTipoCartera(rs.getString("tipocartera"));
                a.setMonto(rs.getDouble("monto"));
                a.setFechaRegistro(rs.getDate("fecharegistro"));
                a.setIdCuota(rs.getInt("idcuota"));
                a.setIdLicencia(rs.getInt("idlicencia"));
                a.setEstado(rs.getString("estado"));

                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // -----------------------------
    // NUEVOS MÉTODOS PARA ValorLibrosDAO
    // -----------------------------
    /**
     * Verifica si existe la licencia en la tabla licencia
     */
    public boolean licenciaExiste(int idLicencia) {
        boolean existe = false;
        String sql = "SELECT 1 FROM licencia WHERE id_licencia = ?";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idLicencia);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                existe = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return existe;
    }

    /**
     * Obtiene la suma de amortizaciones acumuladas para una licencia
     */
    public double obtenerAmortizacionesAcumuladas(int idLicencia) {
        double acumulado = 0.0;
        String sql = "SELECT COALESCE(SUM(monto), 0.0) AS monto_total FROM amortizacion WHERE idlicencia = ?";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idLicencia);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                acumulado = rs.getDouble("monto_total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return acumulado;
    }
}
