<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login Banco</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Íconos Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

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

        .card:hover {
            transform: translateY(-3px);
            box-shadow: 0 0 25px rgba(255, 215, 0, 0.4);
        }

        h3 {
            color: #B22222;
            font-weight: bold;
        }

        .form-label {
            font-weight: 500;
            color: #5A0000;
        }

        .form-control:focus {
            border-color: #FFD700;
            box-shadow: 0 0 6px rgba(255, 215, 0, 0.5);
        }

        .btn-primary {
            background: linear-gradient(90deg, #B22222, #8B0000);
            border: none;
            font-weight: 600;
            color: #FFD700; /* dorado */
            box-shadow: 0 0 8px rgba(255, 215, 0, 0.6);
            transition: all 0.3s ease;
        }

        .btn-primary:hover {
            background: linear-gradient(90deg, #8B0000, #5A0000);
            color: #fff;
            box-shadow: 0 0 10px rgba(255, 215, 0, 0.8);
        }

        /* Texto inferior */
        small {
            color: #5A0000;
            font-weight: 500;
        }

        /* Enlace visible y elegante */
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

<body>

    <div class="card p-4 shadow-lg">
        <div class="text-center mb-3">
            <i class="bi bi-bank2" style="font-size: 2.5rem; color: #B22222;"></i>
        </div>
        <h3 class="text-center mb-4">Acceso Bancario</h3>

        <form action="LoginServlet" method="post">
            <div class="mb-3">
                <label class="form-label">Usuario</label>
                <input type="text" name="usuario" class="form-control" placeholder="Ingresa tu usuario" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Contraseña</label>
                <input type="password" name="password" class="form-control" placeholder="********" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Ingresar</button>
        </form>

        <div class="text-center mt-4">
            <small>¿No tienes cuenta? 
                <a href="registro.jsp" class="text-decoration-none">Regístrate aquí</a>
            </small>
        </div>
    </div>

</body>
</html>
