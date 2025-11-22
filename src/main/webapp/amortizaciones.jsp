<%-- 
    Document   : index
    Created on : 12 nov 2025, 06:20:42
    Author     : Dell
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
            :root {
                --neon-cyan: #00ffff;
                --neon-blue: #0077ff;
                --neon-purple: #ff00ff;
                --neon-green: #00ff00;
                --neon-orange: #ff5500;
                --dark-bg: #000000;
                --darker-bg: #111111;
                --card-bg: #1a1a1a;
                --text-glow: 0 0 10px currentColor;
            }
            
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            
            body {
                background-color: var(--dark-bg);
                color: var(--neon-cyan);
                font-family: 'Poppins', sans-serif;
                margin: 25px;
                font-size: 18px;
                min-height: 100vh;
            }
            
            .container {
                max-width: 1200px;
                margin: 0 auto;
                background: var(--darker-bg);
                border-radius: 15px;
                box-shadow: 0 0 25px var(--neon-cyan);
                overflow: hidden;
                border: 1px solid var(--neon-cyan);
            }
            
            .header {
                background: linear-gradient(135deg, #001122 0%, #003344 100%);
                color: var(--neon-cyan);
                padding: 30px 40px;
                text-align: center;
                text-shadow: var(--text-glow);
                border-bottom: 2px solid var(--neon-cyan);
            }
            
            .header h2 {
                font-size: 2rem;
                font-weight: 600;
                margin-bottom: 8px;
                text-shadow: 0 0 15px var(--neon-cyan);
            }
            
            .header-subtitle {
                font-size: 1rem;
                opacity: 0.9;
                text-shadow: 0 0 10px var(--neon-cyan);
            }
            
            .content {
                padding: 40px;
            }
            
            .mensaje {
                padding: 16px 20px;
                margin-bottom: 25px;
                border-radius: 8px;
                font-weight: 500;
                border-left: 4px solid;
                text-shadow: var(--text-glow);
            }
            
            .error {
                background-color: #330000;
                color: var(--neon-orange);
                border-left-color: var(--neon-orange);
                box-shadow: 0 0 15px var(--neon-orange);
            }
            
            .exito {
                background-color: #003300;
                color: var(--neon-green);
                border-left-color: var(--neon-green);
                box-shadow: 0 0 15px var(--neon-green);
            }
            
            .form-container {
                background: var(--card-bg);
                padding: 25px;
                border-radius: 12px;
                border: 1px solid var(--neon-cyan);
                box-shadow: 0 0 20px var(--neon-cyan);
                margin-bottom: 30px;
            }
            
            .form-group {
                display: flex;
                gap: 20px;
                align-items: end;
                flex-wrap: wrap;
            }
            
            .form-field {
                display: flex;
                flex-direction: column;
                flex: 1;
                min-width: 150px;
            }
            
            label {
                font-weight: 600;
                color: var(--neon-cyan);
                margin-bottom: 8px;
                font-size: 0.9rem;
                text-shadow: var(--text-glow);
            }
            
            input[type="text"], select {
                padding: 12px;
                border: 1px solid var(--neon-cyan);
                border-radius: 8px;
                font-size: 1rem;
                transition: all 0.3s ease;
                background: var(--dark-bg);
                color: var(--neon-cyan);
                box-shadow: 0 0 10px var(--neon-cyan);
            }
            
            input[type="text"]:focus, select:focus {
                outline: none;
                border-color: var(--neon-purple);
                box-shadow: 0 0 20px var(--neon-purple);
            }
            
            .btn {
                background-color: var(--dark-bg);
                border: 2px solid var(--neon-cyan);
                color: var(--neon-cyan);
                padding: 12px 24px;
                border-radius: 10px;
                box-shadow: 0 0 15px var(--neon-cyan);
                cursor: pointer;
                font-weight: bold;
                transition: 0.3s ease;
                font-size: 18px;
                text-decoration: none;
                display: inline-flex;
                align-items: center;
                justify-content: center;
                height: fit-content;
                text-shadow: var(--text-glow);
            }
            
            .btn:hover {
                background-color: var(--neon-cyan);
                color: var(--dark-bg);
                box-shadow: 0 0 30px var(--neon-cyan);
                transform: translateY(-2px);
            }
            
            .btn-success {
                border-color: var(--neon-green);
                box-shadow: 0 0 15px var(--neon-green);
                color: var(--neon-green);
                padding: 8px 16px;
                font-size: 0.85rem;
            }
            
            .btn-success:hover {
                background-color: var(--neon-green);
                color: var(--dark-bg);
                box-shadow: 0 0 30px var(--neon-green);
            }
            
            .btn-back {
                border-color: var(--neon-purple);
                box-shadow: 0 0 15px var(--neon-purple);
                color: var(--neon-purple);
                margin-top: 20px;
            }
            
            .btn-back:hover {
                background-color: var(--neon-purple);
                color: var(--dark-bg);
                box-shadow: 0 0 30px var(--neon-purple);
            }
            
            .section-title {
                font-size: 1.5rem;
                color: var(--neon-cyan);
                margin-bottom: 20px;
                padding-bottom: 10px;
                border-bottom: 2px solid var(--neon-cyan);
                text-shadow: var(--text-glow);
            }
            
            .table-container {
                overflow-x: auto;
                border-radius: 12px;
                box-shadow: 0 0 25px var(--neon-cyan);
                background: var(--darker-bg);
                border: 1px solid var(--neon-cyan);
                margin-bottom: 20px;
            }
            
            table {
                width: 100%;
                border-collapse: collapse;
                background: var(--darker-bg);
                font-size: 18px;
            }
            
            th, td {
                border: 1px solid var(--neon-cyan);
                padding: 14px 12px;
                text-align: left;
            }
            
            th {
                background-color: #222222;
                color: var(--neon-cyan);
                text-shadow: var(--text-glow);
                font-size: 19px;
                font-weight: 600;
            }
            
            tr:nth-child(even) {
                background-color: var(--card-bg);
            }
            
            tr:hover {
                background-color: #003333;
                cursor: pointer;
            }
            
            .monto-cell {
                font-weight: 600;
                color: var(--neon-purple);
                text-shadow: 0 0 10px var(--neon-purple);
            }
            
            .status-badge {
                padding: 6px 12px;
                border-radius: 20px;
                font-size: 0.85rem;
                font-weight: 600;
                text-transform: uppercase;
                text-shadow: var(--text-glow);
            }
            
            .pendiente {
                background: #332200;
                color: var(--neon-orange);
                border: 1px solid var(--neon-orange);
                box-shadow: 0 0 10px var(--neon-orange);
            }
            
            .pagada {
                background: #003300;
                color: var(--neon-green);
                border: 1px solid var(--neon-green);
                box-shadow: 0 0 10px var(--neon-green);
            }
            
            .action-cell {
                text-align: center;
            }
            
            .empty-state {
                text-align: center;
                padding: 60px 20px;
                color: var(--neon-cyan);
                text-shadow: var(--text-glow);
            }
            
            .empty-state p {
                font-size: 1.1rem;
                margin-bottom: 15px;
            }
            
            @media (max-width: 768px) {
                body {
                    margin: 15px;
                    font-size: 16px;
                }
                
                .content {
                    padding: 20px;
                }
                
                .form-group {
                    flex-direction: column;
                    gap: 15px;
                }
                
                .form-field {
                    width: 100%;
                }
                
                .btn {
                    width: 100%;
                }
                
                th, td {
                    padding: 10px 8px;
                    font-size: 16px;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <h2>Administración de Amortizaciones</h2>
                <div class="header-subtitle">SoftControl - Gestión de Activos Intangibles</div>
            </div>
            
            <div class="content">
                <%-- Mostrar mensajes del Servlet --%>
                <c:if test="${not empty mensaje}">
                    <div class="mensaje ${mensaje.toLowerCase().contains('error') ? 'error' : 'exito'}">
                        ${mensaje}
                    </div>
                </c:if>

                <div class="form-container">
                    <h3 class="section-title">Filtrar Amortizaciones</h3>
                    <form action="AmortizacionServlet" method="post">
                        <input type="hidden" name="accion" value="mostrar">
                        
                        <div class="form-group">
                            <div class="form-field">
                                <label for="idLicencia">ID Licencia:</label>
                                <input type="text" id="idLicencia" name="idLicencia" value="${idLicenciaActual}" required placeholder="Ej: L001">
                            </div>
                            
                            <div class="form-field">
                                <label for="cbTipo">Tipo de Reporte:</label>
                                <select id="cbTipo" name="cbTipo">
                                    <option value="mensual" ${tipoActual eq 'mensual' ? 'selected' : ''}>Mensual</option>
                                    <option value="anual" ${tipoActual eq 'anual' ? 'selected' : ''}>Anual</option>
                                    <option value="acumulado" ${tipoActual eq 'acumulado' ? 'selected' : ''}>Acumulado</option>
                                </select>
                            </div>
                            
                            <div class="form-field">
                                <label for="cbEstado">Estado:</label>
                                <select id="cbEstado" name="cbEstado">
                                    <option value="todos" ${estadoActual eq 'todos' ? 'selected' : ''}>Todos</option>
                                    <option value="pendiente" ${estadoActual eq 'pendiente' ? 'selected' : ''}>Pendiente</option>
                                    <option value="pagada" ${estadoActual eq 'pagada' ? 'selected' : ''}>Pagada</option>
                                </select>
                            </div>
                            
                            <div class="form-field">
                                <button type="submit" class="btn">
                                    📊 Mostrar Amortizaciones
                                </button>
                            </div>
                        </div>
                    </form>
                </div>

                <%-- Tabla de Amortizaciones --%>
                <c:if test="${not empty listaAmortizaciones}">
                    <h3 class="section-title">
                        Resultados de Amortización
                        <c:if test="${not empty tipoActual}">
                            <small style="font-size: 1rem; color: var(--neon-cyan);">- Tipo: ${tipoActual}</small>
                        </c:if>
                    </h3>
                    
                    <div class="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <c:choose>
                                        <c:when test="${tipoActual eq 'acumulado'}">
                                            <th>ID Licencia</th>
                                            <th>Tipo</th>
                                            <th>Monto Total</th>
                                            <th>Estado</th>
                                        </c:when>
                                        <c:otherwise>
                                            <th>ID</th>
                                            <th>Tipo</th>
                                            <th>Monto</th>
                                            <th>Fecha</th>
                                            <th>Estado</th>
                                            <th class="action-cell">Acción</th>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="a" items="${listaAmortizaciones}">
                                    <tr>
                                        <c:choose>
                                            <c:when test="${tipoActual eq 'acumulado'}">
                                                <td><strong>${a.idLicencia}</strong></td>
                                                <td>${a.tipoCartera}</td>
                                                <td class="monto-cell">
                                                    <fmt:formatNumber value="${a.monto}" type="currency" currencySymbol="$" maxFractionDigits="2"/>
                                                </td>
                                                <td>
                                                    <span class="status-badge pagada">Consolidado</span>
                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><strong>${a.idAmortizacion}</strong></td>
                                                <td>${a.tipoCartera}</td>
                                                <td class="monto-cell">
                                                    <fmt:formatNumber value="${a.monto}" type="currency" currencySymbol="$" maxFractionDigits="2"/>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value="${a.fechaRegistro}" pattern="dd/MM/yyyy"/>
                                                </td>
                                                <td>
                                                    <span class="status-badge ${a.estado eq 'pendiente' ? 'pendiente' : 'pagada'}">
                                                        ${a.estado}
                                                    </span>
                                                </td>
                                                <td class="action-cell">
                                                    <c:if test="${a.estado eq 'pendiente'}">
                                                        <form action="AmortizacionServlet" method="post" style="display:inline;">
                                                            <input type="hidden" name="accion" value="actualizarEstado">
                                                            <input type="hidden" name="idAmortizacion" value="${a.idAmortizacion}">
                                                            <input type="hidden" name="idLicenciaRetorno" value="${idLicenciaActual}">
                                                            <input type="hidden" name="tipoRetorno" value="${tipoActual}">
                                                            <input type="hidden" name="estadoRetorno" value="${estadoActual}">
                                                            <button type="submit" class="btn btn-success" onclick="return confirm('¿Estás seguro de marcar esta amortización como PAGADA?');">
                                                                💳 Pagar
                                                            </button>
                                                        </form>
                                                    </c:if>
                                                </td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>

                <%-- Botón de regreso --%>
                <div style="text-align: center; margin-top: 30px;">
                    <a href="ContabilidadServlet" class="btn btn-back">
                        ← Volver a Administración Principal
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>