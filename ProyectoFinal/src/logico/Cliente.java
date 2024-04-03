package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable {

	private String id;
	private String nombre;
	private String direccion;
	private ArrayList<Proyecto> losProyectos;
	
	public Cliente(String id, String nombre, String direccion, ArrayList<Proyecto> losProyectos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.losProyectos = losProyectos;
		
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public ArrayList<Proyecto> getLosProyectos() {
		return losProyectos;
	}

	public void setLosProyectos(ArrayList<Proyecto> losProyectos) {
		this.losProyectos = losProyectos;
	}


}
