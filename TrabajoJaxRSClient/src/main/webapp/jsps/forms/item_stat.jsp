<%-- 
    Document   : item_stat
    Created on : 12-ene-2019, 19:59:41
    Author     : regigicas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Introducir nuevo stat a item</title>
    </head>
    <body>
        <h1>Datos del nuevo stat para ${item.codItem} - ${item.nombre}</h1>
        <c:url var="urlPost" value="/api/items/${item.codItem}/stats/insert"/>
        
        <form action="${urlPost}" method="POST" enctype="application/x-www-form-urlencoded">
            Stat: <select name="codStat" required>
                <c:forEach var="stat" items="${stats}">
                    <option value="${stat.codStat}">${stat.nombre}</option>
                </c:forEach>
            </select>
            <br/>
            Valor: <input type="number" name="value" value="" required/>
            <br/>
            <input type="submit" value="Enviar"/>            
        </form>
            
        <c:url var="urlBack" value="/api/items/${item.codItem}"/>
        <p><a href="${urlBack}">Volver a item</a></p>
    </body>
</html>

