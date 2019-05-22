<%-- 
    Document   : categoria
    Created on : 12-ene-2019, 19:59:41
    Author     : regigicas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Introducir nueva categoria</title>
    </head>
    <body>
        <h1>Datos nueva categoria</h1>
        <c:url var="urlPost" value="/api/categorias"/>
        
        <form action="${urlPost}" method="POST" enctype="application/x-www-form-urlencoded">
            Descripción: <input type="text" name="descripcion" value="" required/>
            <br/>
            <input type="submit" value="Enviar"/>            
        </form>
    </body>
</html>

