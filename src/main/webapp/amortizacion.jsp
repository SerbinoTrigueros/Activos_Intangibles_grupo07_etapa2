<%-- 
    Document   : amortizacion
    Created on : 19 nov 2025, 12:29:54
    Author     : serbi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración Contable de Activos Intangibles</title>
        <style>
            body { font-family: Arial, sans-serif; margin: 20px; }
            .container { max-width: 1000px; margin: auto; }
            h2 { color: #333; }
            .form-group { margin-bottom: 15px; }
            label { font-weight: bold; margin-right: 10px; }
            input[type="text"], select { padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
            button { padding: 10px 15px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
            button:hover { background-color: #0056b3; }
            table { width: 100%; border-collapse: collapse; margin-top: 20px; }
            th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
            th { background-color: #f2f2f2; }
            .mensaje { padding: 10px; margin-bottom: 20px; border-radius: 4px; }
            .error { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
            .exito { background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
            .pendiente { color: orange; font-weight: bold; }
            .pagada { color: green; font-weight: bold; }
        </style>
    </head>
    <body>
        <div class="container">
            <h2> Mostrar Amortizaciones</h2>
            
            <%-- Mostrar mensajes del Servlet --%>
            <c:if test="${not empty mensaje}">
                <p class="mensaje ${mensaje.toLowerCase().contains('error') ? 'error' : 'exito'}">
                    ${mensaje}
                </p>
            </c:if>

            <form action="AmortizacionServlet" method="post">
                <input type="hidden" name="accion" value="mostrar">
                
                <div class="form-group">
                    <label for="idLicencia">ID Licencia:</label>
                    <input type="text" id="idLicencia" name="idLicencia" value="${idLicenciaActual}" required>

                    <label for="cbTipo">Tipo:</label>
                    <select id="cbTipo" name="cbTipo">
                        <option value="mensual" ${tipoActual eq 'mensual' ? 'selected' : ''}>Mensual</option>
                        <option value="anual" ${tipoActual eq 'anual' ? 'selected' : ''}>Anual</option>
                        <option value="acumulado" ${tipoActual eq 'acumulado' ? 'selected' : ''}>Acumulado</option>
                    </select>

                    <label for="cbEstado">Estado:</label>
                    <select id="cbEstado" name="cbEstado">
                        <option value="todos" ${estadoActual eq 'todos' ? 'selected' : ''}>Todos</option>
                        <option value="pendiente" ${estadoActual eq 'pendiente' ? 'selected' : ''}>Pendiente</option>
                        <option value="pagada" ${estadoActual eq 'pagada' ? 'selected' : ''}>Pagada</option>
                    </select>

                    <button type="submit">Mostrar Amortizaciones</button>
                </div>
            </form>

            <%-- Tabla de Amortizaciones --%>
            <c:if test="${not empty listaAmortizaciones}">
                <h3>Resultados de Amortización</h3>
                <table>
                    <thead>
                        <tr>
                            <c:choose>
                                <c:when test="${tipoActual eq 'acumulado'}">
                                    <th>ID Licencia</th>
                                    <th>Tipo</th>
                                    <th>Monto Total</th>
                                    <th></th>
                                    <th></th>
                                </c:when>
                                <c:otherwise>
                                    <th>ID</th>
                                    <th>Tipo</th>
                                    <th>Monto</th>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                    <th>Acción</th>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="a" items="${listaAmortizaciones}">
                            <tr>
                                <c:choose>
                                    <c:when test="${tipoActual eq 'acumulado'}">
                                        <td>${a.idLicencia}</td>
                                        <td>${a.tipoCartera}</td>
                                        <td><fmt:formatNumber value="${a.monto}" type="currency" currencySymbol="$" maxFractionDigits="2"/></td>
                                        <td></td>
                                        <td></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>${a.idAmortizacion}</td>
                                        <td>${a.tipoCartera}</td>
                                        <td><fmt:formatNumber value="${a.monto}" type="currency" currencySymbol="$" maxFractionDigits="2"/></td>
                                        <td><fmt:formatDate value="${a.fechaRegistro}" pattern="dd/MM/yyyy"/></td>
                                        <td class="${a.estado eq 'pendiente' ? 'pendiente' : 'pagada'}">${a.estado}</td>
                                        <td>
                                            <c:if test="${a.estado eq 'pendiente'}">
                                                <form action="AmortizacionServlet" method="post" style="display:inline;">
                                                    <input type="hidden" name="accion" value="actualizarEstado">
                                                    <input type="hidden" name="idAmortizacion" value="${a.idAmortizacion}">
                                                    <%-- Parámetros para retornar a la vista actual --%>
                                                    <input type="hidden" name="idLicenciaRetorno" value="${idLicenciaActual}">
                                                    <input type="hidden" name="tipoRetorno" value="${tipoActual}">
                                                    <input type="hidden" name="estadoRetorno" value="${estadoActual}">
                                                    <button type="submit" onclick="return confirm('¿Marcar como PAGADA?');">Pagar</button>
                                                </form>
                                            </c:if>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
            <%-- Botón de regreso --%>
        <div style="text-align: center; margin-top: 20px;">
            <a href="ContabilidadServlet" style="text-decoration: none; padding: 10px 20px; background-color: #3498db; color: white; border-radius: 4px;">Volver a Administración</a>
        </div>
    </body>
</html>