<%-- 
    Document   : item
    Created on : 12-ene-2019, 18:47:39
    Author     : regigicas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Información de item</title>
    </head>
    <body>
        <h2>Id: ${item.codItem}</h2>
        <h2>Nombre: ${item.nombre}</h2>
        <h3>Descripción: ${item.descripcion}</h3>
        <h3>Categoría: ${item.categoria.codCategoria} - ${item.categoria.descripcion}</h3>
        
        <c:if test="${not empty item.stats}">
            <p>Listado de stats:</p>
            <ul>
                <c:forEach var="stat" items="${item.stats}">
                    <c:url var="urlEditStat" value="/api/items/${item.codItem}/stats/editar/${stat.codStat}"/>
                    <c:url var="urlDelStat" value="/api/items/${item.codItem}/stats/delete/${stat.codStat}"/>
                    <li>${stat.nombre}, Cantidad: ${stat.value} <a href="${urlEditStat}">[Editar]</a><a href="${urlDelStat}">[Eliminar]</a></li>
                </c:forEach>
            </ul>
        </c:if>

        <c:url var="urlInsertStat" value="/api/items/${item.codItem}/stats/insert"/>
        <p><a href="${urlInsertStat}">Añadir stat</a></p>
        
        <c:url var="urlEdit" value="/api/items/editar/${item.codItem}"/>
        <p><a href="${urlEdit}">Editar</a></p>
        
        <c:url var="urlDelete" value="/api/items/delete/${item.codItem}"/>
        <p><a href="${urlDelete}">Borrar</a></p>
        
        <c:url var="urlBack" value="/api/items"/>
        <p><a href="${urlBack}">Volver al listado de items</a></p>
    </body>
</html>
