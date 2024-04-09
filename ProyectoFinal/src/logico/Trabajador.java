package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Trabajador implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String id;
	protected String identificacion;
	protected String nombre;
	protected String apellidos;
	protected String direccion;
	protected String sexo;
	protected int edad;
	protected float salario;
	protected ArrayList<Integer> historialPuntuacion = new ArrayList<Integer>();
	
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
		this.historialPuntuacion.add(100);
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
	public String getEvaluacionActual() {
		String result = "";
		int ultimaEvaluacion = historialPuntuacion.size()-1;
		
		if(historialPuntuacion.get(ultimaEvaluacion) > 90) {
			result = "Destacado";
		} else if(historialPuntuacion.get(ultimaEvaluacion) > 70) {
			result = "Cumplidor";
		} else {
			result = "Incumplidor";
		}
		
		return result;
	}
	public ArrayList<Integer> getHistorialPuntuacion() {
		return historialPuntuacion;
	}
	public void actualizarHistorial(int cantHoras, boolean atraso) {
		int puntosAModificar = cantHoras/100;
		int indiceHistActual = historialPuntuacion.size()-1;
		int historialActual = historialPuntuacion.get(indiceHistActual);
		
		// Si se actualiza el historial por un atraso
		if(atraso) {
			historialPuntuacion.add(historialActual - puntosAModificar);
		} else if((historialActual + puntosAModificar) <= 100) { // Si se actualiza el historial por terminar el proyecto antes
			historialPuntuacion.add(historialActual + puntosAModificar);
		}
		
	}
	
	public int getCantDeProyectosAsignados() {
		int result = 0;
		
		for(Contrato c : Empresa.getInstance().getLoscontratos()) {
			Proyecto p = c.getProyecto();
			
			if(!c.activo(new Date())) {

				for(Trabajador t : p.getLosTrabajadores()) {
					if(t.getId().equalsIgnoreCase(id)) {
						result++;
					}

				}
			}
		}
		
		return result;
	}

	public String getPosiDeTrabajador(Trabajador t) { 

		if (t instanceof Programador) {
			return "Programador ";
		}else if (t instanceof Planificador) {
			return "Planificador";
		}else if (t instanceof JefeProyecto) {
			return "JefeProyecto";
		}else if (t instanceof Disenador) {
			return "Diseñador   ";
		}
		return null;
	}
	
}
