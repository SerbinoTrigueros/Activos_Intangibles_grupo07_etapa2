

<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Amortización de Licencias</title>
    </head>
    <body>

        <h2>Consultar Amortización</h2>

        <form action="AmortizacionServlet" method="get">

            <label>ID Licencia:</label>
            <input type="number" name="idLicencia" required value="${idLicenciaActual}">

            <label>Tipo:</label>
            <select name="cbTipo">
                <option value="mensual"  ${tipoActual=='mensual'?'selected':''}>Mensual</option>
                <option value="anual"    ${tipoActual=='anual'?'selected':''}>Anual</option>
                <option value="acumulado" ${tipoActual=='acumulado'?'selected':''}>Acumulado</option>
            </select>

            <label>Estado:</label>
            <select name="cbEstado">
                <option value="todos"     ${estadoActual=='todos'?'selected':''}>Todos</option>
                <option value="pendiente" ${estadoActual=='pendiente'?'selected':''}>Pendiente</option>
                <option value="pagada"    ${estadoActual=='pagada'?'selected':''}>Pagada</option>
            </select>

            <input type="hidden" name="accion" value="mostrar">
            <button type="submit">Consultar</button>

        </form>

        <h3>${msgGenerar}</h3>

        <table border="1">
            <tr>
                <th>ID Amortización</th>
                <th>ID Licencia</th>
                <th>Tipo Cartera</th>
                <th>Monto</th>
                <th>Fecha Registro</th>
                <th>ID Cuota</th>
                <th>Estado</th>
            </tr>

            <c:forEach var="a" items="${lista}">
                <tr>
                    <td>${a.idAmortizacion}</td>
                    <td>${a.idLicencia}</td>
                    <td>${a.tipoCartera}</td>
                    <td>${a.monto}</td>
                    <td>${a.fechaRegistro}</td>
                    <td>${a.idCuota}</td>
                    <td>${a.estado}</td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>