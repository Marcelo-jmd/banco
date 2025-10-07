package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import servicio.ReniecServicio;

/**
 * Servlet que consulta nombres en la API RENIEC usando el servicio
 * ReniecServicio
 */
@WebServlet(name = "ReniecServlet", urlPatterns = {"/ReniecServlet"})
public class ReniecServlet extends HttpServlet {

    private ReniecServicio reniecServicio = new ReniecServicio();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String nombres = reniecServicio.obtenerNombrePorDNI(dni);

        // Devolvemos JSON al frontend
        response.setContentType("application/json");
        JSONObject json = new JSONObject();
        json.put("nombres", nombres);
        response.getWriter().write(json.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
