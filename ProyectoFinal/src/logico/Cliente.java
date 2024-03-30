package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable {

	private String id;
	private String nombre;
	private String identificador;
	private String direccion;
	private int cantProyectos;
	private ArrayList<Proyecto> losProyectos;
	
	public Cliente(String id, String nombre, String identificador, String direccion, int cantProyectos, ArrayList<Proyecto> losProyectos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.identificador = identificador;
		this.direccion = direccion;
		this.cantProyectos = cantProyectos;
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

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCantProyectos() {
		return cantProyectos;
	}

	public void setCantProyectos(int cantProyectos) {
		this.cantProyectos = cantProyectos;
	}

	public ArrayList<Proyecto> getLosProyectos() {
		return losProyectos;
	}

	public void setLosProyectos(ArrayList<Proyecto> losProyectos) {
		this.losProyectos = losProyectos;
	}


}
