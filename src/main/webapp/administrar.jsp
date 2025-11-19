<%-- 
    Document   : administrar
    Created on : 19 nov 2025, 12:26:59
    Author     : serbi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar Información Contable</title>
        <style>
            body { font-family: Arial, sans-serif; margin: 20px; }
            .container { max-width: 900px; margin: auto; }
            h2 { color: #2c3e50; }
            .button-group { margin-top: 20px; margin-bottom: 20px; }
            .button-group a, .button-group button { 
                padding: 10px 15px; background-color: #2980b9; color: white; border: none; 
                border-radius: 4px; cursor: pointer; text-decoration: none; margin-right: 10px; 
            }
            .button-group a:hover, .button-group button:hover { background-color: #1a5276; }
            table { width: 100%; border-collapse: collapse; margin-top: 20px; }
            th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
            th { background-color: #ecf0f1; color: #333; }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Administración de Información Contable - SoftControl</h2>  <!<!-- titulo de la pantalla -->
            
            <div class="button-group">
                <%-- Botón que redirige al Servlet de Amortizaciones --%>
                <a href="<c:url value="/AmortizacionServlet"/>">Mostrar Amortizaciones</a>
                
                <%-- Botón para recargar (Refrescar) la lista de licencias --%>
                <a href="<c:url value="/ContabilidadServlet"/>">Refrescar Licencias</a>
                
                <%-- Botón para "Consultar valores en libros" --%>
                <a href="<c:url value="/ValorLibrosServlet"/>">Consultar Valores en Libros</a>
            </div>
            
            <h3>Licencias Registradas</h3>
            
            <c:choose>
                <c:when test="${not empty listaLicencias}">
                    <table>
                        <thead>
                            <tr>
                                <th>ID Licencia</th>
                                <th>Tipo Licencia</th>
                                <th>Costo</th>
                                <th>Fecha Compra</th>
                                <th>Fecha Fin</th>
                                <th>Vida Útil (Años)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="lic" items="${listaLicencias}">
                                <tr>
                                    <td>${lic.idLicencia}</td>
                                    <td>${lic.tipoLicencia}</td>
                                    <td><fmt:formatNumber value="${lic.costo}" type="currency" currencySymbol="$" maxFractionDigits="2"/></td>
                                    <td><fmt:formatDate value="${lic.fechaCompra}" pattern="dd/MM/yyyy"/></td>
                                    <td><fmt:formatDate value="${lic.fechaFin}" pattern="dd/MM/yyyy"/></td>
                                    <td>${lic.vidaUtil}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>No hay licencias registradas en la base de datos.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>