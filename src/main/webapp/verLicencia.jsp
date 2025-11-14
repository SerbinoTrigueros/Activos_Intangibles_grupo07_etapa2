<%-- 
    Document   : verLicencia
    Created on : 13 nov 2025, 17:05:30
    Author     : serbi
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Licencia" %>

<%
    List<Licencia> lista = (List<Licencia>) request.getAttribute("listaLicencias");
%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Ver Licencias</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/verLicencia.css">
</head>
<body>

<h1>Listado de Licencias</h1>

<% if (request.getAttribute("mensaje") != null) { %>
    <div class="error"><%= request.getAttribute("mensaje") %></div>
<% } %>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Tipo</th>
            <th>Costo</th>
            <th>Fecha Compra</th>
            <th>Fecha Fin</th>
            <th>Vida Útil</th>
            <th>Valor en Libros</th>
            <th>Valor Pendiente</th>
            <th>ID Usuario</th>
        </tr>
    </thead>
    <tbody>
    <% if (lista != null) {
        for (Licencia l : lista) { %>
            <tr>
                <td><%= l.getIdLicencia() %></td>
                <td><%= l.getTipoLicencia() %></td>
                <td><%= l.getCosto() %></td>
                <td><%= l.getFechaCompra() %></td>
                <td><%= l.getFechaFin() %></td>
                <td><%= l.getVidaUtil() %></td>
                <td><%= l.getValorEnLibros() %></td>
                <td><%= l.getValorPendiente() %></td>
                <td><%= l.getIdUsuario() %></td>
            </tr>
    <%  }
       } %>
    </tbody>
</table>

<div class="button-container">
    <button onclick="window.location.href='menuGestion.jsp'">Atrás</button>
</div>

</body>
</html>
