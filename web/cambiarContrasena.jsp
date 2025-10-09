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
    <title>Cambiar Contraseña</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="p-4">
<div class="container">
    <h3 class="mb-4">🔒 Cambiar Contraseña</h3>
    
    <form action="CambiarContrasenaServlet" method="post">
        <div class="mb-3">
            <label class="form-label">Contraseña actual</label>
            <input type="password" name="actual" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Nueva contraseña</label>
            <input type="password" name="nueva" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Confirmar nueva contraseña</label>
            <input type="password" name="confirmar" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-danger">Actualizar Contraseña</button>
    </form>

    <% if (request.getAttribute("mensaje") != null) { %>
        <div class="alert alert-info mt-3">
            <%= request.getAttribute("mensaje") %>
        </div>
    <% } %>
</div>
</body>
</html>
