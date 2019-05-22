<%-- 
    Document   : stat
    Created on : 12-ene-2019, 19:59:41
    Author     : regigicas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Introducir nuevo stat</title>
    </head>
    <body>
        <h1>Datos nuevo stat</h1>
        <c:url var="urlPost" value="/api/stats"/>
        
        <form action="${urlPost}" method="POST" enctype="application/x-www-form-urlencoded">
            Nombre <input type="text" name="nombre" value="" required/>
            <br/>
            <input type="submit" value="Enviar"/>            
        </form>
            
        <c:url var="urlBack" value="/api/stats"/>
        <p><a href="${urlBack}">Volver a stats</a></p>
    </body>
</html>

