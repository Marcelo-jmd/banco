<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Restablecer Contraseña</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>
            body {
                background: linear-gradient(180deg, #5A0000, #B22222); /* rojo sangre degradado */
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100vh;
                font-family: "Segoe UI", sans-serif;
            }
            .card {
                background-color: #fff;
                border: none;
                border-radius: 1.2rem;
                box-shadow: 0 0 20px rgba(255, 215, 0, 0.25); /* sombra dorada */
                width: 24rem;
                transition: all 0.3s ease;
            }

            h3 {
                color: #B22222;
                font-weight: bold;
            }

            .form-label {
                font-weight: 500;
                color: #5A0000;
            }

            .btn-success {
                background: linear-gradient(90deg, #B22222, #8B0000);
                border: none;
                font-weight: 600;
                color: #FFD700; /* dorado */
                box-shadow: 0 0 8px rgba(255, 215, 0, 0.6);
                transition: all 0.3s ease;
            }

            .btn-success:hover {
                background: linear-gradient(90deg, #8B0000, #5A0000);
                color: #fff;
                box-shadow: 0 0 10px rgba(255, 215, 0, 0.8);
            }

            .alert {
                font-size: 0.9rem;
            }

            a {
                color: #DAA520; /* dorado cálido */
                font-weight: 700;
                transition: color 0.3s, text-shadow 0.3s;
            }

            a:hover {
                color: #B22222; /* rojo sangre al pasar el mouse */
                text-decoration: underline;
                text-shadow: 0 0 6px rgba(255, 215, 0, 0.6);
            }
        </style>
    </head>
    <body class="d-flex align-items-center justify-content-center vh-100">

        <!-- Tarjeta centrada -->
        <div class="card shadow-lg p-4">
            <h3 class="text-center mb-4">Restablecer Contraseña</h3>

            <!-- 📢 Mensaje dinámico -->
            <%
                String mensaje = (String) request.getAttribute("mensaje");
                String tipo = (String) request.getAttribute("tipo");
                if (mensaje != null) {
            %>
            <div class="alert alert-<%= tipo%> alert-dismissible fade show" role="alert">
                <%= mensaje%>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
            </div>
            <% }%>

            <!-- Formulario -->
            <form action="RecuperarContrasenaServlet" method="post">
                <div class="mb-3">
                    <label class="form-label">Usuario</label>
                    <input type="text" name="usuario" class="form-control" placeholder="Ingresa tu usuario" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Nueva Contraseña</label>
                    <input type="password" name="password" class="form-control" placeholder="Mínimo 8 caracteres" minlength="8" required>
                </div>

                <button type="submit" class="btn btn-success w-100 mt-2">Actualizar Contraseña</button>
            </form>

            <!-- Enlace inferior -->
            <div class="text-center mt-3">
                <small>¿Recordaste tu contraseña?
                    <a href="login.jsp" class="text-decoration-none">Inicia sesión</a>
                </small>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
