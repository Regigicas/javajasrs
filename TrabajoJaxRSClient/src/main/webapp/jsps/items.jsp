<%-- 
    Document   : items
    Created on : 12-ene-2019, 18:35:46
    Author     : regigicas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de items</title>
    </head>
    <body>
        <h1>Listado de items disponibles</h1>
        
        <c:if test="${not empty items}">
            <ul>
                <c:forEach var="item" items="${items}">
                    <c:url var="urlItem" value="/api/items/${item.codItem}"/>
                    <li><a href="${urlItem}">ID: ${item.codItem} - ${item.nombre}</a></li>
                </c:forEach>
            </ul>
        </c:if>
        
        <c:url var="urlInsertar" value="/api/items/insert"/>
        <p><a href="${urlInsertar}">Crear nuevo item</a></p>
        
        <c:url var="urlIndex" value="/"/>
        <p><a href="${urlIndex}">Index</a></p>
    </body>
</html>
