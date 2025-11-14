<%-- 
    Document   : menuGestion
    Created on : 13 nov 2025, 17:07:36
    Author     : serbi
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Menú de Gestión de Licencias</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuGestion.css">
</head>
<body>

  <div class="container">
    <h1>Gestión de Licencias</h1>

    
    <div class="options">
     
        <div class="option-card" onclick="window.location.href='verLicenciaServlet'">
    <h2>Ver Licencia</h2>
</div>


      <div class="option-card" onclick="window.location.href='crearLicencia.jsp'">
        <h2>Crear Licencia</h2>
      </div>

      <div class="option-card" onclick="window.location.href='eliminarLicencia.jsp'">
        <h2>Eliminar Licencia</h2>
      </div>
    </div>

    <div class="button-container">
      <button onclick="window.location.href='menuPrincipal.jsp'">Atrás</button>
    </div>
  </div>

</body>
</html>
