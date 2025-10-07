<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mensaje del Sistema</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: #f8f9fa;
            font-family: "Segoe UI", sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        /* Tarjeta central */
        .card {
            width: 28rem;
            background: #ffffff;
            border: 2px solid #B22222; /* Rojo sangre */
            border-radius: 20px;
            box-shadow: 0 4px 15px rgba(139, 0, 0, 0.25);
            padding: 2rem;
        }

        /* Título */
        .card h4 {
            background: linear-gradient(90deg, #8B0000, #B22222);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            font-weight: 700;
            margin-bottom: 1rem;
        }

        /* Texto del mensaje */
        .card p {
            color: #333;
            font-size: 1.1rem;
            margin-bottom: 1.5rem;
        }

        /* Botón de volver */
        .btn-volver {
            background: linear-gradient(90deg, #B22222, #8B0000);
            border: none;
            color: #FFD700;
            font-weight: 600;
            border-radius: 10px;
            transition: all 0.3s ease-in-out;
        }

        .btn-volver:hover {
            background: linear-gradient(90deg, #8B0000, #B22222);
            box-shadow: 0 0 10px rgba(255, 215, 0, 0.6);
            color: #fff;
        }
    </style>
</head>

<body>

    <div class="card text-center">
        <h4>Resultado del Proceso</h4>
        <p><%= request.getAttribute("mensaje") %></p>
        <a href="menu.jsp" target="_top" class="btn btn-volver w-100 mt-2">Volver al Menú</a>

    </div>

</body>
</html>

