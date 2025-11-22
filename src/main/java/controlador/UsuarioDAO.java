/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;

/**
 *
 * @author serbi
 */
public class UsuarioDAO {
    
    private Connection conexion;

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion; 
    }

    public Usuario login(String email, String contrasena) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = ? AND contrasena = ?";
        PreparedStatement ps = conexion.prepareStatement(sql); 
        ps.setString(1, email);
        ps.setString(2, contrasena);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idusuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setEmail(rs.getString("email"));
            usuario.setContrasenia(rs.getString("contrasena"));
            usuario.setRegion(rs.getString("region"));
        }
        return usuario;
    }
    
}


//hola

