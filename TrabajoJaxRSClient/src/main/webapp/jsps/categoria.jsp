<%-- 
    Document   : categoria
    Created on : 12-ene-2019, 18:47:39
    Author     : regigicas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Información de categoria</title>
    </head>
    <body>
        <h2>Id: ${categoria.codCategoria}</h2>
        <h2>Categoria: ${categoria.descripcion}</h2>
        
        <c:url var="urlEdit" value="/api/categorias/editar/${categoria.codCategoria}"/>
        <p><a href="${urlEdit}">Editar</a></p>
        
        <c:url var="urlDelete" value="/api/categorias/delete/${categoria.codCategoria}"/>
        <p><a href="${urlDelete}">Borrar</a></p>
        
        <c:url var="urlBack" value="/api/categorias"/>
        <p><a href="${urlBack}">Volver a categorias</a></p>
    </body>
</html>
