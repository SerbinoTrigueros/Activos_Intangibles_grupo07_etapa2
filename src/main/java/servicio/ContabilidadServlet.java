/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servicio;


import controlador.ContabilidadDAO;
import java.io.IOException;
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
 * */

@WebServlet("/ContabilidadServlet")
public class ContabilidadServlet extends HttpServlet {

    private final ContabilidadDAO dao = new ContabilidadDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Licencia> listaLicencias = dao.listarLicencias();

        request.setAttribute("listaLicencias", listaLicencias);

        request.getRequestDispatcher("administrar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}