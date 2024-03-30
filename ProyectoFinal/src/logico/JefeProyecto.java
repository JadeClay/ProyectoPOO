package logico;

import java.io.Serializable;

public class JefeProyecto extends Trabajador implements Serializable {
	private int cantTrabajadores;
	
	public JefeProyecto(String id, String identificacion, String nombre, String apellidos, String direccion,
			String sexo, int edad, float salario) {
		super(id, identificacion, nombre, apellidos, direccion, sexo, edad, salario);
		// TODO Auto-generated constructor stub
	}

	public int getCantTrabajadores() {
		return cantTrabajadores;
	}
	
}
