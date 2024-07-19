package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Proyecto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String nombre;
	private ArrayList<Trabajador> losTrabajadores;
	private boolean estado;
	
	public Proyecto(String id, String nombre, ArrayList<Trabajador> trabajadores) {
		super();
	    this.id = id;
	    this.nombre = nombre;
	    this.losTrabajadores = trabajadores;
	    this.estado = true;
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

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public Trabajador getJefeProyectoAsignado() {
		Trabajador result = null;
		// BUSCANDO QUIEN ES EL JEFE DE PROYECTO
		for(Trabajador t : losTrabajadores) {
			if(t instanceof JefeProyecto) {
				result = t;
			}
		}
		
		return result;
	}
    
}
