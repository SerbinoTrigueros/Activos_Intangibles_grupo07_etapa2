/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servicio;


import controlador.LicenciaDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Licencia;

@WebServlet(name = "ListarLicenciasServlet", urlPatterns = {"/ListarLicenciasServlet"})
public class ListarLicenciasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ConexionBD conexion = new ConexionBD();
        Connection conn = null;

        try {
            conn = conexion.conectar();
            LicenciaDAO dao = new LicenciaDAO(conn);

            List<Licencia> lista = dao.listarLicenciasPorUsuario(1);
            request.setAttribute("listaLicencias", lista);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al cargar las licencias: " + e.getMessage());
        } finally {
            conexion.desconectar(conn);
        }

        request.getRequestDispatcher("eliminarLicencia.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}