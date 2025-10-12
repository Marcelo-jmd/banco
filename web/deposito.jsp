<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="negocio.Usuario" %>
<%@ page session="true" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Depósito en Cuenta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow p-4">
        <h3 class="text-center mb-4 text-danger"><i class="bi bi-cash-coin"></i> Depósito en Cuenta</h3>

        <form action="DepositoServlet" method="post" id="depositoForm">
            <div class="mb-3">
                <label class="form-label">Número de Cuenta</label>
                <input type="text" name="nroCuenta" class="form-control" required maxlength="10">
            </div>

            <div class="mb-3">
                <label class="form-label">Monto</label>
                <input type="number" name="monto" id="monto" class="form-control" step="0.01" min="0.01" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Moneda</label>
                <select name="moneda" class="form-select" required>
                    <option value="Soles">Soles (S/)</option>
                    <option value="Dolares">Dólares ($)</option>
                </select>
            </div>

            <!-- Campos adicionales: se muestran solo si el monto supera 2000 -->
            <div id="camposExtra" style="display:none;">
                <div class="mb-3">
                    <label class="form-label">Origen de los fondos</label>
                    <input type="text" name="origen" class="form-control" placeholder="Ejemplo: salario, ahorro, venta..." maxlength="100">
                </div>

                <div class="mb-3">
                    <label class="form-label">Clave de autorización</label>
                    <input type="password" name="clave" class="form-control" placeholder="Tu contraseña de login">
                </div>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-success px-4">Realizar Depósito</button>
            </div>
        </form>
    </div>
</div>

<script>
document.getElementById("monto").addEventListener("input", function() {
    const extra = document.getElementById("camposExtra");
    const monto = parseFloat(this.value);
    extra.style.display = (monto > 2000) ? "block" : "none";
});
</script>

</body>
</html>
