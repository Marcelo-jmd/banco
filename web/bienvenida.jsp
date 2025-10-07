<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Bienvenida</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

        <style>
            body {
                background-color: #ffffff; /* Fondo blanco */
                height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
                font-family: 'Segoe UI', sans-serif;
                border: 10px solid transparent;
                background-image: linear-gradient(#fff, #fff),
                    linear-gradient(180deg, #B22222, #FFD700); /* rojo sangre → dorado */
                background-origin: border-box;
                background-clip: content-box, border-box;
            }

            .welcome-card {
                background: #ffffff;
                border: 2px solid #B22222;
                border-radius: 1.5rem;
                box-shadow: 0 0 25px rgba(178, 34, 34, 0.25);
                max-width: 600px;
                padding: 3rem 2.5rem;
                text-align: center;
                transition: all 0.3s ease;
            }

            .welcome-card:hover {
                transform: translateY(-5px);
                box-shadow: 0 0 30px rgba(255, 215, 0, 0.4);
            }

            .icono {
                font-size: 3.5rem;
                color: #B22222;
                margin-bottom: 1rem;
            }

            .welcome-card h3 {
                color: #B22222;
                font-weight: 700;
                margin-bottom: 1rem;
            }

            .welcome-card p {
                color: #333;
                font-size: 1.1rem;
                margin-bottom: 1.5rem;
            }

            .btn-custom {
                background: linear-gradient(90deg, #B22222, #8B0000);
                border: none;
                font-weight: 600;
                color: #FFD700;
                box-shadow: 0 0 8px rgba(255, 215, 0, 0.6);
                transition: all 0.3s ease;
            }

            .btn-custom:hover {
                background: linear-gradient(90deg, #8B0000, #5A0000);
                color: #fff;
                box-shadow: 0 0 10px rgba(255, 215, 0, 0.8);
            }
        </style>
    </head>

    <body>
        <div class="welcome-card shadow-lg">
            <i class="bi bi-bank2 icono"></i>
            <h3>Bienvenido al Sistema Bancario</h3>
            <p>Selecciona una opción del menú para comenzar.</p>
            <!-- Botón opcional -->
            <!-- <a href="crearCuenta.jsp" target="contenido" class="btn btn-custom mt-3 px-4">Ir a Crear Cuenta</a> -->
        </div>
        <jsp:include page="/reutilizables/complementoTempo.jsp" />      
    </body>
</html>

