/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author Dell
 */
import controlador.AmortizacionDAO;
import modelo.Amortizacion;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AmortizacionServlet")
public class AmortizacionServlet extends HttpServlet {
    
    private final AmortizacionDAO dao = new AmortizacionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "mostrar";
        }

        switch (accion) {
            case "mostrar":
                mostrarAmortizaciones(request, response);
                break;
            case "actualizarEstado":
                actualizarEstado(request, response);
                break;
            default:
                mostrarAmortizaciones(request, response);
                break;
        }
    }
    
    private void mostrarAmortizaciones(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. VARIABLES INICIALES
        String idLicenciaStr = request.getParameter("idLicencia");
        // Inicializamos con valores, pero como luego pueden cambiar, Java se queja en la lambda
        String tipo = request.getParameter("cbTipo") != null ? request.getParameter("cbTipo") : "mensual";
        String estado = request.getParameter("cbEstado") != null ? request.getParameter("cbEstado") : "todos";
        
        HttpSession session = request.getSession();

        // 2. LÓGICA DE SESIÓN (Aquí modificamos las variables, por eso dejan de ser "final")
        if (idLicenciaStr == null || idLicenciaStr.isEmpty()) {
            idLicenciaStr = (String) session.getAttribute("licenciaActual");
        }
        
        if (request.getParameter("cbTipo") == null && idLicenciaStr != null && !idLicenciaStr.isEmpty()) {
            tipo = (String) session.getAttribute("tipoActual") != null ? (String) session.getAttribute("tipoActual") : tipo;
        }
        
        if (request.getParameter("cbEstado") == null && idLicenciaStr != null && !idLicenciaStr.isEmpty()) {
            estado = (String) session.getAttribute("estadoActual") != null ? (String) session.getAttribute("estadoActual") : estado;
        }
        
        int idLicencia = 0;
        List<Amortizacion> listaAmortizaciones = null;
        String mensaje = null;

        try {
            if (idLicenciaStr != null && !idLicenciaStr.isEmpty()) {
                idLicencia = Integer.parseInt(idLicenciaStr);
                
                session.setAttribute("licenciaActual", idLicenciaStr);
                session.setAttribute("tipoActual", tipo);
                session.setAttribute("estadoActual", estado);
                
                if (!dao.licenciaExiste(idLicencia)) {
                    mensaje = "La licencia con ID " + idLicencia + " no existe.";
                    session.removeAttribute("licenciaActual");
                } else {
                    String generacionMensaje = dao.generarAmortizaciones(idLicencia);
                    if (!generacionMensaje.startsWith("Las amortizaciones")) {
                          mensaje = generacionMensaje; 
                    }
                    
                    listaAmortizaciones = dao.listarAmortizaciones(idLicencia, tipo);
                    
                    // --- CORRECCIÓN AQUÍ ---
                    // Creamos una variable final auxiliar para usarla dentro de la lambda
                    final String estadoFinal = estado; 
                    
                    if (!estado.equals("todos") && !tipo.equals("acumulado")) {
                        // Usamos estadoFinal en lugar de estado
                        listaAmortizaciones.removeIf(a -> !a.getEstado().equalsIgnoreCase(estadoFinal));
                    }
                    // -----------------------
                    
                    if (listaAmortizaciones.isEmpty() && mensaje == null) {
                        mensaje = "No hay amortizaciones registradas con esos filtros.";
                    }
                }
            } else {
                session.removeAttribute("licenciaActual");
                session.removeAttribute("tipoActual");
                session.removeAttribute("estadoActual");
            }

        } catch (NumberFormatException e) {
            mensaje = "Ingrese un ID de licencia válido.";
            session.removeAttribute("licenciaActual");
        }
        
        request.setAttribute("listaAmortizaciones", listaAmortizaciones);
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("idLicenciaActual", idLicenciaStr);
        request.setAttribute("tipoActual", tipo);
        request.setAttribute("estadoActual", estado);
        
        request.getRequestDispatcher("amortizaciones.jsp").forward(request, response);
    }
    
    private void actualizarEstado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idAmortizacionStr = request.getParameter("idAmortizacion");
        String mensaje = null;
        try {
            int idAmortizacion = Integer.parseInt(idAmortizacionStr);
            boolean exito = dao.actualizarEstado(idAmortizacion, "pagada");
            
            if (exito) {
                mensaje = "Amortización ID " + idAmortizacion + " marcada como pagada.";
            } else {
                mensaje = "Error al actualizar la amortización.";
            }
        } catch (NumberFormatException e) {
            mensaje = "ID de amortización no válido.";
        }
        
        request.setAttribute("mensaje", mensaje);
        mostrarAmortizaciones(request, response);
    }
}