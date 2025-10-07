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

    // 1️⃣ Recibir datos del formulario
    String tipoCuenta = request.getParameter("tipo_cuenta");
    String dniUsuario = request.getParameter("dni_usuario");
    String moneda = request.getParameter("moneda");

    try (Connection con = Conexion.getConnection()) {

        // 2️⃣ Generar número de cuenta consecutivo según tipo
        String prefijo = "";
        switch (tipoCuenta) {
            case "AH":
                prefijo = "AH";
                break;
            case "CO":
                prefijo = "CO";
                break;
            case "PL":
                prefijo = "PF"; // Plazo Fijo
                break;
        }

        // Consultar el último número de cuenta de ese tipo
        String sqlMax = "SELECT NroCta FROM cuentas WHERE NroCta LIKE ? ORDER BY NroCta DESC LIMIT 1";
        PreparedStatement psMax = con.prepareStatement(sqlMax);
        psMax.setString(1, prefijo + "%");
        ResultSet rs = psMax.executeQuery();

        int nuevoNumero = 1; // por defecto si no hay cuentas
        if (rs.next()) {
            String ultimoNro = rs.getString("NroCta"); // ejemplo: AH000012
            // Obtener solo el número y convertir a entero
            int ultimo = Integer.parseInt(ultimoNro.substring(2));
            nuevoNumero = ultimo + 1;
        }

        // Formatear el número de cuenta con ceros
        String nroCuenta = prefijo + String.format("%06d", nuevoNumero);

        // 3️⃣ Insertar nueva cuenta con moneda
        String sql = "INSERT INTO cuentas (NroCta, TipoCta, dni, Saldo, Estado, FechaApertura, Moneda) "
                   + "VALUES (?, ?, ?, 0.00, 'A', CURDATE(), ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nroCuenta);
        ps.setString(2, tipoCuenta);
        ps.setString(3, dniUsuario);
        ps.setString(4, moneda);

        int filas = ps.executeUpdate();

        if (filas > 0) {
            request.setAttribute("mensaje", "Cuenta creada correctamente. Número: " + nroCuenta);
        } else {
            request.setAttribute("mensaje", "Error al crear la cuenta.");
        }

        RequestDispatcher rd = request.getRequestDispatcher("mensaje.jsp");
        rd.forward(request, response);

    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("mensaje", "❌ Error del sistema: " + e.getMessage());
        request.getRequestDispatcher("mensaje.jsp").forward(request, response);
    }
}


}
