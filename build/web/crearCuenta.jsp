<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="negocio.Usuario" %>
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
    <title>Crear Cuenta Bancaria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            background-color: #ffffff;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', sans-serif;
            border: 10px solid transparent;
            background-image: linear-gradient(#fff, #fff),
                              linear-gradient(180deg, #B22222, #FFD700); /* rojo sangre → dorado */
            background-origin: border-box;
            background-clip: content-box, border-box;
        }

        .card {
            background-color: #fff;
            border: 2px solid #B22222;
            border-radius: 1.5rem;
            box-shadow: 0 0 25px rgba(178, 34, 34, 0.25);
            padding: 2rem;
            width: 32rem;
            transition: all 0.3s ease;
        }

        .card:hover {
            transform: translateY(-4px);
            box-shadow: 0 0 30px rgba(255, 215, 0, 0.4);
        }

        h4 {
            color: #B22222;
            font-weight: 700;
            margin-bottom: 1.5rem;
        }

        .form-label {
            color: #5A0000;
            font-weight: 600;
        }

        .form-control, .form-select {
            border: 1px solid #B22222;
            border-radius: 0.5rem;
            transition: box-shadow 0.3s, border-color 0.3s;
        }

        .form-control:focus, .form-select:focus {
            border-color: #FFD700;
            box-shadow: 0 0 6px rgba(255, 215, 0, 0.6);
        }

        .btn-success {
            background: linear-gradient(90deg, #B22222, #8B0000);
            border: none;
            font-weight: 600;
            color: #FFD700;
            box-shadow: 0 0 8px rgba(255, 215, 0, 0.5);
            transition: all 0.3s ease;
        }

        .btn-success:hover {
            background: linear-gradient(90deg, #8B0000, #5A0000);
            color: #fff;
            box-shadow: 0 0 12px rgba(255, 215, 0, 0.8);
        }

        .btn-outline-secondary {
            border-color: #B22222;
            color: #B22222;
            font-weight: 600;
            transition: all 0.3s ease;
        }

        .btn-outline-secondary:hover {
            background: #B22222;
            color: #FFD700;
            box-shadow: 0 0 10px rgba(255, 215, 0, 0.5);
        }

        .icono {
            font-size: 3rem;
            color: #B22222;
            margin-bottom: 0.8rem;
        }
    </style>
</head>

<body>
    <div class="card shadow-lg">
        <div class="text-center">
            <i class="bi bi-wallet2 icono"></i>
            <h4>Crear Nueva Cuenta</h4>
        </div>

        <form action="CuentaController" method="post">

            <!-- Tipo de cuenta -->
            <div class="mb-3">
                <label class="form-label">Tipo de Cuenta</label>
                <select name="tipo_cuenta" class="form-select" required>
                    <option value="">-- Selecciona un tipo --</option>
                    <option value="AH">Cuenta de Ahorro</option>
                    <option value="CO">Cuenta Corriente</option>
                    <option value="PF">Cuenta a Plazo Fijo</option>
                </select>
            </div>

            <!-- Moneda -->
            <div class="mb-3">
                <label class="form-label">Moneda</label>
                <select name="moneda" class="form-select" required>
                    <option value="">-- Selecciona una moneda --</option>
                    <option value="PEN">Soles</option>
                    <option value="USD">Dólares</option>
                </select>
            </div>

            <!-- DNI -->
            <div class="mb-3">
                <label class="form-label">DNI del Titular</label>
                <input type="text" name="dni_usuario" class="form-control" value="<%= usuario.getDni() %>" readonly>
            </div>

            <!-- Botón enviar -->
            <button type="submit" class="btn btn-success w-100">Crear Cuenta</button>

            <!-- Botón volver -->
            <!-- <a href="menu.jsp" class="btn btn-outline-secondary w-100 mt-3">Volver al Menú</a> -->
        </form>
    </div>
     <jsp:include page="/reutilizables/complementoTempo.jsp" />           
</body>
</html>
