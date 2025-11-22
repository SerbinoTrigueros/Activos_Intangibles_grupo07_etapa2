/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author serbi
 */

public class ConexionBD {
 private final String url = "jdbc:postgresql://localhost:5432/Activos_Intangibles";
    private final String user = "postgres";
    private final String pass = "255623";

    public Connection conectar() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");  
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver de PostgreSQL", e);
        }

        return DriverManager.getConnection(url, user, pass);
    }
    
    public void desconectar(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
