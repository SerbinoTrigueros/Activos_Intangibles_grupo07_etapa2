/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.ValorLibros;
import java.sql.*;


public class ValorLibrosDAO {

    private final AmortizacionDAO amortizacionDAO = new AmortizacionDAO();

    public ValorLibros calcularValorLibros(int idLicencia) {
        if (!amortizacionDAO.licenciaExiste(idLicencia)) {
            return null;
        }
        
        Connection conn = ConexionBD.conectar();
        if (conn == null) return null;
        
        double costo = 0.0;
        double acumulado = 0.0;
        
        // [CÓDIGO para obtener costo y acumulado se mantiene igual]...
        
        // Obtener Costo de Adquisición
        String sqlCosto = "SELECT costo FROM licencia WHERE idlicencia = ?";
        try (PreparedStatement psCosto = conn.prepareStatement(sqlCosto)) {
            psCosto.setInt(1, idLicencia);
            ResultSet rs = psCosto.executeQuery();
            if (rs.next()) {
                costo = rs.getDouble("costo");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el costo de la licencia: " + e.getMessage());
            ConexionBD.desconectar(conn);
            return null;
        }

        // Obtener Amortizaciones Acumuladas
        String sqlAcumulado = "SELECT COALESCE(SUM(monto), 0.0) AS monto_total FROM amortizacion WHERE idlicencia = ?";
        try (PreparedStatement psAcumulado = conn.prepareStatement(sqlAcumulado)) {
            psAcumulado.setInt(1, idLicencia);
            ResultSet rs = psAcumulado.executeQuery();
            if (rs.next()) {
                acumulado = rs.getDouble("monto_total"); 
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener amortizaciones acumuladas: " + e.getMessage());
            ConexionBD.desconectar(conn);
            return null;
        } finally {
            ConexionBD.desconectar(conn);
        }
        
        // 4. Calcular el Valor en Libros y manejar el excedente.
        double valorBruto = costo - acumulado;
       
        
        // Si valorBruto es negativo, el valor absoluto es el excedente o "carga"
        double excedenteCarga = (valorBruto < 0) ? Math.abs(valorBruto) : 0.0;
        
        // 5. Crear y poblar el objeto de resultado
        ValorLibros vl = new ValorLibros();
        vl.setIdLicencia(idLicencia);
        vl.setCostoAdquisicion(costo);
        vl.setAmortizacionesAcumuladas(acumulado);
        vl.setValorEnLibros(valorBruto);
        
        
        return vl;
    }
}