<%-- 
    Document   : stats
    Created on : 12-ene-2019, 18:35:46
    Author     : regigicas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de stats</title>
    </head>
    <body>
        <h1>Listado de stats disponibles</h1>
        
        <c:if test="${not empty stats}">
            <ul>
                <c:forEach var="stat" items="${stats}">
                    <c:url var="urlStat" value="/api/stats/${stat.codStat}"/>
                    <li><a href="${urlStat}">ID: ${stat.codStat} - ${stat.nombre}</a></li>
                </c:forEach>
            </ul>
        </c:if>
        
        <c:url var="urlInsertar" value="/api/stats/insert"/>
        <p><a href="${urlInsertar}">Crear nuevo stat</a></p>
        
        <c:url var="urlIndex" value="/"/>
        <p><a href="${urlIndex}">Index</a></p>
    </body>
</html>
