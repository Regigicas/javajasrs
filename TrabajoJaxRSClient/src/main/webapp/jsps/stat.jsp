<%-- 
    Document   : stat
    Created on : 12-ene-2019, 18:47:39
    Author     : regigicas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Información de stat</title>
    </head>
    <body>
        <h2>Id: ${stat.codStat}</h2>
        <h2>Stat: ${stat.nombre}</h2>
        
        <c:url var="urlEdit" value="/api/stats/editar/${stat.codStat}"/>
        <p><a href="${urlEdit}">Editar</a></p>
        
        <c:url var="urlDelete" value="/api/stats/delete/${stat.codStat}"/>
        <p><a href="${urlDelete}">Borrar</a></p>
        
        <c:url var="urlBack" value="/api/stats"/>
        <p><a href="${urlBack}">Volver al listado de stats</a></p>
    </body>
</html>
