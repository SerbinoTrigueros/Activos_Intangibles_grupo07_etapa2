<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Licencia de Software</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/crearLicencia.css?v=<%= System.currentTimeMillis() %>">


<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/themes/dark.css" />

</head>
<body>

  <div class="form-container">

    <h2>Licencia de Software</h2>

    <% if (request.getAttribute("mensaje") != null) { %>
        <div class="mensaje"><%= request.getAttribute("mensaje") %></div>
    <% } %>

    <form id="formLicencia" action="crearLicenciaServlet" method="post">

      <label for="tipo">Tipo de Licencia:</label>
      <select id="tipo" name="tipo">
        <option>Microsoft Office</option>
        <option>MySQL</option>
        <option>SQL Server</option>
        <option>Windows</option>
        <option>SAP</option>
        <option>Visual Studio</option>
      </select>

      <label for="costo">Costo:</label>
      <input type="number" id="costo" name="costo" placeholder="$">

      <label for="fechaCompra">Fecha de Compra:</label>
      <input type="text" id="fechaCompra" name="fechaCompra" placeholder="Selecciona fecha" readonly>

      <label for="vidaUtil">Vida Útil:</label>
      <input type="text" id="vidaUtil" name="vidaUtil" placeholder="Ej. 1 año">

      <label for="fechaFin">Fecha de Fin:</label>
      <input type="text" id="fechaFin" name="fechaFin" placeholder="Selecciona fecha" readonly>

      <div class="btn-container">
        <button type="submit">Guardar</button>
        <button type="button" onclick="document.getElementById('formLicencia').reset();">Cancelar</button>
        <button type="button" onclick="window.location.href='menuGestion.jsp'">Atrás</button>
      </div>

    </form>

  </div>

  <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
  <script>
    flatpickr("#fechaCompra", { dateFormat: "Y-m-d", theme: "dark" });
    flatpickr("#fechaFin", { dateFormat: "Y-m-d", theme: "dark" });
  </script>

</body>
</html>
