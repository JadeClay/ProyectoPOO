package logico;

public class Disenador extends Trabajador {
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
