package logico;

import java.io.Serializable;

public class Disenador extends Trabajador implements Serializable {
	private static final long serialVersionUID = 1L;
	private int aniosExp;

	public Disenador(String id, String identificacion, String nombre, String apellidos, String direccion, String sexo,
			int edad, float salario, int aniosExp) {
		super(id, identificacion, nombre, apellidos, direccion, sexo, edad, salario);
		this.aniosExp = aniosExp;
	}

	public int getAniosExp() {
		return aniosExp;
	}

}
