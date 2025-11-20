/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servicio;

import controlador.LicenciaDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Licencia;
import modelo.Usuario;

/**
 *
 * @author serbi
 */
@WebServlet(name = "verLicenciaServlet", urlPatterns = {"/verLicenciaServlet"})
public class verLicenciaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ConexionBD conexionBD = new ConexionBD(); // <-- Crear objeto de conexión
        Connection conn = null;

        try {
            // Obtener el usuario desde la sesión
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("usuario") == null) {
                response.sendRedirect("login.jsp"); // No hay sesión activa
                return;
            }

            Usuario usuario = (Usuario) session.getAttribute("usuario");
            int idUsuario = usuario.getIdUsuario(); // ID del usuario logueado

            // Conectar a la base de datos y obtener licencias del usuario
            conn = conexionBD.conectar();
            LicenciaDAO dao = new LicenciaDAO(conn);
            List<Licencia> lista = dao.listarLicenciasPorUsuario(idUsuario);

            request.setAttribute("listaLicencias", lista);
            request.getRequestDispatcher("verLicencia.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al consultar la base de datos: " + e.getMessage());
            request.getRequestDispatcher("verLicencia.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("verLicencia.jsp").forward(request, response);
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

    @Override
    public String getServletInfo() {
        return "Servlet para mostrar las licencias de un usuario";
    }
}
