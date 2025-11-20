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

/**
 *
 * @author serbi
 */
@WebServlet(name = "eliminarLicenciaServlet", urlPatterns = {"/eliminarLicenciaServlet"})
public class eliminarLicenciaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ConexionBD conexionBD = new ConexionBD(); // <-- Crear objeto de conexión
        Connection conn = null;

        try {
            conn = conexionBD.conectar();
            LicenciaDAO dao = new LicenciaDAO(conn);

            // Si viene un id de licencia a eliminar
            String idParam = request.getParameter("idlicencia");
            if (idParam != null && !idParam.isEmpty()) {
                int idLicencia = Integer.parseInt(idParam);
                dao.eliminar(idLicencia); // <-- Aquí el método correcto es eliminar()
            }

            // Cargar la lista actualizada de licencias
            List<Licencia> lista = dao.listarLicenciasPorUsuario(1); // o usar idUsuario dinámico
            request.setAttribute("listaLicencias", lista);
            request.getRequestDispatcher("eliminarLicencia.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al consultar la base de datos: " + e.getMessage());
            request.getRequestDispatcher("eliminarLicencia.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("eliminarLicencia.jsp").forward(request, response);
        } finally {
            conexionBD.desconectar(conn); // <-- Usar el objeto para desconectar
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
