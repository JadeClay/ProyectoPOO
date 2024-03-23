package logico;

public class Trabajador {
	protected String id;
	protected String identificacion;
	protected String nombre;
	protected String apellidos;
	protected String direccion;
	protected String sexo;
	protected int edad;
	protected float salario;
	protected String evaluacion;
	
	public Trabajador(String id, String identificacion, String nombre, String apellidos, String direccion, String sexo, int edad, float salario) {
		super();
		this.id = id;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.sexo = sexo;
		this.edad = edad;
		this.salario = salario;
		this.evaluacion = "N/A";
	}
	public String getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(String evaluacion) {
		this.evaluacion = evaluacion;
	}
	public String getId() {
		return id;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getSexo() {
		return sexo;
	}
	public int getEdad() {
		return edad;
	}
	public float getSalario() {
		return salario;
	}

}
