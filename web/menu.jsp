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
    <title>Menú Principal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            margin: 0;
            font-family: "Segoe UI", sans-serif;
            background-color: #f8f9fa;
            display: flex;
            height: 100vh;
            overflow: hidden;
        }

        /* Sidebar */
        .sidebar {
            width: 250px;
            background: linear-gradient(180deg, #5A0000, #B22222);
            color: white;
            display: flex;
            flex-direction: column;
            padding: 1rem;
            transition: width 0.3s ease;
        }

        .sidebar.collapsed {
            width: 80px;
        }

        .sidebar-header {
            font-size: 1.3rem;
            font-weight: bold;
            margin-bottom: 1.5rem;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .sidebar.collapsed .sidebar-header span {
            display: none;
        }

        .toggle-btn {
            background: none;
            border: none;
            color: white;
            font-size: 1.3rem;
            cursor: pointer;
        }

        /* Menu links */
        .menu-link {
            color: white;
            text-decoration: none;
            display: flex;
            align-items: center;
            gap: 10px;
            padding: 0.75rem 1rem;
            border-radius: 8px;
            margin-bottom: 0.4rem;
            transition: background-color 0.3s, color 0.3s;
        }

        .menu-link i {
            font-size: 1.2rem;
        }

        .menu-link:hover {
            background-color: rgba(255, 255, 255, 0.15);
        }

        /* 🔹 Enlace activo */
        .menu-link.active {
            background: linear-gradient(90deg, #B22222, #8B0000);
            color: #FFD700 !important;
            font-weight: 600;
            box-shadow: 0 0 8px rgba(255, 215, 0, 0.6);
        }

        .menu-link.active i {
            color: #fff !important;
        }

        .sidebar.collapsed .menu-link span {
            display: none;
        }

        .sidebar.collapsed .menu-link {
            justify-content: center;
        }

        /* Logout */
        .logout {
            margin-top: auto;
            background-color: rgba(255, 255, 255, 0.1);
            color: #ffdddd;
            transition: background-color 0.3s;
        }

        .logout:hover {
            background-color: rgba(255, 255, 255, 0.25);
            color: white;
        }

        /* Main content */
        .content {
            flex-grow: 1;
            padding: 1.5rem;
            background-color: #f0f2f5;
            overflow-y: auto;
        }

        iframe {
            width: 100%;
            height: 100%;
            border: none;
            border-radius: 12px;
            background: white;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        @media (max-width: 768px) {
            .sidebar {
                position: fixed;
                z-index: 10;
                height: 100%;
                transform: translateX(-100%);
            }

            .sidebar.show {
                transform: translateX(0);
            }
        }
    </style>
</head>

<body>

    <!-- Sidebar -->
    <div class="sidebar" id="sidebar">
        <div class="sidebar-header">
            <span>Menú</span>
            <button class="toggle-btn" id="toggle-btn"><i class="bi bi-list"></i></button>
        </div>

        <a href="bienvenida.jsp" target="contenido" class="menu-link active">
            <i class="bi bi-house"></i> <span>Inicio</span>
        </a>
        <a href="crearCuenta.jsp" target="contenido" class="menu-link">
            <i class="bi bi-person-plus"></i> <span>Crear Cuenta</span>
        </a>
        <a href="deposito.jsp" target="contenido" class="menu-link">
            <i class="bi bi-cash-coin"></i> <span>Depósito</span>
        </a>
        <a href="consultaCuentas.jsp" target="contenido" class="menu-link">
            <i class="bi bi-search"></i> <span>Consultar Cuentas</span>
        </a>
        <a href="movimientos.jsp" target="contenido" class="menu-link">
            <i class="bi bi-clock-history"></i> <span>Movimientos</span>
        </a>

        <a href="login.jsp" class="menu-link logout">
            <i class="bi bi-box-arrow-right"></i> <span>Cerrar Sesión</span>
        </a>
        
    </div>
    

    <!-- Contenido principal -->
    <div class="content">
        <iframe name="contenido" src="bienvenida.jsp"></iframe>
        
                <!-- Include Timer -->
    <jsp:include page="/reutilizables/temporizador.jsp" />
    </div>
    
    <script>
        // Colapsar/expandir menú
        document.getElementById('toggle-btn').addEventListener('click', function() {
            document.getElementById('sidebar').classList.toggle('collapsed');
        });

        // Mantener el botón activo
        const links = document.querySelectorAll('.menu-link');
        links.forEach(link => {
            link.addEventListener('click', () => {
                links.forEach(l => l.classList.remove('active'));
                link.classList.add('active');
            });
        });
    </script>

</body>
</html>
