<%-- 
    Document   : item
    Created on : 12-ene-2019, 19:59:41
    Author     : regigicas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Introducir nuevo item</title>
    </head>
    <body>
        <h1>Datos nuevo item</h1>
        <c:url var="urlPost" value="/api/items"/>
        
        <form action="${urlPost}" method="POST" enctype="application/x-www-form-urlencoded">
            Nombre: <input type="text" name="nombre" value="" required/>
            <br/>
            Descripción: <input type="text" name="descripcion" value="" required/>
            <br/>
            
            Categoría: <select name="codCategoria" required>
                <c:forEach var="cat" items="${categorias}">
                    <option value="${cat.codCategoria}">${cat.descripcion}</option>
                </c:forEach>
            </select>
            <br/>
            
            Stats:<br/>
            <c:forEach var="stat" items="${stats}">
                ${stat.nombre}: <input type="checkbox" name="stat_${stat.codStat}" value="${stat.codStat}">
                Valor:<input type="number" name="value_${stat.codStat}" value=""><br/>
            </c:forEach>
            
            <br/>
            <input type="submit" value="Enviar"/>
        </form>
            
        <c:url var="urlBack" value="/api/items"/>
        <p><a href="${urlBack}">Volver a items</a></p>
    </body>
</html>
