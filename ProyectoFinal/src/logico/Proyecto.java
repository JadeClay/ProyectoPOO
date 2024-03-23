package logico;

import java.util.ArrayList;

public class Proyecto {

	private String id;
	private String nombre;
	private ArrayList<Trabajador> losTrabajadores;
	
	public Proyecto(String id, String nombre, ArrayList<Trabajador> losTrabajadores) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.losTrabajadores = losTrabajadores;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Trabajador> getLosTrabajadores() {
		return losTrabajadores;
	}

	public void setLosTrabajadores(ArrayList<Trabajador> losTrabajadores) {

		this.losTrabajadores = losTrabajadores; 

	}
	
}
