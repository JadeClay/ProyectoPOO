package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
	private String connectionUrl;
	
	public Database() {
		this.connectionUrl = "jdbc:sqlserver://rafayoscar.database.windows.net:1433;"
				+ "database=gestionsoftware;"
				+ "user=system@rafayoscar;"
				+ "password=:cgiNR7cvP.N9.m;"
				+ "encrypt=true;"
				+ "trustServerCertificate=false;"
				+ "hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	}
	
	// Methods to administer users
	public Usuario logUser(String username, String password) {
		Usuario result = null;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
	    	Statement statement = conn.createStatement();
	    	ResultSet rs = statement.executeQuery("SELECT * FROM Usuario WHERE nombre = '" + username + "' AND contraseña = '" + password + "'");
	    	
	    	if(rs.next()) {
	    		result = new Usuario("U-" + rs.getInt(1), username, password, rs.getInt(4));
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Usuario> getAllUsers(){
		ArrayList<Usuario> result = new ArrayList<Usuario>();
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Usuario");
			
			while(rs.next()) {
				Usuario usuario = new Usuario("U-" + rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				result.add(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public boolean registerUser(String username, String password, int type) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			int rowsModified = statement.executeUpdate("INSERT INTO Usuario (nombre,contraseña,tipo) VALUES ('"+ username+ "','" + password + "'," + type +")");
		
			if(rowsModified > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean deleteUser(int id) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			int rowsModified = statement.executeUpdate("DELETE FROM Usuario WHERE id=" + id);
		
			if(rowsModified > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// Methods to administer clients
	public ArrayList<Cliente> getAllClients(){
		ArrayList<Cliente> result = new ArrayList<Cliente>();
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Cliente");
			
			while(rs.next()) {
				Cliente cliente = new Cliente("CL-" + rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				result.add(cliente);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean addClient(String cedula, String nombre, String direccion) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			int rowsModified = statement.executeUpdate("INSERT INTO Cliente (cedula,nombre,direccion) VALUES ('"+ cedula + "','" + nombre + "','" + direccion +"')");
			if(rowsModified > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean deleteClient(int id) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			int rowsModified = statement.executeUpdate("DELETE FROM Cliente WHERE id=" + id);
		
			if(rowsModified > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean updateClient(int id, String cedula, String nombre, String direccion) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			int rowsModified = statement.executeUpdate("UPDATE INTO Cliente SET cedula = '"+ cedula + "', nombre = '" + nombre + "', direccion = '" + direccion +"' WHERE id = " + id);
			
			if(rowsModified > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// Methods to administer Workers
	public ArrayList<Trabajador> getAllWorkers(){
		ArrayList<Trabajador> result = new ArrayList<Trabajador>();
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT Trabajador.id, Trabajador.cedula, Trabajador.nombre, Trabajador.apellidos, \r\n" + 
					"	   Trabajador.direccion, Trabajador.edad, Trabajador.salario, JefeProyecto.cantTrabajadores, Trabajador.sexo\r\n" + 
					"FROM JefeProyecto JOIN Trabajador ON JefeProyecto.id_trabajador = Trabajador.id;");
			
			// Adding Jefe Proyectos
			while(rs.next()) {
				JefeProyecto trabajador = new JefeProyecto("T-" + rs.getInt(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(9),
						rs.getInt(6), rs.getFloat(7), rs.getInt(8));
				
				trabajador.setHistorialPuntuacion(retrieveEvaluations(new Integer(trabajador.getId().substring(2))));
				result.add(trabajador);
			}
			
			// Adding Planificadores
			rs = statement.executeQuery("SELECT Trabajador.id, Trabajador.cedula, Trabajador.nombre, Trabajador.apellidos, \r\n" + 
					"	   Trabajador.direccion, Trabajador.edad, Trabajador.salario, Planificador.frecuenciaDias, Trabajador.sexo\r\n" + 
					"FROM Planificador JOIN Trabajador ON Planificador.id_trabajador = Trabajador.id;");
			while(rs.next()) {
				Planificador trabajador = new Planificador("T-" + rs.getInt(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(9),
						rs.getInt(6), rs.getFloat(7), rs.getInt(8));
				
				trabajador.setHistorialPuntuacion(retrieveEvaluations(new Integer(trabajador.getId().substring(2))));
				result.add(trabajador);
			}
			
			// Adding Programadores
			rs = statement.executeQuery("SELECT Trabajador.id, Trabajador.cedula, Trabajador.nombre, Trabajador.apellidos, \r\n" + 
					"	   Trabajador.direccion, Trabajador.edad, Trabajador.salario, Programador.lenguaje, Trabajador.sexo\r\n" + 
					"FROM Programador JOIN Trabajador ON Programador.id_trabajador = Trabajador.id;");
			while(rs.next()) {
				Programador trabajador = new Programador("T-" + rs.getInt(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(9),
						rs.getInt(6), rs.getFloat(7), rs.getString(8));
				
				trabajador.setHistorialPuntuacion(retrieveEvaluations(new Integer(trabajador.getId().substring(2))));
				result.add(trabajador);
			}
			
			// Adding Disenadores
			rs = statement.executeQuery("SELECT Trabajador.* FROM Trabajador \r\n" + 
					"LEFT JOIN Programador ON Trabajador.id = Programador.id_trabajador\r\n" + 
					"LEFT JOIN Planificador ON Trabajador.id = Planificador.id_trabajador\r\n" + 
					"LEFT JOIN JefeProyecto ON Trabajador.id = JefeProyecto.id_trabajador\r\n" + 
					"WHERE (Programador.id_trabajador IS NULL) \r\n" + 
					"AND (Planificador.id_trabajador IS NULL)\r\n" + 
					"AND (JefeProyecto.id_trabajador IS NULL);");
			while(rs.next()) {
				Trabajador trabajador = new Trabajador("T-" + rs.getInt(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getInt(7), rs.getFloat(8));
				
				trabajador.setHistorialPuntuacion(retrieveEvaluations(new Integer(trabajador.getId().substring(2))));
				result.add(trabajador);
			}
		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Integer> retrieveEvaluations(int id) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT valor FROM evaluacion WHERE id_trabajador = " + id);
			
			while(rs.next()) {
				result.add(rs.getInt(1));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean addJefeProyecto(String cedula, String nombre, String apellidos, String direccion, String sexo, int edad, float salario) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			if(addWorker(cedula, nombre, apellidos, direccion, sexo, edad, salario)) {
				ResultSet rs = statement.executeQuery("SELECT id FROM Trabajador WHERE cedula = '" + cedula + "'");
				
				int rowsModified = statement.executeUpdate("INSERT INTO JefeProyecto (id_trabajador,cantTrabajadores) VALUES ("+ rs.getInt(1) + "," + 0 +")");
				if(rowsModified > 0) {
					result = true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean addPlanificador(String cedula, String nombre, String apellidos, String direccion, String sexo, int edad, float salario, int cantDias) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			if(addWorker(cedula, nombre, apellidos, direccion, sexo, edad, salario)) {
				ResultSet rs = statement.executeQuery("SELECT id FROM Trabajador WHERE cedula = '" + cedula + "'");
				
				int rowsModified = statement.executeUpdate("INSERT INTO Planificador (id_trabajador,frecuenciaDias) VALUES ("+ rs.getInt(1) + "," + cantDias +")");
				if(rowsModified > 0) {
					result = true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean addProgramador(String cedula, String nombre, String apellidos, String direccion, String sexo, int edad, float salario, String lenguaje) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			if(addWorker(cedula, nombre, apellidos, direccion, sexo, edad, salario)) {
				ResultSet rs = statement.executeQuery("SELECT id FROM Trabajador WHERE cedula = '" + cedula + "'");
				
				int rowsModified = statement.executeUpdate("INSERT INTO Programador (id_trabajador,lenguaje) VALUES ("+ rs.getInt(1) + ",'" + lenguaje +"')");
				if(rowsModified > 0) {
					result = true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean addWorker(String cedula, String nombre, String apellido, String direccion, String sexo, int edad, float salario) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			int rowsModified = statement.executeUpdate("INSERT INTO Trabajador (cedula,nombre,apellidos,direccion,sexo,edad,salario) "
					+ "VALUES ('"+ cedula + "','" + nombre + "','" + apellido +"','"+ direccion + "','" + sexo + "'," + edad + "," + salario + ")");
			if(rowsModified > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean deleteJefeProyecto(int id) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			int rowsModified = statement.executeUpdate("DELETE FROM JefeProyecto WHERE id_trabajador=" + id);
		
			if(rowsModified > 0) {
				result = true;
				deleteWorker(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean deletePlanificador(int id) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			int rowsModified = statement.executeUpdate("DELETE FROM Planificador WHERE id_trabajador=" + id);
		
			if(rowsModified > 0) {
				result = true;
				deleteWorker(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean deleteProgramador(int id) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			int rowsModified = statement.executeUpdate("DELETE FROM Programador WHERE id_trabajador=" + id);
		
			if(rowsModified > 0) {
				result = true;
				deleteWorker(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean deleteWorker(int id) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			int rowsModified = statement.executeUpdate("DELETE FROM Trabajador WHERE id=" + id);
		
			if(rowsModified > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean updateJefeProyecto(int id, int cantTrabajadores) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			int rowsModified = statement.executeUpdate("UPDATE INTO JefeProyecto SET cantTrabajadores = "+ cantTrabajadores + " WHERE id = " + id);
			
			if(rowsModified > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

}
