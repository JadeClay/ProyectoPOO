package logico;

import java.io.Serializable;

public class JefeProyecto extends Trabajador implements Serializable {
	private static final long serialVersionUID = 1L;
	private int cantTrabajadores;
	
	public JefeProyecto(String id, String identificacion, String nombre, String apellidos, String direccion,
			String sexo, int edad, float salario) {
		super(id, identificacion, nombre, apellidos, direccion, sexo, edad, salario);
		// TODO Auto-generated constructor stub
	}
	
	public void setCantTrabajadores(int cant) {
		this.cantTrabajadores = cant;
	}
	
	public int getCantTrabajadores() {
		return cantTrabajadores;
	}
	
}
