<%-- 
    Document   : valorLibros
    Created on : 19 nov 2025, 12:32:09
    Author     : serbi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar Valor en Libros</title>
        <style>
            /* ... (Estilos CSS se mantienen igual) ... */
            body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
            .container { max-width: 500px; margin: auto; padding: 20px; background: white; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }
            h2 { color: #2c3e50; text-align: center; }
            .form-group { margin-bottom: 15px; display: flex; align-items: center; justify-content: space-between; }
            label { font-weight: bold; width: 50%; }
            input[type="text"] { width: 45%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
            button { padding: 10px 15px; background-color: #e67e22; color: white; border: none; border-radius: 4px; cursor: pointer; width: 100%; margin-top: 10px; }
            button:hover { background-color: #d35400; }
            .resultado-valor { font-size: 1.5em; font-weight: bold; color: #16a085; text-align: right; }
            .mensaje { padding: 10px; margin-bottom: 20px; border-radius: 4px; text-align: center; }
            .error { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
            .aviso { background-color: #fff3cd; color: #856404; border: 1px solid #ffeeba; }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>🔎 Consultar Valor en Libros</h2>
            
            <%-- Mostrar mensajes del Servlet --%>
            <c:if test="${not empty mensaje}">
                <p class="mensaje 
                    <c:choose>
                        <c:when test="${mensaje.toLowerCase().contains('error') || mensaje.toLowerCase().contains('no existe')}">error</c:when>
                        <c:otherwise>aviso</c:otherwise>
                    </c:choose>">
                    ${mensaje}
                </p>
            </c:if>

            <form action="ValorLibrosServlet" method="post">
                <div class="form-group">
                    <label for="idLicencia">ID Licencia:</label>
                    <input type="text" id="idLicencia" name="idLicencia" value="${idLicenciaActual}" required>
                </div>
                <button type="submit">Consultar Valor</button>
            </form>

            <c:if test="${not empty resultadoVL}">
                <hr style="margin: 20px 0;">
                <h3>Resultados (ID: ${resultadoVL.idLicencia})</h3>
                
                <fmt:setLocale value="es_ES"/> 
                
                <div class="form-group">
                    <label>Costo de Adquisición:</label>
                    <input type="text" value="<fmt:formatNumber value="${resultadoVL.costoAdquisicion}" type="currency" currencySymbol="$"/>" readonly>
                </div>
                
                <div class="form-group">
                    <label>Amortizaciones Acumuladas:</label>
                    <input type="text" value="<fmt:formatNumber value="${resultadoVL.amortizacionesAcumuladas}" type="currency" currencySymbol="$"/>" readonly>
                </div>
                
                <div class="form-group" style="margin-top: 20px;">
                    <label style="font-size: 1.2em; color: #16a085;">VALOR EN LIBROS:</label>
                    <input type="text" 
                           class="resultado-valor" 
                           value="<fmt:formatNumber value="${Math.abs(resultadoVL.valorEnLibros)}" type="currency" currencySymbol="$"/>" 
                           readonly>
                </div>

                <%-- LÓGICA DE CARGA: Muestra el mensaje si el valor en libros es NEGATIVO --%>
                <c:if test="${resultadoVL.valorEnLibros <= 0}">
                    <p class="mensaje error">
                        ⚠️ ¡ATENCIÓN! El Valor en Libros es negativo.
                        Esto se considera un Excedente de Amortización y debe cargarse en el libro mayor.
                    </p>
                </c:if>
                
            </c:if>
        </div>
        <%-- Botón de regreso --%>
        <div style="text-align: center; margin-top: 20px;">
            <a href="ContabilidadServlet" style="text-decoration: none; padding: 10px 20px; background-color: #3498db; color: white; border-radius: 4px;">Volver a Administración</a>
        </div>
    </body>
</html>
