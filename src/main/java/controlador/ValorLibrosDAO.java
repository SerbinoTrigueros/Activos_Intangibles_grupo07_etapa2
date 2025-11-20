/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.ValorLibros;
import servicio.ConexionBD;

/**
 *
 * @author serbi
 */
public class ValorLibrosDAO {

    private final AmortizacionDAO amortizacionDAO = new AmortizacionDAO();

    /**
     * Calcula el valor en libros de una licencia.
     *
     * @param idLicencia ID de la licencia
     * @return ValorLibros con costo, amortizaciones y valor en libros, o null
     * si no existe
     */
    public ValorLibros calcularValorLibros(int idLicencia) {

        // 1. Verificar si la licencia existe
        if (!amortizacionDAO.licenciaExiste(idLicencia)) {
            return null;
        }

        ConexionBD conexionBD = new ConexionBD();
        Connection conn = null;

        double costo = 0.0;
        double acumulado = 0.0;

        try {
            conn = conexionBD.conectar();
            if (conn == null) {
                return null;
            }

            // 2. Obtener Costo de Adquisición desde tabla licencia
            String sqlCosto = "SELECT costo FROM licencia WHERE id_licencia = ?";
            try (PreparedStatement psCosto = conn.prepareStatement(sqlCosto)) {
                psCosto.setInt(1, idLicencia);
                ResultSet rs = psCosto.executeQuery();
                if (rs.next()) {
                    costo = rs.getDouble("costo");
                }
            }

            // 3. Obtener Amortizaciones Acumuladas usando AmortizacionDAO
            acumulado = amortizacionDAO.obtenerAmortizacionesAcumuladas(idLicencia);

        } catch (SQLException e) {
            System.err.println("Error al calcular valor de libros: " + e.getMessage());
            return null;
        } finally {
            conexionBD.desconectar(conn);
        }

        // 4. Calcular Valor en Libros
        double valorBruto = costo - acumulado;
        double excedenteCarga = (valorBruto < 0) ? Math.abs(valorBruto) : 0.0;

        // 5. Crear objeto ValorLibros y poblar datos
        ValorLibros vl = new ValorLibros();
        vl.setIdLicencia(idLicencia);
        vl.setCostoAdquisicion(costo);
        vl.setAmortizacionesAcumuladas(acumulado);
        vl.setValorEnLibros(valorBruto);
      //  vl.setExcedenteCarga(excedenteCarga); // Opcional si tu clase tiene este campo

        return vl;
    
}
}
