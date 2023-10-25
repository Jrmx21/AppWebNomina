<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Resultados de Búsqueda</title>
</head>
<body>
	<h2>Resultados de Búsqueda</h2>
	<p>DEBUG: Número de empleados encontrados: ${empleados.size()}</p>
	<!-- Verifica si hay empleados para mostrar -->
	<c:if test="${not empty empleados}">
		<!-- Mostrar los resultados obtenidos -->
		<table border="1">
			<tr>
				<th>DNI</th>
				<th>Nombre</th>
				<th>Sexo</th>
				<th>Categoría</th>
				<th>Años</th>
				<!-- Agrega más columnas según sea necesario -->
			</tr>
			<c:forEach var="empleado" items="${empleados}">
				<tr>
					<td>${empleado.dni}</td>
					<td>${empleado.nombre}</td>
					<td>${empleado.sexo}</td>
					<td>${empleado.categoria}</td>
					<td>${empleado.anyos}</td>

				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>
