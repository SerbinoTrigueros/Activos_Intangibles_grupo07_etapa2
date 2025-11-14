<%-- 
    Document   : menuLicencia
    Created on : 13 nov 2025, 17:06:39
    Author     : serbi
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Gestión y Administración en Licencias de Software</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuLicencia.css">
</head>
<body>

  <div class="container">
    <h1>Gestión y Administración en Licencias de Software</h1>

    <div class="options">
      <div class="option-card" onclick="window.location.href='gestionarLicencias.jsp'">
        <h2>Gestión de Licencias</h2>
      </div>

      <div class="option-card" onclick="window.location.href='adminInfoContable.jsp'">
        <h2>Administración de Información Contable</h2>
      </div>
    </div>
  </div>

</body>
</html>

