package logico;

import java.io.Serializable;

public class Planificador extends Trabajador implements Serializable {
	private static final long serialVersionUID = 1L;
	private int cantDias;

	public Planificador(String id, String identificacion, String nombre, String apellidos, String direccion,
			String sexo, int edad, float salario, int cantDias) {
		super(id, identificacion, nombre, apellidos, direccion, sexo, edad, salario);
		this.cantDias = cantDias;
	}

	public int getCantDias() {
		return cantDias;
	}
	
}
