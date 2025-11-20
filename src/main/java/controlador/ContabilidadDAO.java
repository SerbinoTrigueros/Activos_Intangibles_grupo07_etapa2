/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Licencia;
import servicio.ConexionBD;

/**
 *
 * @author serbi
 */
public class ContabilidadDAO {

    private final ConexionBD conexion = new ConexionBD();

    public List<Licencia> listarLicencias() {
        List<Licencia> lista = new ArrayList<>();

        String sql = "SELECT idlicencia, tipolicencia, costo, fechacompra, fechafin, vidautil FROM licencia ORDER BY idlicencia ASC";

        try (Connection con = conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Licencia l = new Licencia();
                l.setIdLicencia(rs.getInt("idlicencia"));
                l.setTipoLicencia(rs.getString("tipolicencia"));
                l.setCosto(rs.getDouble("costo"));
                l.setFechaCompra(rs.getDate("fechacompra"));
                l.setFechaFin(rs.getDate("fechafin"));
                l.setVidaUtil(rs.getInt("vidautil"));
                lista.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean actualizarLicencia(int idLicencia, double valorLibros, double valorPendiente) {

        String sql = "UPDATE licencia SET valorenlibros = ?, valorpendientes = ? WHERE idlicencia = ?";

        Connection con = null;

        try {
            con = conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, valorLibros);
            ps.setDouble(2, valorPendiente);
            ps.setInt(3, idLicencia);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            conexion.desconectar(con);
        }
    }
}
