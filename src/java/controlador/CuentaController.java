package controlador;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Conexion;

@WebServlet("/CuentaController")
public class CuentaController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipoCuenta = request.getParameter("tipo_cuenta");
        String dniUsuario = request.getParameter("dni_usuario");
        String moneda = request.getParameter("moneda");

        //Generar número de cuenta secuencial con prefijo según tipo
        String prefijo = switch (tipoCuenta) {
            case "AH" -> "AH";
            case "CO" -> "CO";
            case "PF" -> "PF";
            default -> "CU";
        };

        try (Connection con = Conexion.getConnection()) {

           //Si es cuenta a plazo fijo, verificar si tiene cuenta AH o CO
            if (tipoCuenta.equals("PF")) {
                String validarSQL = "SELECT COUNT(*) FROM cuentas WHERE dni = ? AND (TipoCta = 'AH' OR TipoCta = 'CO')";
                PreparedStatement psValidar = con.prepareStatement(validarSQL);
                psValidar.setString(1, dniUsuario);
                ResultSet rs = psValidar.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    request.setAttribute("mensaje", "❌ No puedes crear una cuenta a plazo fijo sin tener una cuenta de ahorro o corriente.");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    return; // Detiene el flujo
                }
            }

            //Obtener el número secuencial máximo actual del mismo tipo
            String sqlMax = "SELECT MAX(CAST(SUBSTRING(NroCta, 3) AS UNSIGNED)) FROM cuentas WHERE TipoCta = ?";
            PreparedStatement psMax = con.prepareStatement(sqlMax);
            psMax.setString(1, tipoCuenta);
            ResultSet rsMax = psMax.executeQuery();

            int secuencia = 1;
            if (rsMax.next() && rsMax.getInt(1) > 0) {
                secuencia = rsMax.getInt(1) + 1;
            }

            String nroCuenta = String.format("%s%06d", prefijo, secuencia);

            //Insertar nueva cuenta
            String sql = "INSERT INTO cuentas (NroCta, TipoCta, dni, Saldo, Estado, FechaApertura, Moneda) "
                    + "VALUES (?, ?, ?, 0.00, 'A', CURDATE(), ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nroCuenta);
            ps.setString(2, tipoCuenta);
            ps.setString(3, dniUsuario);
            ps.setString(4, moneda);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                request.setAttribute("mensaje", "✅ Cuenta creada correctamente. Número: " + nroCuenta);
            } else {
                request.setAttribute("mensaje", "⚠️ Error al crear la cuenta.");
            }

            request.getRequestDispatcher("mensaje.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "❌ Error del sistema: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
        }
    }
}

