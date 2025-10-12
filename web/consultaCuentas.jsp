<%-- 
    Document   : consultaCuentas
    Created on : 12 oct. 2025, 12:53:08
    Author     : Marcelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, negocio.Cuenta" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Mis Cuentas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <div class="container mt-4">
            <div class="card shadow p-3">
                <h3 class="text-primary mb-3"><i class="bi bi-bank"></i> Mis Cuentas</h3>

                <%
                    ArrayList<Cuenta> lista = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");
                    if (lista == null || lista.isEmpty()) {
                %>
                <div class="alert alert-warning">No se encontraron cuentas registradas. </div>
                <% } else { %>

                <table class="table table-striped table-hover align-middle">
                    <thead class="table-primary">
                        <tr>
                            <th>Número de Cuenta</th>
                            <th>Tipo de Cuenta</th>
                            <th>Moneda</th>
                            <th>Saldo</th>
                            <th>Fecha de Apertura</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Cuenta c : lista) {%>
                        <tr>
                            <td><%= c.getNroCta()%></td>
                            <td><%= c.getTipoDescripcion()%></td>
                            <td><%= c.getMoneda()%></td>
                            <td><%= String.format("%.2f", c.getSaldo())%></td>
                            <td><%= c.getFechaApertura()%></td>
                            <td>
                                <% if ("A".equals(c.getEstado())) { %>
                                <span class="badge bg-success">Activo</span>
                                <% } else { %>
                                <span class="badge bg-danger">Inactivo</span>
                                <% } %>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <% }%>
            </div>
        </div>

    </body>
</html>