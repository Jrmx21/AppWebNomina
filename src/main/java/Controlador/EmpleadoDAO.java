package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmpleadoDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

//	public boolean guardar(Empleado empleado) throws SQLException {
//		String sql = null;
//		estadoOperacion = false;
//		connection = obtenerConexion();
//
//		try {
//			connection.setAutoCommit(false);
//			sql = "INSERT INTO productos (id, nombre, cantidad, precio, fecha_crear,fecha_actualizar) VALUES(?,?,?,?,?,?)";
//			statement = connection.prepareStatement(sql);
//
//			statement.setString(1, empleado.getNombre());
//			statement.setCharacterStream(2, empleado.getSexo());
//			statement.setInt(4, empleado.getAnyos());
//			statement.setString(5, empleado.getDni());
//
//			estadoOperacion = statement.executeUpdate() > 0;
//
//			connection.commit();
//			statement.close();
//			connection.close();
//		} catch (SQLException e) {
//			connection.rollback();
//			e.printStackTrace();
//		}
//
//		return estadoOperacion;
//	}

	// editar producto
//		 public boolean editar(Empleado empleado) throws SQLException {
//		  String sql = null;
//		  estadoOperacion = false;
//		  connection = obtenerConexion();
//		  try {
//		   connection.setAutoCommit(false);
//		   sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, sexo=?,fecha_actualizar=? WHERE dni=?";
//		   statement = connection.prepareStatement(sql);
//		 
//		   statement.setString(1, empleado.getNombre());
//		   statement.set(2, empleado.getSexo());
//		   statement.setInt(3, empleado.getCategoria());
//		   statement.setInt(4, empleado.getAnyos());
//		   statement.setString(5, empleado.getDni());
//		 
//		   estadoOperacion = statement.executeUpdate() > 0;
//		   connection.commit();
//		   statement.close();
//		   connection.close();
//		 
//		  } catch (SQLException e) {
//		   connection.rollback();
//		   e.printStackTrace();
//		  }
//		 
//		  return estadoOperacion;
//		 }
//		 
	// obtener lista de productos

	// obtener producto
	// obtener lista de productos
	public List<Empleado> obtenerEmpleados() throws SQLException {
		ResultSet resultSet = null;
		List<Empleado> listaEmpleados = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM empleados";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Empleado e = new Empleado(resultSet.getString(1), resultSet.getString(2),
						resultSet.getString(3).toCharArray()[0], resultSet.getInt(4), resultSet.getInt(5));

				listaEmpleados.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEmpleados;
	}

	// obtener conexion pool
	private Connection obtenerConexion() throws SQLException {
		return ConnectionDB.getConnection();
	}

}