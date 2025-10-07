package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.Usuario;
import servicio.ReniecServicio;
import servicio.UsuarioServicio;

/**
 * Servlet que procesa el registro de un nuevo usuario
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {

    private UsuarioServicio usuarioServicio = new UsuarioServicio();
    private ReniecServicio reniecServicio = new ReniecServicio();

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
        // Obtenemos los datos del formulario
        try {
            // Obtenemos los datos del formulario
            String dni = request.getParameter("dni");
            String nombres = reniecServicio.obtenerNombrePorDNI(dni);
            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            // Mostramos en consola para debug
            System.out.println("📌 DNI: " + dni);
            System.out.println("📌 Nombres: " + nombres);
            System.out.println("📌 Usuario: " + usuario);
            System.out.println("📌 Email: " + email);

            // Creamos objeto usuario
            Usuario u = new Usuario();
            u.setDni(dni);
            u.setNombres(nombres);
            u.setUsuario(usuario);
            u.setPassword(password);
            u.setEmail(email);

            // Guardamos en base de datos
            if (usuarioServicio.registrarUsuario(u)) {
                response.sendRedirect("login.jsp");
            } else {
                response.getWriter().println("❌ Error al registrar en la base de datos. Revisa consola.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Error inesperado: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
