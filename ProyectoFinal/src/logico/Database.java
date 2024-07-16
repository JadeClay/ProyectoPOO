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
			ResultSet rs = statement.executeQuery("INSERT INTO Usuario (nombre,contraseña,tipo) VALUES ('"+ username+ "','" + password + "'," + type +")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
