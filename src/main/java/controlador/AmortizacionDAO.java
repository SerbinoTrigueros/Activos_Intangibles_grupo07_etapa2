/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import modelo.Amortizacion;
import servicio.ConexionBD;
/**
 *
 * @author serbi
 */
public class AmortizacionDAO {
     // Verifica si ya existen amortizaciones para una licencia
    public boolean amortizacionesExisten(int idLicencia) {
        String sql = "SELECT 1 FROM amortizacion WHERE idlicencia = ?";
        Connection conn = ConexionBD.conectar();
        if (conn == null) return false;
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idLicencia);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error en amortizacionesExisten: " + e.getMessage());
            return false;
        } finally {
            ConexionBD.desconectar(conn);
        }
    }

    // Calcula y guarda amortizaciones (mensuales y anuales)
    public String generarAmortizaciones(int idLicencia) { // Retorna String para mensajes
        if (amortizacionesExisten(idLicencia)) {
            return "Las amortizaciones para esta licencia ya existen.";
        }

        String sqlLicencia = "SELECT costo, vidautil, fechacompra FROM licencia WHERE idlicencia = ?";
        String sqlInsert = "INSERT INTO amortizacion (idlicencia, tipocartera, monto, fecharegistro, estado) VALUES (?, ?, ?, ?, ?)";

        Connection conn = ConexionBD.conectar();
        if (conn == null) return "Error de conexión a la BD.";
        
        try (PreparedStatement psLic = conn.prepareStatement(sqlLicencia);
             PreparedStatement psIns = conn.prepareStatement(sqlInsert)) {

            psLic.setInt(1, idLicencia);
            ResultSet rs = psLic.executeQuery();

            if (rs.next()) {
                double costo = rs.getDouble("costo");
                int vidaUtil = rs.getInt("vidautil");
                java.sql.Date fechaCompra = rs.getDate("fechacompra");

                if (vidaUtil <= 0) vidaUtil = 1;

                double amortMensual = costo / (vidaUtil * 12);
                double amortAnual = costo / vidaUtil;

                Calendar cal = Calendar.getInstance();
                cal.setTime(fechaCompra);

                // Insertar amortizaciones mensuales
                for (int i = 0; i < vidaUtil * 12; i++) {
                    cal.add(Calendar.MONTH, 1);
                    psIns.setInt(1, idLicencia);
                    psIns.setString(2, "mensual");
                    psIns.setDouble(3, amortMensual);
                    psIns.setDate(4, new java.sql.Date(cal.getTimeInMillis()));
                    psIns.setString(5, "pendiente");
                    psIns.addBatch();
                }

                // Insertar amortizaciones anuales
                cal.setTime(fechaCompra);
                for (int i = 0; i < vidaUtil; i++) {
                    cal.add(Calendar.YEAR, 1);
                    psIns.setInt(1, idLicencia);
                    psIns.setString(2, "anual");
                    psIns.setDouble(3, amortAnual);
                    psIns.setDate(4, new java.sql.Date(cal.getTimeInMillis()));
                    psIns.setString(5, "pendiente");
                    psIns.addBatch();
                }

                psIns.executeBatch();
                return "Amortizaciones generadas con éxito.";

            } else {
                 return "Licencia con ID " + idLicencia + " no encontrada.";
            }

        } catch (SQLException e) {
            System.err.println("Error al generar amortizaciones: " + e.getMessage());
            return "Error al generar amortizaciones: " + e.getMessage();
        } finally {
            ConexionBD.desconectar(conn);
        }
    }

    // Listar amortizaciones según el tipo
    public List<Amortizacion> listarAmortizaciones(int idLicencia, String tipo) {
        List<Amortizacion> lista = new ArrayList<>();
        String sql = "";
        
        switch (tipo.toLowerCase()) {
            case "mensual":
                sql = "SELECT * FROM amortizacion WHERE idlicencia = ? AND tipocartera = 'mensual' ORDER BY fecharegistro ASC";
                break;
            case "anual":
                sql = "SELECT * FROM amortizacion WHERE idlicencia = ? AND tipocartera = 'anual' ORDER BY fecharegistro ASC";
                break;
            case "acumulado":
                sql = "SELECT idlicencia, SUM(monto) AS monto_total FROM amortizacion WHERE idlicencia = ? GROUP BY idlicencia";
                break;
            case "pendiente":
                sql = "SELECT * FROM amortizacion WHERE idlicencia = ? AND estado = 'pendiente'";
                break;
            case "pagada":
                sql = "SELECT * FROM amortizacion WHERE idlicencia = ? AND estado = 'pagada'";
                break;
            default:
                // Tipo no válido, retornar lista vacía
                return lista; 
        }

        Connection conn = ConexionBD.conectar();
        if (conn == null) return lista;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLicencia);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Amortizacion a = new Amortizacion();
                if (tipo.equalsIgnoreCase("acumulado")) {
                    a.setIdLicencia(rs.getInt("idlicencia"));
                    a.setMonto(rs.getDouble("monto_total"));
                    a.setTipoCartera("Acumulado");
                } else {
                    a.setIdAmortizacion(rs.getInt("idamortizacion"));
                    a.setIdLicencia(rs.getInt("idlicencia"));
                    a.setTipoCartera(rs.getString("tipocartera"));
                    a.setMonto(rs.getDouble("monto"));
                    a.setFechaRegistro(rs.getDate("fecharegistro"));
                    a.setEstado(rs.getString("estado"));
                }
                lista.add(a);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar amortizaciones: " + e.getMessage());
        } finally {
            ConexionBD.desconectar(conn);
        }

        return lista;
    }

    // Cambiar estado (pendiente/pagada)
    public boolean actualizarEstado(int idAmortizacion, String nuevoEstado) {
        String sql = "UPDATE amortizacion SET estado = ? WHERE idamortizacion = ?";
        Connection conn = ConexionBD.conectar();
        if (conn == null) return false;
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idAmortizacion);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar estado: " + e.getMessage());
            return false;
        } finally {
            ConexionBD.desconectar(conn);
        }
    }

    public boolean licenciaExiste(int idLicencia) {
        String sql = "SELECT 1 FROM licencia WHERE idlicencia = ?";
        Connection conn = ConexionBD.conectar();
        if (conn == null) return false;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idLicencia);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error en licenciaExiste: " + e.getMessage());
            return false;
        } finally {
            ConexionBD.desconectar(conn);
        }
    }
}
