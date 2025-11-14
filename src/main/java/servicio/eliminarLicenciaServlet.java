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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          Connection conn = null;
        try {
            conn = ConexionBD.conectar();
            LicenciaDAO dao = new LicenciaDAO(conn);

            // Si viene un id de licencia a eliminar
            String idParam = request.getParameter("idlicencia");
            if (idParam != null && !idParam.isEmpty()) {
                int idLicencia = Integer.parseInt(idParam);
                dao.eliminarLicencia(idLicencia);
            }

            // Cargar la lista actualizada de licencias
            List<Licencia> lista = dao.listarLicenciasPorUsuario(1); // o listarLicenciasPorUsuario(idUsuario)
            request.setAttribute("listaLicencias", lista);
            request.getRequestDispatcher("eliminarLicencia.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al consultar la base de datos: " + e.getMessage());
            request.getRequestDispatcher("eliminarLicencia.jsp").forward(request, response);
        } finally {
            ConexionBD.desconectar(conn);
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
