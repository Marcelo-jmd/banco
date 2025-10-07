<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Registro Banco</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light d-flex align-items-center justify-content-center vh-100">

        <div class="card shadow-lg p-4 rounded-4" style="width: 28rem;">
            <h3 class="text-center mb-4 text-success">Registro de Usuario</h3>
            <form action="RegistroServlet" method="post">
                <div class="mb-3">
                    <label class="form-label">DNI</label>
                    <input type="text" id="dni" name="dni" class="form-control" maxlength="8" placeholder="Ingresa tu DNI" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Nombres (se completa automáticamente)</label>
                    <input type="text" id="nombres" name="nombres" class="form-control" readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">Usuario</label>
                    <input type="text" name="usuario" class="form-control" placeholder="Crea tu usuario" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Contraseña</label>
                    <input type="password" name="password" class="form-control" placeholder="Mínimo 8 caracteres" minlength="8" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" placeholder="ejemplo@correo.com" required>
                </div>
                <button type="submit" class="btn btn-success w-100">Registrar</button>
            </form>
            <div class="text-center mt-3">
                <small>¿Ya tienes cuenta? <a href="login.jsp" class="text-decoration-none">Inicia sesión</a></small>
            </div>
        </div>

        <!-- Script para autocompletar nombres -->
        <script>
            document.getElementById("dni").addEventListener("blur", function () {
                let dni = this.value;
                if (dni.length === 8) {
                    fetch("ReniecServlet?dni=" + dni)
                            .then(response => response.json())
                            .then(data => {
                                document.getElementById("nombres").value = data.nombres || "No encontrado";
                            })
                            .catch(err => console.error("Error:", err));
                }
            });
        </script>

    </body>
</html>
