<%-- 
    Document   : login
    Created on : 13 nov 2025, 16:57:46
    Author     : serbi
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Neon</title>
<style>
  body {
    background-color: #000;
    color: #0f0;
    font-family: 'Poppins', sans-serif;
    height: 100vh;
    margin: 0;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .login-container {
    background: rgba(0, 0, 0, 0.85);
    border: 2px solid #0f0;
    box-shadow: 0 0 25px #0f0;
    border-radius: 15px;
    padding: 40px;
    width: 320px;
    text-align: center;
  }

  .login-container h2 {
    margin-bottom: 25px;
    text-shadow: 0 0 15px #0f0;
    letter-spacing: 2px;
  }

  .login-container input[type="text"],
  .login-container input[type="password"] {
    width: 100%;
    padding: 12px;
    margin: 12px 0;
    background: #000;
    border: 2px solid #0f0;
    border-radius: 8px;
    color: #0f0;
    font-size: 16px;
    outline: none;
    box-shadow: 0 0 10px #0f0, inset 0 0 10px #0f0;
    transition: 0.3s ease;
    animation: glow 2s infinite alternate;
  }

  .login-container input:focus {
    box-shadow: 0 0 25px #0f0, inset 0 0 15px #0f0;
  }

  .login-container button {
    width: 100%;
    padding: 12px;
    background: #000;
    color: #0f0;
    border: 2px solid #0f0;
    border-radius: 8px;
    font-size: 18px;
    cursor: pointer;
    transition: 0.3s;
    box-shadow: 0 0 15px #0f0;
  }

  .login-container button:hover {
    background: #0f0;
    color: #000;
    box-shadow: 0 0 30px #0f0;
  }

  @keyframes glow {
    0% { box-shadow: 0 0 10px #00ff00, inset 0 0 5px #00ff00; }
    100% { box-shadow: 0 0 25px #00ff00, inset 0 0 15px #00ff00; }
  }

  .error {
    color: red;
    text-shadow: 0 0 5px red;
    margin-top: 10px;
  }
</style>
</head>
<body>

  <div class="login-container">
    <h2>Activos Intangibles</h2>

    <!-- Mostrar mensaje de error desde Servlet -->
    <%
        if (request.getAttribute("mensaje") != null) {
    %>
        <div class="error"><%= request.getAttribute("mensaje") %></div>
    <%
        }
    %>

    <form action="LoginServlet" method="post">
      <input type="text" name="email" placeholder="Correo" required>
      <input type="password" name="contrasena" placeholder="Contraseña" required>
      <button type="submit">Ingresar</button>
    </form>
  </div>

</body>
</html>
