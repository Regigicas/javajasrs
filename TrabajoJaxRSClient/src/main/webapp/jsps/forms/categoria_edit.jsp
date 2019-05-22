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
        <title>Actualizar datos de categoria</title>
    </head>
    <body>
        <h1>Editar descripción de la categoria: ${categoria.codCategoria} - ${categoria.descripcion} </h1>
        <c:url var="urlPost" value="/api/categorias/editar/${categoria.codCategoria}"/>
        
        <form action="${urlPost}" method="POST" enctype="application/x-www-form-urlencoded">
            Nueva Descripción: <input type="text" name="descripcion" value="" required/>
            <br/>
            <input type="submit" value="Enviar"/>            
        </form>
            
        <c:url var="urlBack" value="/api/categorias/${categoria.codCategoria}"/>
        <p><a href="${urlBack}">Volver a categoria</a></p>
    </body>
</html>
