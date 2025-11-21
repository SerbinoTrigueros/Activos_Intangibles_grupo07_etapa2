<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login - Activos Intangibles</title>
<link rel="stylesheet" href="css/login.css">
</head>

<body>

<div class="contenedor-login">

    <h2 class="titulo">Activos Intangibles</h2>

    <% if (request.getAttribute("mensaje") != null) { %>
        <div class="error"><%= request.getAttribute("mensaje") %></div>
    <% } %>

    <form action="LoginServlet" method="post">

        <div class="grupo">
            <input type="text" name="email" required placeholder=" ">
            <label>Correo:</label>
        </div>

        <div class="grupo">
            <input type="password" name="contrasena" required placeholder=" ">
            <label>Contraseña:</label>
        </div>

        <button class="boton" type="submit">Ingresar</button>

    </form>

</div>

</body>
</html>
