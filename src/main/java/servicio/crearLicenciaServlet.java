/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servicio;

import controlador.LicenciaDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Licencia;
import modelo.Usuario;

/**
 *
 * @author serbi
 */
@WebServlet(name = "crearLicenciaServlet", urlPatterns = {"/crearLicenciaServlet"})
public class crearLicenciaServlet extends HttpServlet {

    private final ConexionBD conexionBD = new ConexionBD();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String tipo = request.getParameter("tipo");
        String costoStr = request.getParameter("costo");
        String fechaCompraStr = request.getParameter("fechaCompra");
        String fechaFinStr = request.getParameter("fechaFin");
        String vidaUtilStr = request.getParameter("vidaUtil");

        Connection conn = null;

        try {
            conn = conexionBD.conectar();
            LicenciaDAO dao = new LicenciaDAO(conn);

            HttpSession session = request.getSession();
            Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");

            if (usuarioLogueado == null) {
                request.setAttribute("mensaje", "No hay usuario logueado.");
                request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);
                return;
            }

            int idUsuario = usuarioLogueado.getIdUsuario();

            double costo = Double.parseDouble(costoStr);
            int vidaUtil = Integer.parseInt(vidaUtilStr);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fechaCompraUtil = sdf.parse(fechaCompraStr);
            java.util.Date fechaFinUtil = sdf.parse(fechaFinStr);

            java.sql.Date fechaCompra = new java.sql.Date(fechaCompraUtil.getTime());
            java.sql.Date fechaFin = new java.sql.Date(fechaFinUtil.getTime());

            Licencia licencia = new Licencia();
            licencia.setTipoLicencia(tipo);
            licencia.setCosto(costo);
            licencia.setFechaCompra(fechaCompra);
            licencia.setFechaFin(fechaFin);
            licencia.setVidaUtil(vidaUtil);
            licencia.setIdUsuario(idUsuario);

            boolean exito = dao.insertar(licencia);

            if (exito) {
                request.setAttribute("mensaje", "Licencia guardada correctamente.");
            } else {
                request.setAttribute("mensaje", "Error al guardar la licencia.");
            }

            request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("mensaje", "Error: " + e.getMessage());
            request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);
        } finally {
            conexionBD.desconectar(conn);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(crearLicenciaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(crearLicenciaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para crear Licencia asociada al usuario logueado";
    }
}
