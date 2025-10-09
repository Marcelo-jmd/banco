package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import negocio.Usuario;
import servicio.UsuarioServicio;

@WebServlet(name = "CambiarContrasenaServlet", urlPatterns = {"/CambiarContrasenaServlet"})
public class CambiarContrasenaServlet extends HttpServlet {

    private UsuarioServicio usuarioServicio = new UsuarioServicio();

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
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String actual = request.getParameter("actual");
        String nueva = request.getParameter("nueva");
        String confirmar = request.getParameter("confirmar");

        if (!nueva.equals(confirmar)) {
            request.setAttribute("mensaje", "⚠️ Las nuevas contraseñas no coinciden.");
            request.getRequestDispatcher("cambiarContrasena.jsp").forward(request, response);
            return;
        }

        boolean ok = usuarioServicio.cambiarPassword(usuario.getUsuario(), actual, nueva);

        if (ok) {
            request.setAttribute("mensaje", "✅ Contraseña actualizada correctamente.");
        } else {
            request.setAttribute("mensaje", "❌ Error: contraseña actual incorrecta.");
        }

        request.getRequestDispatcher("cambiarContrasena.jsp").forward(request, response);
    }

    @Override
public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
