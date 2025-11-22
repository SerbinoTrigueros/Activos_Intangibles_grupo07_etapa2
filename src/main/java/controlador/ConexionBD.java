/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Dell
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/Activos_Intangibles";
    private static final String USUARIO = "postgres";
    private static final String CLAVE = "255623";

    public static Connection conectar() {
        Connection conn = null;
        try {
            // cargamos el driver de PostgreSQL
            Class.forName("org.postgresql.Driver"); 
            // establecemos la conexión
            conn = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexión exitosa a la BD.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver de PostgreSQL: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error de conexión a la BD: " + e.getMessage());
        }
        return conn;
    }

    public static void desconectar(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
