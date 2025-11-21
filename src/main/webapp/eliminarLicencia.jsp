<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Eliminar Licencias</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/eliminarLicencia.css?v=<%= System.currentTimeMillis() %>">

    </head>
    <body>

        <h2>Gestión - Eliminar Licencias</h2>

        <c:if test="${mensaje != null}">
            <p style="color:red;">${mensaje}</p>
        </c:if>

        <table border="1" cellpadding="6">
            <tr>
                <th>ID</th>
                <th>Tipo Licencia</th>
                <th>Costo</th>
                <th>Fecha Compra</th>
                <th>Fecha Fin</th>
                <th>Vida Útil</th>
                <th>Acción</th>
            </tr>

             <div class="button-container">
                <button onclick="window.location.href = 'menuGestion.jsp'">Atrás</button>
            </div>
        </div>
            <c:forEach var="lic" items="${listaLicencias}">
                <tr>
                    <td>${lic.idLicencia}</td>
                    <td>${lic.tipoLicencia}</td>
                    <td>${lic.costo}</td>
                    <td>${lic.fechaCompra}</td>
                    <td>${lic.fechaFin}</td>
                    <td>${lic.vidaUtil}</td>
                    <td>
                        <!-- Enviamos el parámetro EXACTO que el servlet espera: idlicencia -->
                        <a href="eliminarLicenciaServlet?idlicencia=${lic.idLicencia}"
                           onclick="return confirm('¿Seguro que quieres eliminar esta licencia?');">
                            Eliminar
                        </a>
                    </td>
                </tr>
            </c:forEach>

           
    </table>

</body>
</html>