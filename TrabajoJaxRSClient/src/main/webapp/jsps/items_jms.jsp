<%-- 
    Document   : items_jms
    Created on : 14-ene-2019, 18:59:34
    Author     : regigicas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Obtener información item JMS</title>
    </head>
    <body>
        <h1>Obtener información de item mediante JMS</h1>
        
        <c:url var="urlPost" value="/api/items/jms"/>
        <form action="${urlPost}" method="POST" enctype="application/x-www-form-urlencoded">
            <select name="codItem" required>
                <c:forEach var="item" items="${items}">
                    <option value="${item.codItem}">${item.codItem} - ${item.nombre}</option>
                </c:forEach>
            </select>
            <br/>
            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>
