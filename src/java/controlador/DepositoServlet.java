package controlador;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import negocio.CuentaDAO;
import negocio.UsuarioDAO;
import negocio.Usuario;
import servicio.UsuarioServicio;

/**
 * Controlador encargado de manejar los depósitos en cuentas
 */
@WebServlet("/DepositoServlet")
public class DepositoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nroCuenta = request.getParameter("nroCuenta");
        String montoStr = request.getParameter("monto");
        String moneda = request.getParameter("moneda");
        String origen = request.getParameter("origen");
        String clave = request.getParameter("clave");

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        try {
            double monto = Double.parseDouble(montoStr);
            if (monto <= 0) {
                request.setAttribute("mensaje", "El monto debe ser positivo.");
                request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                return;
            }

            CuentaDAO cuentaDAO = new CuentaDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            UsuarioServicio usuarioServicio = new UsuarioServicio(); // ✅ instancia correcta

            // Validar existencia de la cuenta
            if (!cuentaDAO.existeCuenta(nroCuenta)) {
                request.setAttribute("mensaje", "La cuenta ingresada no existe.");
                request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                return;
            }

            // 🔍 Verificar saldo actual
            double saldoActual = cuentaDAO.obtenerSaldo(nroCuenta);
            if (saldoActual == 0) {
                request.setAttribute("mensaje", "Saldo insuficiente: la cuenta tiene 0.00 " + moneda + ".");
                request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                return;
            }

            // Si el monto > 2000, validar clave de autorización
            if (monto > 2000) {
                if (clave == null || clave.isEmpty()) {
                    request.setAttribute("mensaje", "Debe ingresar su clave de autorización para depósitos mayores a S/ 2000.");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    return;
                }

                // ✅ Usar correctamente el servicio para encriptar
                String passwordHash = usuarioServicio.encriptar(clave);

                // Buscar usuario con esa contraseña encriptada
                Usuario validado = usuarioDAO.login(usuario.getUsuario(), passwordHash);

                if (validado == null) {
                    request.setAttribute("mensaje", "Clave de autorización incorrecta.");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    return;
                }
            }

            // ✅ Realizar depósito
            boolean ok = cuentaDAO.depositar(nroCuenta, monto);
            if (ok) {
                String msg = "Depósito realizado con éxito.<br>Monto: " + monto + " " + moneda;
                if (monto > 2000) {
                    msg += "<br>Origen de fondos: " + origen;
                }
                request.setAttribute("mensaje", msg);
            } else {
                request.setAttribute("mensaje", "Error al procesar el depósito.");
            }

            request.getRequestDispatcher("mensaje.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "Monto inválido.");
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error del sistema: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
        }
    }
}
