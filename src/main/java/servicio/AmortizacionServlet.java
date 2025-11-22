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
            accion = "mostrar"; // Acción por defecto
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
        
        String idLicenciaStr = request.getParameter("idLicencia");
        String tipo = request.getParameter("cbTipo");
        String estado = request.getParameter("cbEstado");
        
        // Valores por defecto
        int idLicencia = 0;
        List<Amortizacion> listaAmortizaciones = null;
        String mensaje = null;

        try {
            if (idLicenciaStr != null && !idLicenciaStr.isEmpty()) {
                idLicencia = Integer.parseInt(idLicenciaStr);
                
                if (!dao.licenciaExiste(idLicencia)) {
                    mensaje = "La licencia con ID " + idLicencia + " no existe.";
                } else {
                    // 1. Generar amortizaciones si no existen (Lógica de negocio)
                    String generacionMensaje = dao.generarAmortizaciones(idLicencia);
                    if (!generacionMensaje.startsWith("Las amortizaciones")) {
                         // Solo si se generaron por primera vez (o hubo error grave)
                         mensaje = generacionMensaje; 
                    }
                    
                    // 2. Listar y filtrar
                    listaAmortizaciones = dao.listarAmortizaciones(idLicencia, tipo);
                    
                    // Filtrado adicional por estado, necesario porque el DAO de listar no filtra
                    // totalmente por estado y tipo al mismo tiempo
                    if (!estado.equals("todos") && !tipo.equals("acumulado")) {
                        listaAmortizaciones.removeIf(a -> !a.getEstado().equalsIgnoreCase(estado));
                    }
                    
                    if (listaAmortizaciones.isEmpty() && mensaje == null) {
                        mensaje = "No hay amortizaciones registradas con esos filtros.";
                    }
                }
            }

        } catch (NumberFormatException e) {
            mensaje = "Ingrese un ID de licencia válido.";
        }
        
        // Poner datos en el request para que el JSP los muestre
        request.setAttribute("listaAmortizaciones", listaAmortizaciones);
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("idLicenciaActual", idLicenciaStr);
        request.setAttribute("tipoActual", tipo != null ? tipo : "mensual");
        request.setAttribute("estadoActual", estado != null ? estado : "todos");
                                    //nombre del jsp
        request.getRequestDispatcher("amortizaciones.jsp").forward(request, response);
    }
    
    private void actualizarEstado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idAmortizacionStr = request.getParameter("idAmortizacion");
        String idLicenciaRetorno = request.getParameter("idLicenciaRetorno");
        String tipoRetorno = request.getParameter("tipoRetorno");
        String estadoRetorno = request.getParameter("estadoRetorno");

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
        
        // Redirigir de nuevo a la vista de la licencia, manteniendo los filtros
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("idLicencia", idLicenciaRetorno);
        request.setAttribute("cbTipo", tipoRetorno);
        request.setAttribute("cbEstado", estadoRetorno);
        mostrarAmortizaciones(request, response);
    }
}