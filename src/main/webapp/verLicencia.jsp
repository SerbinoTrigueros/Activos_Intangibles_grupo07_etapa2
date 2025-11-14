<%-- 
    Document   : verLicencia
    Created on : 13 nov 2025, 17:05:30
    Author     : serbi
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Listado de Licencias</title>

<!-- Enlace al CSS externo -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/verLicencia.css">

</head>
<body>

  <h2 class="titulo">Listado de Licencias Registradas</h2>

  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>Tipo de Licencia</th>
        <th>Costo</th>
        <th>Fecha Compra</th>
        <th>Fecha Fin</th>
        <th>Vida Útil</th>
      </tr>
    </thead>
    <tbody>
      <!-- Ejemplo de filas estáticas, luego puedes hacer que se generen dinámicamente con JSTL -->
      <tr>
        <td>1</td>
        <td>Microsoft Office</td>
        <td>$250</td>
        <td>2024-01-15</td>
        <td>2025-01-15</td>
        <td>1 año</td>
      </tr>
      <tr>
        <td>2</td>
        <td>SQL Server</td>
        <td>$180</td>
        <td>2023-11-10</td>
        <td>2024-11-10</td>
        <td>1 año</td>
      </tr>
    </tbody>
  </table>

  <div class="button-container">
    <button onclick="window.location='index.jsp'">Atrás</button>
  </div>

</body>
</html>
