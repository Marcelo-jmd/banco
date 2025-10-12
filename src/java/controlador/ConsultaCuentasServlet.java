package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Conexion;
import negocio.Cuenta;
import negocio.Usuario;

@WebServlet(name = "ConsultaCuentasServlet", urlPatterns = {"/ConsultaCuentasServlet"})
public class ConsultaCuentasServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        // ✅ Verificamos si hay sesión activa
        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String dniUsuario = usuario.getDni();

        try (Connection con = Conexion.getConnection()) {

            // 🔍 Si el DNI está vacío o null, lo consultamos desde la base de datos
            if (dniUsuario == null || dniUsuario.trim().isEmpty()) {
                String sqlUsuario = "SELECT dni FROM usuarios WHERE usuario = ?";
                PreparedStatement psu = con.prepareStatement(sqlUsuario);
                psu.setString(1, usuario.getUsuario());
                ResultSet rsu = psu.executeQuery();
                if (rsu.next()) {
                    dniUsuario = rsu.getString("dni").trim();
                    System.out.println("DNI obtenido desde BD: " + dniUsuario);
                }
            }

            System.out.println("DNI del usuario usado para buscar cuentas: [" + dniUsuario + "]");

            ArrayList<Cuenta> listaCuentas = new ArrayList<>();
            String sql = "SELECT NroCta, TipoCta, Moneda, Saldo, FechaApertura, Estado "
                       + "FROM cuentas WHERE dni = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dniUsuario.trim());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cuenta c = new Cuenta();
                c.setNroCta(rs.getString("NroCta"));
                c.setTipoCta(rs.getString("TipoCta"));
                c.setMoneda(rs.getString("Moneda"));
                c.setSaldo(rs.getDouble("Saldo"));
                c.setFechaApertura(rs.getDate("FechaApertura"));
                c.setEstado(rs.getString("Estado"));
                listaCuentas.add(c);
            }

            System.out.println("Cuentas encontradas: " + listaCuentas.size());

            request.setAttribute("listaCuentas", listaCuentas);
            request.getRequestDispatcher("consultaCuentas.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("❌ Error al consultar cuentas: " + e.getMessage());
            e.printStackTrace();
            response.getWriter().println("Error al consultar las cuentas.");
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
        return "Consulta las cuentas del usuario logueado";
    }

}
