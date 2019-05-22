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
        <title>Editar item</title>
    </head>
    <body>
        <h1>Editar datos de item</h1>
        <c:url var="urlPost" value="/api/items/editar/${item.codItem}"/>
        
        <form action="${urlPost}" method="POST" enctype="application/x-www-form-urlencoded">
            Nombre: <input type="text" name="nombre" value="${item.nombre}" required/>
            <br/>
            Descripción: <input type="text" name="descripcion" value="${item.descripcion}" required/>
            <br/>
            
            Categoría: <select name="codCategoria" required>
                <c:forEach var="cat" items="${categorias}">
                    <c:choose>
                        <c:when test="${cat.codCategoria == item.categoria.codCategoria}">
                            <option value="${cat.codCategoria}" selected>${cat.descripcion}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${cat.codCategoria}">${cat.descripcion}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <br/>
            <input type="submit" value="Enviar"/>            
        </form>
            
        <c:url var="urlBack" value="/api/items/${item.codItem}"/>
        <p><a href="${urlBack}">Volver a item</a></p>
    </body>
</html>
