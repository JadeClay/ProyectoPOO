package logico;

public class Programador extends Trabajador {
	private String lenguaje;
	
	public Programador(String id, String identificacion, String nombre, String apellidos, String direccion, String sexo,
			int edad, float salario, String lenguaje) {
		super(id, identificacion, nombre, apellidos, direccion, sexo, edad, salario);
		this.lenguaje = lenguaje;
	}

	public String getLenguaje() {
		return lenguaje;
	}
	
}
