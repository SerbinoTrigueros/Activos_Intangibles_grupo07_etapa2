/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author Dell
 */
import controlador.ValorLibrosDAO;
import modelo.ValorLibros;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ValorLibrosServlet")
public class ValorLibrosServlet extends HttpServlet {

    private final ValorLibrosDAO dao = new ValorLibrosDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("valorLibros.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idLicenciaStr = request.getParameter("idLicencia");
        String mensaje = null;
        ValorLibros vl = null;

        try {
            int idLicencia = Integer.parseInt(idLicenciaStr);

            vl = dao.calcularValorLibros(idLicencia);

            if (vl == null) {
                mensaje = "La licencia con ID " + idLicencia + " no existe o hubo un error de BD.";
            } else if (vl.getAmortizacionesAcumuladas() == 0) {
                mensaje = "Licencia encontrada. No hay amortizaciones acumuladas registradas aún.";
            } else {
                mensaje = "Cálculo de Valor en Libros exitoso.";
            }

        } catch (NumberFormatException e) {
            mensaje = "Ingrese un ID de licencia válido (número entero).";
        } catch (Exception e) {
            mensaje = "Error inesperado al procesar la solicitud.";
            e.printStackTrace();
        }

        request.setAttribute("resultadoVL", vl);
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("idLicenciaActual", idLicenciaStr);

        request.getRequestDispatcher("valorLibros.jsp").forward(request, response);
    }
}
