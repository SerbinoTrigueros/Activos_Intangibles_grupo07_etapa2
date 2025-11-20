package servicio;

import controlador.UsuarioDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final ConexionBD conexionBD = new ConexionBD();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");

        Connection conn = null;

        try {
            conn = conexionBD.conectar();

            if (conn == null) {
                request.setAttribute("mensaje", "No se pudo conectar a la base de datos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            UsuarioDAO dao = new UsuarioDAO(conn);
            Usuario admin = dao.login(email, contrasena);

            if (admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", admin);
                response.sendRedirect("menuLicencia.jsp");
            } else {
                request.setAttribute("mensaje", "Correo o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            request.setAttribute("mensaje", "Error de base de datos: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } finally {
            conexionBD.desconectar(conn);
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
        return "Servlet de login seguro";
    }
}
