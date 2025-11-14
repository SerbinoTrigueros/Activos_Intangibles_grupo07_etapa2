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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");

        Connection conn = null;
        try {
            // Intentar conectar a la base de datos
            conn = ConexionBD.conectar();

            if (conn == null) {
                request.setAttribute("mensaje", "No se pudo conectar a la base de datos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return; // salir si no hay conexión
            }

            // Crear DAO con la conexión
            UsuarioDAO dao = new UsuarioDAO(conn);
            Usuario admin = dao.login(email, contrasena);

            if (admin != null) {
                // Crear sesión y redirigir al menú
                HttpSession session = request.getSession();
                session.setAttribute("usuario", admin);
                response.sendRedirect("menuLicencia.jsp"); // reemplaza "inicio.jsp" por tu interfaz
            } else {
                // Credenciales incorrectas
                request.setAttribute("mensaje", "Correo o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error de base de datos: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } finally {
            ConexionBD.desconectar(conn);
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
