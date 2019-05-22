<%-- 
    Document   : categorias
    Created on : 12-ene-2019, 18:35:46
    Author     : regigicas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de categorias</title>
    </head>
    <body>
        <h1>Listado de categorias de items</h1>
        
        <c:if test="${not empty categorias}">
            <ul>
                <c:forEach var="categoria" items="${categorias}">
                    <c:url var="urlCategoria" value="/api/categorias/${categoria.codCategoria}"/>
                    <li><a href="${urlCategoria}">ID: ${categoria.codCategoria} - ${categoria.descripcion}</a></li>
                </c:forEach>
            </ul>
        </c:if>
        
        <c:url var="urlInsertar" value="/api/categorias/insert"/>
        <p><a href="${urlInsertar}">Crear nueva categoria</a></p>
        
        <c:url var="urlIndex" value="/"/>
        <p><a href="${urlIndex}">Index</a></p>
    </body>
</html>
