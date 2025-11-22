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
        <title>Consultar Valor en Libros - SoftControl</title>
        <style>
            :root {
                --neon-cyan: #00ffff;
                --neon-blue: #0077ff;
                --neon-purple: #ff00ff;
                --neon-green: #00ff00;
                --neon-orange: #ff5500;
                --neon-red: #ff0000;
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
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            
            .container {
                max-width: 600px;
                width: 100%;
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
                font-size: 1.8rem;
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
                text-align: center;
                text-shadow: var(--text-glow);
            }
            
            .error {
                background-color: #330000;
                color: var(--neon-red);
                border-left-color: var(--neon-red);
                box-shadow: 0 0 15px var(--neon-red);
            }
            
            .aviso {
                background-color: #332200;
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
                align-items: center;
                justify-content: space-between;
                margin-bottom: 20px;
            }
            
            label {
                font-weight: 600;
                color: var(--neon-cyan);
                font-size: 1rem;
                width: 45%;
                text-shadow: var(--text-glow);
            }
            
            input[type="text"] {
                width: 50%;
                padding: 12px;
                border: 1px solid var(--neon-cyan);
                border-radius: 8px;
                font-size: 1rem;
                transition: all 0.3s ease;
                background: var(--dark-bg);
                color: var(--neon-cyan);
                box-shadow: 0 0 10px var(--neon-cyan);
            }
            
            input[type="text"]:focus {
                outline: none;
                border-color: var(--neon-purple);
                box-shadow: 0 0 20px var(--neon-purple);
            }
            
            input[readonly] {
                background-color: var(--card-bg);
                color: var(--neon-cyan);
                font-weight: 500;
                text-align: right;
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
                width: 100%;
                text-shadow: var(--text-glow);
            }
            
            .btn:hover {
                background-color: var(--neon-cyan);
                color: var(--dark-bg);
                box-shadow: 0 0 30px var(--neon-cyan);
                transform: translateY(-2px);
            }
            
            .btn-back {
                border-color: var(--neon-purple);
                box-shadow: 0 0 15px var(--neon-purple);
                color: var(--neon-purple);
                margin-top: 20px;
                width: auto;
                padding: 10px 20px;
            }
            
            .btn-back:hover {
                background-color: var(--neon-purple);
                color: var(--dark-bg);
                box-shadow: 0 0 30px var(--neon-purple);
            }
            
            .results-section {
                margin-top: 30px;
            }
            
            .section-title {
                font-size: 1.3rem;
                color: var(--neon-cyan);
                margin-bottom: 20px;
                padding-bottom: 10px;
                border-bottom: 2px solid var(--neon-cyan);
                text-align: center;
                text-shadow: var(--text-glow);
            }
            
            .result-item {
                background: var(--card-bg);
                padding: 15px 20px;
                border-radius: 8px;
                margin-bottom: 15px;
                border-left: 4px solid var(--neon-cyan);
                box-shadow: 0 0 10px var(--neon-cyan);
            }
            
            .result-label {
                font-weight: 600;
                color: var(--neon-cyan);
                margin-bottom: 5px;
                text-shadow: var(--text-glow);
            }
            
            .result-value {
                font-size: 1.1rem;
                color: var(--neon-cyan);
                text-align: right;
                text-shadow: var(--text-glow);
            }
            
            .result-value-positive {
                color: var(--neon-green);
                font-weight: 600;
                text-shadow: 0 0 10px var(--neon-green);
            }
            
            .result-value-negative {
                color: var(--neon-red);
                font-weight: 600;
                text-shadow: 0 0 10px var(--neon-red);
            }
            
            .valor-libros-final {
                background: linear-gradient(135deg, #001122 0%, #003344 100%);
                border-left: 4px solid var(--neon-cyan);
                padding: 20px;
                border-radius: 8px;
                margin-top: 20px;
                box-shadow: 0 0 20px var(--neon-cyan);
            }
            
            .valor-libros-label {
                font-size: 1.1rem;
                font-weight: 700;
                color: var(--neon-cyan);
                margin-bottom: 10px;
                text-shadow: var(--text-glow);
                text-align: center;
            }
            
            .valor-libros-value {
                font-size: 1.8rem;
                font-weight: 700;
                text-align: center;
                padding: 10px;
                text-shadow: var(--text-glow);
            }
            
            .warning-box {
                background: #332200;
                border: 1px solid var(--neon-orange);
                border-left: 4px solid var(--neon-orange);
                padding: 20px;
                border-radius: 8px;
                margin-top: 20px;
                box-shadow: 0 0 15px var(--neon-orange);
            }
            
            .warning-title {
                color: var(--neon-orange);
                font-weight: 600;
                margin-bottom: 8px;
                display: flex;
                align-items: center;
                gap: 8px;
                text-shadow: var(--text-glow);
            }
            
            hr {
                border: none;
                height: 1px;
                background: linear-gradient(90deg, transparent, var(--neon-cyan), transparent);
                margin: 20px 0;
                box-shadow: 0 0 10px var(--neon-cyan);
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
                    align-items: stretch;
                    gap: 10px;
                }
                
                label, input[type="text"] {
                    width: 100%;
                }
                
                .btn {
                    width: 100%;
                }
                
                .valor-libros-value {
                    font-size: 1.5rem;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <h2>🔎 Consultar Valor en Libros</h2>
                <div class="header-subtitle">SoftControl - Análisis Contable</div>
            </div>
            
            <div class="content">
                <%-- Mostrar mensajes del Servlet --%>
                <c:if test="${not empty mensaje}">
                    <div class="mensaje 
                        <c:choose>
                            <c:when test="${mensaje.toLowerCase().contains('error') || mensaje.toLowerCase().contains('no existe')}">error</c:when>
                            <c:when test="${mensaje.toLowerCase().contains('atención') || mensaje.toLowerCase().contains('aviso')}">aviso</c:when>
                            <c:otherwise>exito</c:otherwise>
                        </c:choose>">
                        ${mensaje}
                    </div>
                </c:if>

                <div class="form-container">
                    <h3 class="section-title">Consulta Específica</h3>
                    <form action="ValorLibrosServlet" method="post">
                        <div class="form-group">
                            <label for="idLicencia">ID Licencia:</label>
                            <input type="text" id="idLicencia" name="idLicencia" value="${idLicenciaActual}" required placeholder="Ej: L001">
                        </div>
                        <button type="submit" class="btn">
                            📊 Calcular Valor en Libros
                        </button>
                    </form>
                </div>

                <c:if test="${not empty resultadoVL}">
                    <div class="results-section">
                        <hr>
                        <h3 class="section-title">
                            Resultados para Licencia: 
                            <span style="color: var(--neon-purple); text-shadow: 0 0 10px var(--neon-purple);">${resultadoVL.idLicencia}</span>
                        </h3>
                        
                        <fmt:setLocale value="es_ES"/> 
                        
                        <div class="result-item">
                            <div class="result-label">Costo de Adquisición:</div>
                            <div class="result-value">
                                <fmt:formatNumber value="${resultadoVL.costoAdquisicion}" type="currency" currencySymbol="$"/>
                            </div>
                        </div>
                        
                        <div class="result-item">
                            <div class="result-label">Amortizaciones Acumuladas:</div>
                            <div class="result-value">
                                <fmt:formatNumber value="${resultadoVL.amortizacionesAcumuladas}" type="currency" currencySymbol="$"/>
                            </div>
                        </div>
                        
                        <div class="valor-libros-final">
                            <div class="valor-libros-label">VALOR EN LIBROS:</div>
                            <div class="valor-libros-value 
                                <c:choose>
                                    <c:when test="${resultadoVL.valorEnLibros > 0}">result-value-positive</c:when>
                                    <c:otherwise>result-value-negative</c:otherwise>
                                </c:choose>">
                                <fmt:formatNumber value="${Math.abs(resultadoVL.valorEnLibros)}" type="currency" currencySymbol="$"/>
                            </div>
                        </div>

                        <%-- Alerta para valor negativo --%>
                        <c:if test="${resultadoVL.valorEnLibros <= 0}">
                            <div class="warning-box">
                                <div class="warning-title">
                                    ⚠️ ATENCIÓN - EXCEDENTE DE AMORTIZACIÓN
                                </div>
                                <p style="color: var(--neon-orange); text-shadow: var(--text-glow);">
                                    El Valor en Libros es negativo. Esto se considera un Excedente de Amortización y debe cargarse en el libro mayor contable.
                                </p>
                            </div>
                        </c:if>
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