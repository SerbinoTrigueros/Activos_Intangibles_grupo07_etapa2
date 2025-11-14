/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servicio;


import controlador.LicenciaDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
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
            throws ServletException, IOException, SQLException {
  
        // Obtener los datos del formulario
        String tipo = request.getParameter("tipo");
        String costoStr = request.getParameter("costo");
        String fechaCompraStr = request.getParameter("fechaCompra");
        String fechaFinStr = request.getParameter("fechaFin");
        String vidaUtilStr = request.getParameter("vidaUtil");

        Connection conn = null;

        try {
            // Conexión a la base de datos
            conn = ConexionBD.conectar();
            LicenciaDAO dao = new LicenciaDAO(conn);

            // Obtener usuario logueado de la sesión
            HttpSession session = request.getSession();
            Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
            if (usuarioLogueado == null) {
                request.setAttribute("mensaje", "No hay usuario logueado.");
                request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);
                return;
            }

            int idUsuario = usuarioLogueado.getIdUsuario(); // Aquí obtenemos el id real

            // Convertir los datos
            double costo = Double.parseDouble(costoStr);
            int vidaUtil = Integer.parseInt(vidaUtilStr);

           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           java.util.Date fechaCompraUtil = sdf.parse(fechaCompraStr);
           java.util.Date fechaFinUtil = sdf.parse(fechaFinStr);

            // Convertir a java.sql.Date
        java.sql.Date fechaCompra = new java.sql.Date(fechaCompraUtil.getTime());
        java.sql.Date fechaFin = new java.sql.Date(fechaFinUtil.getTime());
    
        Licencia licencia = new Licencia();
        licencia.setTipoLicencia(tipo);
        licencia.setCosto(costo);
        licencia.setFechaCompra(fechaCompra); // <-- ahora es java.sql.Date
        licencia.setFechaFin(fechaFin);       // <-- ahora es java.sql.Date
        licencia.setVidaUtil(vidaUtil);
        licencia.setIdUsuario(idUsuario);

            // Guardar en la base de datos
            boolean exito = dao.agregarLicencia(licencia);
            if (exito) {
                request.setAttribute("mensaje", "Licencia guardada correctamente.");
            } else {
                request.setAttribute("mensaje", "Error al guardar la licencia.");
            }

            request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error de base de datos: " + e.getMessage());
            request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error: " + e.getMessage());
            request.getRequestDispatcher("crearLicencia.jsp").forward(request, response);
        } finally {
            ConexionBD.desconectar(conn);
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
