<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Resultados de Búsqueda</title>
</head>
<body>
    <h2>Resultados de Búsqueda</h2>

    <!-- Verificar si hay empleados para mostrar -->
    <c:if test="${not empty empleados}">
        <!-- Mostrar los resultados obtenidos -->
        <ul>
            <!-- Iterar sobre la lista de empleados -->
            <c:forEach var="empleado" items="${empleados}">
                <li>${empleado}</li>
            </c:forEach>
        </ul>
    </c:if>

    <!-- Mostrar un mensaje si no hay resultados -->
    <c:if test="${empty empleados}">
        <p>No se encontraron resultados para la búsqueda.</p>
    </c:if>
</body>
</html>
