<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar empleados</title>
</head>
<body>
 <h1>Listar empleados</h1>
 <table border="1">
  <tr>
   <td>Dni</td>
   <td>Nombre</td>
   <td>Sexo</td>
   <td>Categoria</td>
   <td>Años</td>
   <td>Fecha Creacion</td>
   <td>Fecha Actualizacion</td>
   <td>Accion</td>
  </tr>
  <c:forEach var="empleado" items="${lista}">
  <tr>
    <td>
     <a href="empleados?opcion=meditar&id=<c:out value="${ empleado.dni}"></c:out>">
      <c:out value="${ empleado.dni}"></c:out>
     </a>
    </td>
    <td><c:out value="${ empleado.dni}"></c:out></td>
    <td><c:out value="${ empleado.nombre}"></c:out></td>
    <td><c:out value="${ empleado.sexo}"></c:out></td>
    <td><c:out value="${ empleado.categoria}"></c:out></td>
    <td><c:out value="${ empleado.anyos}"></c:out></td>
    <td>
     <a href="empleados?opcion=eliminar&dni=<c:out value="${ empleado.dni}"></c:out>">
      Eliminar 
     </a>
    </td>
  </tr>
  </c:forEach>
 </table>
</body>
</html>