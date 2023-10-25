package Controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Administra peticiones para la tabla", urlPatterns = "/empleados")
public class EmpleadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final char E = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String opcion = request.getParameter("opcion");

		if (opcion.equals("listar")) {
			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			List<Empleado> lista = new ArrayList<>();
			try {
				lista = empleadoDAO.obtenerEmpleados();
				for (Empleado empleado : lista) {
					System.out.println(empleado);
				}
				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error al obtener empleados: " + e.getMessage());
			}

			System.out.println("Usted a presionado la opcion listar");
		}
//		else if (opcion.equals("meditar")) {
//			int id = Integer.parseInt(request.getParameter("id"));
//			System.out.println("Editar id: " + id);
//			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
//			Empleado e = new Empleado("", "", E);
//			try {
//				e = (Empleado) empleadoDAO.obtenerEmpleados();
//				System.out.println(e);
//				request.setAttribute("employees", e);
//				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
//				requestDispatcher.forward(request, response);
//
//			} catch (SQLException error) {
//				// TODO Auto-generated catch block
//				error.printStackTrace();
//			}
//
//		} else if (opcion.equals("eliminar")) {
//			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
//			int id = Integer.parseInt(request.getParameter("id"));
//			// empleadoDAO.eliminar(id);
//			System.out.println("Registro eliminado satisfactoriamente...");
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
//			requestDispatcher.forward(request, response);
//
//		}
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private double obtenerSalarioDesdeBD(String dni) {
		double salario = 0.0;

		// Configura tu conexión a la base de datos aquí usando ConnectionDB
		try (Connection connection = ConnectionDB.getConnection()) {
			// Consulta SQL para obtener el sueldo asociado al DNI de la tabla empleados
			String sql = "SELECT n.sueldo FROM nominas n INNER JOIN empleados e ON n.dni_empleado = e.dni WHERE e.dni = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, dni);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						salario = resultSet.getDouble("sueldo");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Manejo de errores: ¡Considera un manejo más robusto en un entorno de
									// producción!
		}

		return salario;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recuperar el DNI del formulario
		String dni = request.getParameter("dni");

		// Llamar a un método para obtener las nominas desde la base de datos
		List<Double> nominas = obtenerNominasDesdeBD(dni);

		// Establecer las nominas como un atributo para la página JSP
		request.setAttribute("nominas", nominas);

		// Redirigir a la página JSP de visualización
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mostrarSalario.jsp");
		dispatcher.forward(request, response);
	}

	private List<Double> obtenerNominasDesdeBD(String dni) {
		// Llamar al método de EmpleadoDAO para obtener las nominas
		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		return empleadoDAO.obtenerNominasPorDNI(dni);
	}
}
