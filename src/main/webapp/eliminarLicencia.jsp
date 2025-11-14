<%-- 
    Document   : eliminarLicencia
    Created on : 13 nov 2025, 17:04:08
    Author     : serbi
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Eliminar Licencia</title>

<!-- Enlace al CSS externo -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eliminarLicencia.css">

</head>
<body>

  <h2 class="titulo">Lista de Licencias Registradas</h2>

  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>Tipo de Licencia</th>
        <th>Costo</th>
        <th>Fecha Compra</th>
        <th>Fecha Fin</th>
        <th>Vida Útil</th>
        <th>Acción</th>
      </tr>
    </thead>
    <tbody>
      <!-- Aquí puedes reemplazar los datos estáticos por un bucle JSTL -->
      <tr>
        <td>1</td>
        <td>Microsoft Office</td>
        <td>$250</td>
        <td>2024-01-15</td>
        <td>2025-01-15</td>
        <td>1 año</td>
        <td>
          <form action="EliminarLicenciaServlet" method="post">
            <input type="hidden" name="idlicencia" value="1">
            <button type="submit">Eliminar</button>
          </form>
        </td>
      </tr>
      <tr>
        <td>2</td>
        <td>SQL Server</td>
        <td>$180</td>
        <td>2023-11-10</td>
        <td>2024-11-10</td>
        <td>1 año</td>
        <td>
          <form action="EliminarLicenciaServlet" method="post">
            <input type="hidden" name="idlicencia" value="2">
            <button type="submit">Eliminar</button>
          </form>
        </td>
      </tr>
    </tbody>
  </table>

  <div class="button-container">
    <button onclick="window.location='index.jsp'">Cancelar</button>
  </div>

</body>
</html>
