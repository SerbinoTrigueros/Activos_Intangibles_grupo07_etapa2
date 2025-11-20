
import controlador.AmortizacionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import modelo.Amortizacion;

@WebServlet(name = "AmortizacionServlet", urlPatterns = {"/AmortizacionServlet"})
public class AmortizacionServlet extends HttpServlet {

    private final AmortizacionDAO amortizacionDAO = new AmortizacionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        String idLicenciaStr = request.getParameter("idLicencia");

        if ("mostrar".equals(accion) && idLicenciaStr != null && !idLicenciaStr.isEmpty()) {

            int idLicencia = Integer.parseInt(idLicenciaStr);

            String tipo = request.getParameter("cbTipo");
            if (tipo == null || tipo.isEmpty()) {
                tipo = "mensual";
            }

            String estado = request.getParameter("cbEstado");
            if (estado == null || estado.isEmpty()) {
                estado = "todos";
            }

            String msgGenerar = amortizacionDAO.generarAmortizaciones(idLicencia);
            request.setAttribute("msgGenerar", msgGenerar);

            List<Amortizacion> lista = amortizacionDAO.listarPorLicencia(idLicencia, tipo, estado);

            request.setAttribute("lista", lista);
            request.setAttribute("idLicenciaActual", idLicencia);
            request.setAttribute("tipoActual", tipo);
            request.setAttribute("estadoActual", estado);

            request.getRequestDispatcher("amortizacion.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("amortizacion.jsp").forward(request, response);
    }
}
