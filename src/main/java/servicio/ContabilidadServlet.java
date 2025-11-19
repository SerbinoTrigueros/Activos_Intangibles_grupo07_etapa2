/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servicio;

import controlador.ContabilidadDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import modelo.Licencia;

/**
 *
 * @author serbi
 */

@WebServlet("/ContabilidadServlet")
public class ContabilidadServlet extends HttpServlet {
    
    private final ContabilidadDAO dao = new ContabilidadDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Cargar y mostrar la lista de licencias (simulando cargarLicencias())
        List<Licencia> listaLicencias = dao.listarLicencias();
        
        // Poner la lista en el request para que el JSP la muestre
        request.setAttribute("listaLicencias", listaLicencias);
        
        // Redireccionar al JSP principal
        request.getRequestDispatcher("administrar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Lógica de POST (ej. para actualizar valores o consultar, si se implementa más tarde)
        // Por ahora, solo refrescamos la vista
        doGet(request, response); 
    }
}