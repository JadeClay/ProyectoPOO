package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Proyecto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String nombre;
	private ArrayList<Trabajador> losTrabajadores;
	
	public Proyecto(String id, String nombre, ArrayList<Trabajador> trabajadores) {
		super();
	    this.id = id;
	    this.nombre = nombre;
	    this.losTrabajadores = trabajadores;
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
