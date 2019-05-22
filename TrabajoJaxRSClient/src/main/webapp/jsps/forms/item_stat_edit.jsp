<%-- 
    Document   : categoria_edit
    Created on : 12-ene-2019, 21:00:13
    Author     : regigicas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar datos de stat</title>
    </head>
    <body>
        <h1>Editar stat ${stat.nombre} de ${item.codItem} - ${item.nombre} </h1>
        <c:url var="urlPost" value="/api/items/${item.codItem}/stats/editar/${stat.codStat}"/>
        
        <form action="${urlPost}" method="POST" enctype="application/x-www-form-urlencoded">
            Nuevo valor: <input type="number" name="value" value="" required/>
            <br/>
            <input type="submit" value="Enviar"/>            
        </form>
            
        <c:url var="urlBack" value="/api/items/${item.codItem}"/>
        <p><a href="${urlBack}">Volver a item</a></p>
    </body>
</html>
