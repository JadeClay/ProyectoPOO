package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Database {
	private static String connectionUrl;
	
	public Database() {
		this.connectionUrl = "jdbc:sqlserver://localhost:1433;encrypt=false;user=system;password=hola;database=gestionsoftware";
	}
	
	// Methods to administer users
	public Usuario logUser(String username, String password) {
		Usuario result = null;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
	    	Statement statement = conn.createStatement();
	    	ResultSet rs = statement.executeQuery("SELECT id,tipo FROM Usuario WHERE nombre = '" + username + "' AND PWDCOMPARE('" + password + "', contraseña)=1");
	    	
	    	if(rs.next()) {
	    		result = new Usuario("U-" + rs.getInt(1), username, password, rs.getInt(2));
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
			System.out.println("hola");
			while(rs.next()) {
				System.out.println("U-" + rs.getInt(1));
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
			int rowsModified = statement.executeUpdate("INSERT INTO Usuario (nombre,contraseña,tipo) VALUES ('"+ username+ "',PWDENCRYPT('" + password + "')," + type +")");
		
			if(rowsModified > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Usuario searchUserById(int id) {
		Usuario result = null;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Usuario WHERE id = " + id);
			if(rs.next()) {
				result = new Usuario(new String("U-" + rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getInt(4));
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
				Cliente cliente = new Cliente("C-" + rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				result.add(cliente);
				searchContractsOfClient(cliente);
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
	
	public Cliente searchClient(int id) {
		Cliente result = null;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Cliente WHERE id =" + id);
			
			if(rs.next()) {
				result = new Cliente("C-" + rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				searchContractsOfClient(result);
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
			rs = statement.executeQuery("SELECT * FROM view_disenadores");
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
	
	public Trabajador searchWorkerById(int id) {
		Trabajador result = null;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT id_trabajador FROM JefeProyecto WHERE id_trabajador = "+ id);
			if(rs.next()) {
				ResultSet rs1 = statement.executeQuery("SELECT Trabajador.id, Trabajador.cedula, Trabajador.nombre, Trabajador.apellidos, \r\n" + 
					"	   Trabajador.direccion, Trabajador.edad, Trabajador.salario, JefeProyecto.cantTrabajadores, Trabajador.sexo\r\n" + 
					"FROM JefeProyecto JOIN Trabajador ON JefeProyecto.id_trabajador = Trabajador.id WHERE JefeProyecto.id_trabajador = " + id);
				
				if(rs1.next()) {
					JefeProyecto trabajador = new JefeProyecto("T-" + rs1.getInt(1), 
							rs1.getString(2), rs1.getString(3), 
							rs1.getString(4), rs1.getString(5), rs1.getString(9),
							rs1.getInt(6), rs1.getFloat(7), rs1.getInt(8));
					trabajador.setHistorialPuntuacion(retrieveEvaluations(new Integer(trabajador.getId().substring(2))));
					result = trabajador;
				}
			} 
			
			rs = statement.executeQuery("SELECT id_trabajador FROM Programador WHERE id_trabajador = "+ id);
			if(rs.next()) {
				ResultSet rs1 = statement.executeQuery("SELECT Trabajador.id, Trabajador.cedula, Trabajador.nombre, Trabajador.apellidos, \r\n" + 
						"	   Trabajador.direccion, Trabajador.edad, Trabajador.salario, Programador.lenguaje, Trabajador.sexo\r\n" + 
						"FROM Programador JOIN Trabajador ON Programador.id_trabajador = Trabajador.id WHERE Programador.id_trabajador = " + id);
				while(rs1.next()) {
					Programador trabajador = new Programador("T-" + rs1.getInt(1), 
							rs1.getString(2), rs1.getString(3), 
							rs1.getString(4), rs1.getString(5), rs1.getString(9),
							rs1.getInt(6), rs1.getFloat(7), rs1.getString(8));
					
					trabajador.setHistorialPuntuacion(retrieveEvaluations(new Integer(trabajador.getId().substring(2))));
					result = trabajador;
				}
			} 
			
			rs = statement.executeQuery("SELECT id_trabajador FROM Planificador WHERE id_trabajador = "+ id);
			if(rs.next()) {
				ResultSet rs1 = statement.executeQuery("SELECT Trabajador.id, Trabajador.cedula, Trabajador.nombre, Trabajador.apellidos, \r\n" + 
						"	   Trabajador.direccion, Trabajador.edad, Trabajador.salario, Planificador.frecuenciaDias, Trabajador.sexo\r\n" + 
						"FROM Planificador JOIN Trabajador ON Planificador.id_trabajador = Trabajador.id WHERE Planificador.id_trabajador = " + id);
				while(rs1.next()) {
					Planificador trabajador = new Planificador("T-" + rs1.getInt(1), 
							rs1.getString(2), rs1.getString(3), 
							rs1.getString(4), rs1.getString(5), rs1.getString(9),
							rs1.getInt(6), rs1.getFloat(7), rs1.getInt(8));
					
					trabajador.setHistorialPuntuacion(retrieveEvaluations(new Integer(trabajador.getId().substring(2))));
					result = trabajador;
				}
			} 
			
			rs = statement.executeQuery("SELECT id FROM view_disenadores WHERE id = "+ id);
			if(rs.next()) {
				ResultSet rs1 = statement.executeQuery("SELECT * FROM view_disenadores WHERE id = " + id);
				while(rs1.next()) {
					Trabajador trabajador = new Trabajador("T-" + rs1.getInt(1), 
							rs1.getString(2), rs1.getString(3), 
							rs1.getString(4), rs1.getString(5), rs1.getString(6),
							rs1.getInt(7), rs1.getFloat(8));
					
					trabajador.setHistorialPuntuacion(retrieveEvaluations(new Integer(trabajador.getId().substring(2))));
					result = trabajador;
				}
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<JefeProyecto> getJefeProyectosDisponibles() {
		ArrayList<JefeProyecto> result = new ArrayList<JefeProyecto>();
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT Trabajador.id, Trabajador.cedula, Trabajador.nombre, Trabajador.apellidos, Trabajador.direccion, Trabajador.sexo, \n" + 
					"Trabajador.edad, Trabajador.salario, JefeProyecto.cantTrabajadores \n" + 
					"FROM JefeProyecto \n" + 
					"FULL JOIN Proyecto ON JefeProyecto.id_trabajador = Proyecto.id_jefeproyecto AND Proyecto.estado = 1\n" + 
					"JOIN Trabajador ON JefeProyecto.id_trabajador = Trabajador.id \n" + 
					"GROUP BY Trabajador.id, Trabajador.cedula, Trabajador.nombre, Trabajador.apellidos, Trabajador.direccion, Trabajador.sexo, \n" + 
					"Trabajador.edad, Trabajador.salario, JefeProyecto.cantTrabajadores HAVING count(Proyecto.nombre) < 2;");
			
			while(rs.next()) {
				JefeProyecto trabajador = new JefeProyecto("T-" + rs.getInt(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getInt(7), rs.getFloat(8), rs.getInt(9));
				result.add(trabajador);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Planificador> getPlanificadoresDisponibles() {
		ArrayList<Planificador> result = new ArrayList<Planificador>();
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT Trabajador.id, Trabajador.cedula, Trabajador.nombre, Trabajador.apellidos, \r\n" + 
					"	   Trabajador.direccion, Trabajador.edad, Trabajador.salario, Planificador.frecuenciaDias, Trabajador.sexo\r\n" + 
					"FROM Planificador JOIN Trabajador ON Planificador.id_trabajador = Trabajador.id;");
			while(rs.next()) {
				Planificador trabajador = new Planificador("T-" + rs.getInt(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(9),
						rs.getInt(6), rs.getFloat(7), rs.getInt(8));
				result.add(trabajador);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Trabajador> getDisenadoresDisponibles() {
		ArrayList<Trabajador> result = new ArrayList<Trabajador>();
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT view_disenadores.id, view_disenadores.cedula, view_disenadores.nombre, view_disenadores.apellidos, view_disenadores.direccion, view_disenadores.sexo, \n" + 
					"view_disenadores.edad, view_disenadores.salario \n" + 
					"FROM view_disenadores\n" + 
					"FULL JOIN Trabajador_Proyecto ON view_disenadores.id = Trabajador_Proyecto.id_trabajador\n" + 
					"JOIN Proyecto ON Trabajador_Proyecto.id_proyecto = Proyecto.id AND Proyecto.estado = 1\n" + 
					"GROUP BY view_disenadores.id, view_disenadores.cedula, view_disenadores.nombre, view_disenadores.apellidos, view_disenadores.direccion, view_disenadores.sexo, \n" + 
					"view_disenadores.edad, view_disenadores.salario HAVING count(Proyecto.nombre) < 2;");
			
			while(rs.next()) {
				Trabajador trabajador = new Trabajador("T-" + rs.getInt(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getInt(7), rs.getFloat(8));
				result.add(trabajador);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Programador> getProgramadoresDisponibles() {
		ArrayList<Programador> result = new ArrayList<Programador>();
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT Trabajador.id, Trabajador.cedula, Trabajador.nombre, Trabajador.apellidos, Trabajador.direccion, Trabajador.sexo, \n" + 
					"Trabajador.edad, Trabajador.salario, Programador.lenguaje\n" + 
					"FROM Programador \n" + 
					"JOIN Trabajador ON Trabajador.id = Programador.id_trabajador\n" + 
					"LEFT JOIN Trabajador_Proyecto ON Trabajador_Proyecto.id_trabajador = Trabajador.id\n" + 
					"LEFT JOIN Proyecto ON Trabajador_Proyecto.id_proyecto = Proyecto.id AND Proyecto.estado = 1\n" + 
					"WHERE Proyecto.id IS NULL;");
			
			while(rs.next()) {
				Programador trabajador = new Programador("T-" + rs.getInt(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), 
						rs.getString(6), rs.getInt(7), rs.getFloat(8), rs.getString(9));
				result.add(trabajador);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Integer> retrieveEvaluations(int id) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("EXECUTE sp_buscarEvaluaciones " + id);
			
			while(rs.next()) {
				result.add(rs.getInt(1));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean addEvaluation(int id,int valor) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			int rowsModified = statement.executeUpdate("INSERT INTO Evaluacion(id_trabajador,valor) VALUES (" + id + "," + valor + ")");
			if(rowsModified > 0) {
				result = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean addJefeProyecto(int id, String cedula, String nombre, String apellidos, String direccion, String sexo, int edad, float salario) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			if(addWorker(cedula, nombre, apellidos, direccion, sexo, edad, salario)) {
				int rowsModified = statement.executeUpdate("INSERT INTO JefeProyecto (id_trabajador,cantTrabajadores) VALUES ("+ id + "," + 0 +")");
				
				addEvaluation(id,100);
				
				if(rowsModified > 0) {
					result = true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean addPlanificador(int id, String cedula, String nombre, String apellidos, String direccion, String sexo, int edad, float salario, int cantDias) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			if(addWorker(cedula, nombre, apellidos, direccion, sexo, edad, salario)) {				
				int rowsModified = statement.executeUpdate("INSERT INTO Planificador (id_trabajador,frecuenciaDias) VALUES ("+ id + "," + cantDias +")");
				
				addEvaluation(id,100);
				
				if(rowsModified > 0) {
					result = true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean addProgramador(int id, String cedula, String nombre, String apellidos, String direccion, String sexo, int edad, float salario, String lenguaje) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			if(addWorker(cedula, nombre, apellidos, direccion, sexo, edad, salario)) {
				int rowsModified = statement.executeUpdate("INSERT INTO Programador (id_trabajador,lenguaje) VALUES ("+ id + ",'" + lenguaje +"')");
					
					addEvaluation(id,100);
					
					if(rowsModified > 0) {
						result = true;
					}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean addDisenador(int id, String cedula, String nombre, String apellidos, String direccion, String sexo, int edad, float salario) {
		boolean result = false;
		
		if(addWorker(cedula, nombre, apellidos, direccion, sexo, edad, salario)) {
					
			if(addEvaluation(id, 100)) {
				result = true;
			}
		
		}
		
		return result;
	}
	
	public boolean addWorker(String cedula, String nombre, String apellido, String direccion, String sexo, int edad, float salario) {
		boolean result = false;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			String sql ="INSERT INTO Trabajador (cedula,nombre,apellidos,direccion,sexo,edad,salario) "
					+ "VALUES ('"+ cedula + "','" + nombre + "','" + apellido +"','"+ direccion + "','" + sexo + "'," + edad + "," + salario + ")";
			 
			PreparedStatement ps = conn.prepareStatement(sql,
			        Statement.RETURN_GENERATED_KEYS);
			int rowsModified = ps.executeUpdate();
			
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
			statement.executeUpdate("DELETE FROM Trabajador_Proyecto WHERE id_trabajador=" + id);
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
	
	// Methods to administer projects
	
	// POS 0 - ArrayList con Contratos
	// POS 1 - ArrayList con Proyectos
	public ArrayList[] getAllProjects() {
		ArrayList[] result = new ArrayList[2];
		result[0] = new ArrayList<Contrato>();
		result[1] = new ArrayList<Proyecto>();
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT id,nombre,estado,id_cliente,horasHombre,fechaInicio,fechaFin,prorrogado FROM Proyecto");
			
			while(rs.next()) {
				int id = rs.getInt(1);
				Proyecto proyecto = new Proyecto("P-" + id,rs.getString(2),new ArrayList<Trabajador>());
				proyecto.setEstado(rs.getBoolean(3));
				proyecto.setLosTrabajadores(getWorkersOfProject(id));
				result[1].add(proyecto);
			
				// MODIFICAR PARA HACER UN SELECT QUE BUSQUE USANDO ESE ID
				Cliente cliente = searchClient(rs.getInt(4));
				cliente.getLosProyectos().add(proyecto);
				Contrato contrato = new Contrato("CL-" + id,cliente,proyecto, rs.getInt(5));
					
				contrato.setFechaInicio(rs.getDate(6));
				contrato.setFechaEntrega(rs.getDate(7));
				contrato.setProrrogado(rs.getBoolean(8));
				result[0].add(contrato);

			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public ArrayList<Contrato> searchContractsOfClient(Cliente cliente) {
		ArrayList<Contrato> result = new ArrayList<Contrato>();
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT id,nombre,estado,id_cliente,horasHombre,fechaInicio,fechaFin,prorrogado FROM Proyecto WHERE id_cliente =" + new Integer(cliente.getId().substring(2)));
			
			while(rs.next()) {
				int id = rs.getInt(1);
				Proyecto proyecto = new Proyecto("P-" + id,rs.getString(2),new ArrayList<Trabajador>());
				proyecto.setEstado(rs.getBoolean(3));
				proyecto.setLosTrabajadores(getWorkersOfProject(id));
			
				cliente.getLosProyectos().add(proyecto);
				Contrato contrato = new Contrato("CL-" + id,cliente,proyecto, rs.getInt(5));
					
				contrato.setFechaInicio(rs.getDate(6));
				contrato.setFechaEntrega(rs.getDate(7));
				contrato.setProrrogado(rs.getBoolean(8));
				result.add(contrato);

			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Trabajador> getWorkersOfProject(int id) {
		ArrayList<Trabajador> lostrabajadores = new ArrayList<Trabajador>();
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("EXECUTE sp_buscarDisenadoresRelacionados " + id);
			
			while(rs.next()) {
				Trabajador trabajador = new Trabajador("T-" + rs.getInt(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getInt(7), rs.getFloat(8));
				
				lostrabajadores.add(trabajador);
			}
			
			rs = statement.executeQuery("EXECUTE sp_buscarProgramadoresRelacionados " + id);
			
			while(rs.next()) {
				Programador trabajador = new Programador("T-" + rs.getInt(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getInt(7), rs.getFloat(8), rs.getString(9));
				
				lostrabajadores.add(trabajador);
			}
			
			rs = statement.executeQuery("EXECUTE sp_buscarPlanificadoresRelacionados " + id);
			
			while(rs.next()) {
				Planificador trabajador = new Planificador("T-" + rs.getInt(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getInt(7), rs.getFloat(8), rs.getInt(9));
				
				lostrabajadores.add(trabajador);
			}
			
			rs = statement.executeQuery("EXECUTE sp_buscarJefeRelacionado " + id);
			
			while(rs.next()) {
				JefeProyecto trabajador = new JefeProyecto("T-" + rs.getInt(1), 
						rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getInt(7), rs.getFloat(8), rs.getInt(9));
				
				lostrabajadores.add(trabajador);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lostrabajadores;
	}
	
	public boolean addProject(Contrato contrato) {
		boolean result = false;
		
		try {
			int idGuardadoProyecto = 0;
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			
			
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			int rowsModified = statement.executeUpdate("INSERT INTO Proyecto(nombre,horasHombre,fechaInicio,fechaFin,prorrogado,id_cliente,id_jefeproyecto,estado) "
					+ "VALUES ('" + contrato.getProyecto().getNombre() + "'," 
					+ contrato.getHoras() + ",'" 
					+ sdf1.format(contrato.getFechaInicio()) + "','" 
					+ sdf1.format(contrato.getFechaEntrega()) + "',"
					+ (contrato.isProrrogado() ? 1 : 0) + ","
					+ new Integer(contrato.getCliente().getId().substring(2)) + ","
					+ new Integer(contrato.getProyecto().getJefeProyectoAsignado().getId().substring(2)) + ","
					+ 1 + ")");
			
			// BUSCANDO CON QUE ID SE GUARDÃ“ EN LA BASE DE DATOS
			Statement statement2 = conn.createStatement();
			ResultSet rs = statement2.executeQuery("SELECT id,nombre FROM Proyecto WHERE nombre = '" + contrato.getProyecto().getNombre() + "'");
			
			while(rs.next()) {
				idGuardadoProyecto = rs.getInt(1);
			}
			
			for(Trabajador t : contrato.getProyecto().getLosTrabajadores()) {
				rowsModified += statement.executeUpdate("INSERT INTO Trabajador_Proyecto(id_trabajador,id_proyecto) VALUES ("+ new Integer(t.getId().substring(2)) + "," + idGuardadoProyecto + ")");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void deleteProject(String projectId) {
        try {
        	Connection conn = DriverManager.getConnection(connectionUrl);
            Statement statement = conn.createStatement();

            String deleteTrabajadorProyectoQuery = "DELETE FROM Trabajador_Proyecto WHERE id_proyecto = " + new Integer(projectId.substring(2));
            statement.executeUpdate(deleteTrabajadorProyectoQuery);

            String deleteProyectoQuery = "DELETE FROM Proyecto WHERE ID = " + new Integer(projectId.substring(2));
            statement.executeUpdate(deleteProyectoQuery);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	
	public boolean prorrogarProyecto(Contrato contrato, int hoursToExtend) {
		boolean result = false;
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
            contrato.prorrogarProyecto(hoursToExtend);
            
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    		
            String updateFinishDateQuery = "UPDATE Proyecto SET  horasHombre = horasHombre + " + hoursToExtend + ", prorrogado = 1 " + ",  fechaFin = '" + sdf1.format(contrato.getFechaEntrega())  + "' WHERE ID = " + new Integer(contrato.getProyecto().getId().substring(2));
            int rowsModified = statement.executeUpdate(updateFinishDateQuery);
			
            if (rowsModified > 0) {
            	result = true;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean finalizeProjecct(Contrato contrato) {
		boolean result = false;
		
        try {
        	Connection conn = DriverManager.getConnection(connectionUrl);
            Statement statement = conn.createStatement();
            String finalizeProyectoQuery = "UPDATE Proyecto SET estado = 0 WHERE ID = " + new Integer(contrato.getProyecto().getId().substring(2));
            statement.executeUpdate(finalizeProyectoQuery);
        } catch(SQLException e) {
        	e.printStackTrace();
        }
		
		return result;
	}
	
	public int recoverLastIDUser() {
		int result = 1;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("EXECUTE sp_conseguirSiguienteID \"Usuario\"");
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			rs = statement.executeQuery("SELECT count(id) FROM \"Usuario\"");
			
			if(rs.next()) {
				int cant = rs.getInt(1);
				if(cant == 0 && result == 1) {
					result += 1;
				} else if(result > 1) {
					result += 1;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	public int recoverLastIDProject() {
		int result = 1;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("EXECUTE sp_conseguirSiguienteID \"Proyecto\"");
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			rs = statement.executeQuery("SELECT count(id) FROM \"Proyecto\"");
			
			if(rs.next()) {
				int cant = rs.getInt(1);
				if(cant == 0 && result == 1) {
					result += 1;
				} else if(result > 1) {
					result += 1;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	public int recoverLastIDClient() {
		int result = 1;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("EXECUTE sp_conseguirSiguienteID \"Cliente\"");
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			rs = statement.executeQuery("SELECT count(id) FROM \"Cliente\"");
			
			if(rs.next()) {
				int cant = rs.getInt(1);
				if(cant == 0 && result == 1) {
					result += 1;
				} else if(result > 1) {
					result += 1;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	public int recoverLastIDWorker() {
		int result = 1;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("EXECUTE sp_conseguirSiguienteID \"Trabajador\"");
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			rs = statement.executeQuery("SELECT count(id) FROM \"Trabajador\"");
			
			if(rs.next()) {
				int cant = rs.getInt(1);
				if(cant == 0 && result == 1) {
					result += 1;
				} else if(result > 1) {
					result += 1;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}

	public Contrato searchContractById(int id) {
		Contrato result = null;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT id,nombre,estado,id_cliente,horasHombre,fechaInicio,fechaFin,prorrogado FROM Proyecto WHERE id =" + id);
			
			while(rs.next()) {
				Proyecto proyecto = new Proyecto("P-" + id,rs.getString(2),new ArrayList<Trabajador>());
				proyecto.setEstado(rs.getBoolean(3));
				proyecto.setLosTrabajadores(getWorkersOfProject(id));
			
				Cliente cliente = searchClient(rs.getInt(4));
				Contrato contrato = new Contrato("CL-" + id,cliente,proyecto, rs.getInt(5));
					
				contrato.setFechaInicio(rs.getDate(6));
				contrato.setFechaEntrega(rs.getDate(7));
				contrato.setProrrogado(rs.getBoolean(8));
				
				result = contrato;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Cliente searchClientByIdentification(String id) {
		Cliente result = null;
		
		try {
			Connection conn = DriverManager.getConnection(connectionUrl);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Cliente WHERE cedula = '" + id + "'");
			
			if(rs.next()) {
				result = new Cliente("C-" + rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			
			searchContractsOfClient(result);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
