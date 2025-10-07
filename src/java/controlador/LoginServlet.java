package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.Usuario;
import servicio.UsuarioServicio;

/**
 * Servlet que procesa el inicio de sesión
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private UsuarioServicio servicio = new UsuarioServicio();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperamos datos del formulario
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        // Verificamos credenciales
        Usuario u = servicio.login(usuario, password);

        if (u != null) {
            // Si existe, guardamos en sesión
            request.getSession().setAttribute("usuario", u);
            response.sendRedirect("menu.jsp");
        } else {
            // Si falla, mostramos error
            response.getWriter().println("Usuario o contraseña incorrectos");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
