package logico;

import java.io.Serializable;

public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String usuario;
	private String password;
	private int tipo; // 0 usuario basico, 1 usuario normal
	
	public Usuario(String id, String usuario, String password, int tipo) {
		this.id = id;
		this.usuario = usuario;
		this.password = password;
		this.tipo = tipo;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	
}
