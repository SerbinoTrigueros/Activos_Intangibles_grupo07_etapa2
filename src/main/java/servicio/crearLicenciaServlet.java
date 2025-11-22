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

        request.setAttribute("tipoValor", tipo);
        request.setAttribute("costoValor", costoStr);
        request.setAttribute("fechaCompraValor", fechaCompraStr);
        request.setAttribute("fechaFinValor", fechaFinStr);
        request.setAttribute("vidaUtilValor", vidaUtilStr);

        Connection conn = null;

        try {
            conn = conexionBD.conectar();
            LicenciaDAO dao = new LicenciaDAO(conn);

            if (tipo == null || tipo.trim().isEmpty()
                    || costoStr == null || costoStr.trim().isEmpty()
                    || fechaCompraStr == null || fechaCompraStr.trim().isEmpty()
                    || fechaFinStr == null || fechaFinStr.trim().isEmpty()
                    || vidaUtilStr == null || vidaUtilStr.trim().isEmpty()) {

                request.setAttribute("mensaje", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);
                return;
            }

            double costo;
            try {
                costoStr = costoStr.replace(",", ".");
                costo = Double.parseDouble(costoStr);
                if (costo <= 0) {
                    request.setAttribute("mensaje", "El costo debe ser mayor a 0.");
                    request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("mensaje", "El costo debe ser un decimal válido.");
                request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);
                return;
            }

            int vidaUtil;
            try {
                vidaUtil = Integer.parseInt(vidaUtilStr);
                if (vidaUtil <= 0) {
                    request.setAttribute("mensaje", "La vida útil debe ser mayor a 0.");
                    request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("mensaje", "La vida útil debe ser un número entero.");
                request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fechaCompraUtil;
            java.util.Date fechaFinUtil;

            try {
                fechaCompraUtil = sdf.parse(fechaCompraStr);
                fechaFinUtil = sdf.parse(fechaFinStr);
            } catch (Exception ex) {
                request.setAttribute("mensaje", "Formato de fecha incorrecto.");
                request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);
                return;
            }

            if (fechaFinUtil.before(fechaCompraUtil)) {
                request.setAttribute("mensaje", "La fecha fin debe ser mayor que la fecha de compra.");
                request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);
                return;
            }

            long diffMilis = fechaFinUtil.getTime() - fechaCompraUtil.getTime();
            double diffAnios = diffMilis / (1000.0 * 60 * 60 * 24 * 365);
            int aniosCalculados = (int) Math.round(diffAnios);

            if (vidaUtil != aniosCalculados) {
                request.setAttribute("mensaje", "La vida útil debe ser exactamente " + aniosCalculados + " año(s).");
                request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);
                return;
            }

            int idUsuario = 1;

            java.sql.Date fechaCompra = new java.sql.Date(fechaCompraUtil.getTime());
            java.sql.Date fechaFin = new java.sql.Date(fechaFinUtil.getTime());

            Licencia licencia = new Licencia();
            licencia.setTipoLicencia(tipo);
            licencia.setCosto(costo);
            licencia.setFechaCompra(fechaCompra);
            licencia.setFechaFin(fechaFin);
            licencia.setVidaUtil(vidaUtil);
            licencia.setIdUsuario(idUsuario);
            licencia.setValorEnLibros(0);
            licencia.setValorPendiente(0);

            boolean exito = dao.insertar(licencia);

            if (exito) {
                request.setAttribute("mensaje", "Licencia guardada correctamente.");

                request.removeAttribute("tipoValor");
                request.removeAttribute("costoValor");
                request.removeAttribute("fechaCompraValor");
                request.removeAttribute("fechaFinValor");
                request.removeAttribute("vidaUtilValor");

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(crearLicenciaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
