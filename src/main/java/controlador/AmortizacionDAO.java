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

        try (Connection con = conexion.conectar(); PreparedStatement psVer = con.prepareStatement(sqlVerificar)) {

            psVer.setInt(1, idLicencia);
            ResultSet rs = psVer.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                return "Amortizaciones ya existen para esta licencia.";
            }

            // -----------------------------
            // Generar automáticamente cuotas y amortizaciones
            // -----------------------------
            // Ejemplo: generar 12 cuotas mensuales de 50.00
            int numeroCuotas = 12;
            double monto = 50.00;

            for (int i = 1; i <= numeroCuotas; i++) {
                // Insertar cuota
                String sqlInsertCuota = "INSERT INTO cuota (numerocuota, monto, estado, idlicencia, tipo, fecharegistro) "
                        + "VALUES (?, ?, 'pendiente', ?, 'mensual', CURRENT_DATE) RETURNING idcuota";
                int idCuota = 0;
                try (PreparedStatement psCuota = con.prepareStatement(sqlInsertCuota)) {
                    psCuota.setInt(1, i);
                    psCuota.setDouble(2, monto);
                    psCuota.setInt(3, idLicencia);
                    ResultSet rsCuota = psCuota.executeQuery();
                    if (rsCuota.next()) {
                        idCuota = rsCuota.getInt("idcuota");
                    }
                }

                // Insertar amortización con el idCuota generado
                String sqlInsertAmort = "INSERT INTO amortizacion (idlicencia, tipocartera, monto, fecharegistro, estado, idcuota) "
                        + "VALUES (?, 'mensual', ?, CURRENT_DATE, 'pendiente', ?)";
                try (PreparedStatement psAmort = con.prepareStatement(sqlInsertAmort)) {
                    psAmort.setInt(1, idLicencia);
                    psAmort.setDouble(2, monto);
                    psAmort.setInt(3, idCuota);
                    psAmort.executeUpdate();
                }
            }

            return "Amortizaciones y cuotas generadas automáticamente.";

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
