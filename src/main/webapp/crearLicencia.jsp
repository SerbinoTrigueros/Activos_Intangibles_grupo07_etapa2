<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Licencia de Software</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/crearLicencia.css?v=<%= System.currentTimeMillis()%>">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/themes/dark.css" />

    </head>
    <body>

        <div class="form-container">

            <h2>Licencia de Software</h2>

            <% if (request.getAttribute("mensaje") != null) {%>
            <div class="mensaje"><%= request.getAttribute("mensaje")%></div>
            <% }%>

            <form id="formLicencia" action="crearLicenciaServlet" method="post">

                <label for="tipo">Tipo de Licencia:</label>
                <select id="tipo" name="tipo">
                    <option <%= "Microsoft Office".equals(request.getAttribute("tipoValor")) ? "selected" : ""%>>Microsoft Office</option>
                    <option <%= "MySQL".equals(request.getAttribute("tipoValor")) ? "selected" : ""%>>MySQL</option>
                    <option <%= "SQL Server".equals(request.getAttribute("tipoValor")) ? "selected" : ""%>>SQL Server</option>
                    <option <%= "Windows".equals(request.getAttribute("tipoValor")) ? "selected" : ""%>>Windows</option>
                    <option <%= "SAP".equals(request.getAttribute("tipoValor")) ? "selected" : ""%>>SAP</option>
                    <option <%= "Visual Studio".equals(request.getAttribute("tipoValor")) ? "selected" : ""%>>Visual Studio</option>
                </select>

                <label for="costo">Costo:</label>
                <input type="number" id="costo" name="costo" step="0.01"
                       value="<%= request.getAttribute("costoValor") != null ? request.getAttribute("costoValor") : ""%>">

                <label for="fechaCompra">Fecha de Compra:</label>
                <input type="text" id="fechaCompra" name="fechaCompra" readonly
                       value="<%= request.getAttribute("fechaCompraValor") != null ? request.getAttribute("fechaCompraValor") : ""%>">

                <label for="vidaUtil">Vida Útil:</label>
                <input type="text" id="vidaUtil" name="vidaUtil"
                       value="<%= request.getAttribute("vidaUtilValor") != null ? request.getAttribute("vidaUtilValor") : ""%>">

                <label for="fechaFin">Fecha de Fin:</label>
                <input type="text" id="fechaFin" name="fechaFin" readonly
                       value="<%= request.getAttribute("fechaFinValor") != null ? request.getAttribute("fechaFinValor") : ""%>">

                <div class="btn-container">
                    <button type="submit">Guardar</button>
                    <button type="button" onclick="window.location.href = 'crearLicencia.jsp'">Cancelar</button>
                    <button type="button" onclick="window.location.href = 'menuGestion.jsp'">Atrás</button>
                </div>

            </form>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
        <script>
                        flatpickr("#fechaCompra", {dateFormat: "Y-m-d"});
                        flatpickr("#fechaFin", {dateFormat: "Y-m-d"});
        </script>

    </body>
</html>
