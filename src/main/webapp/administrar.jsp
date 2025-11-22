<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    if (request.getAttribute("listaLicencias") == null) {
        try {
            controlador.ContabilidadDAO daoTemp = new controlador.ContabilidadDAO();
            java.util.List<modelo.Licencia> data = daoTemp.listarLicencias();
            request.setAttribute("listaLicencias", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Administración de Información Contable</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/administrar.css?v=<%= System.currentTimeMillis()%>">

    </head>
    <body>

        <h1 class="titulo">Administración de Información Contable</h1>

        <div class="button-container">
            <a href="<c:url value='/ContabilidadServlet'/>">
                <button>Refrescar Licencias</button>
            </a>
            <a href="<c:url value='/AmortizacionServlet'/>">
                <button>Mostrar Amortizaciones</button>
            </a>
            <a href="<c:url value='/ValorLibrosServlet'/>">
                <button>Consultar Valores en Libros</button>
            </a>

            <a href="menuLicencia.jsp">
                <button>Regresar</button>
            </a>
        </div>

        <h2 class="titulo">Licencias Registradas</h2>

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
                            <th>Vida Útil</th>
                            <th>ID Usuario</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="lic" items="${listaLicencias}">
                            <tr>
                                <td>${lic.idLicencia}</td>
                                <td>${lic.tipoLicencia}</td>
                                <td>
                                    <fmt:formatNumber value="${lic.costo}" type="currency" currencySymbol="$" maxFractionDigits="2"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${lic.fechaCompra}" pattern="dd/MM/yyyy"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${lic.fechaFin}" pattern="dd/MM/yyyy"/>
                                </td>
                                <td>${lic.vidaUtil}</td>
                                <td>${lic.idUsuario}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>

            <c:otherwise>
                <p>No hay licencias registradas.</p>
            </c:otherwise>
        </c:choose>

    </body>
</html>
