package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicio.UsuarioServicio;

@WebServlet(name = "RecuperarContrasenaServlet", urlPatterns = {"/RecuperarContrasenaServlet"})
public class RecuperarContrasenaServlet extends HttpServlet {

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
        String usuario = request.getParameter("usuario");
        String nuevaPassword = request.getParameter("password");

        boolean exito = servicio.actualizarPassword(usuario, nuevaPassword);

        if (exito) {
            request.setAttribute("mensaje", "✅ Contraseña actualizada correctamente.");
            request.setAttribute("tipo", "success");
        } else {
            request.setAttribute("mensaje", "❌ No se encontró el usuario o ocurrió un error.");
            request.setAttribute("tipo", "danger");
        }

        // Volvemos a la misma página con mensaje
        request.getRequestDispatcher("recuperarContrasena.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
