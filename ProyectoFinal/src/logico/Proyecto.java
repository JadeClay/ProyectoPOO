package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Proyecto implements Serializable {

	private String id;
	private String nombre;
	private ArrayList<Trabajador> losTrabajadores;
	private ArrayList<Cliente> losClientes;
	private int cantidadJefesProyecto;
	private int cantidadDisenadores;
	private int cantidadProgramadores;
	
	public Proyecto(String id, String nombre) {
		super();
	    this.id = id;
	    this.nombre = nombre;
	    this.losTrabajadores = new ArrayList<>();
	    this.losClientes = new ArrayList<>();
	    this.cantidadJefesProyecto = 0;
	    this.cantidadDisenadores = 0;
	    this.cantidadProgramadores = 0;

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

	public ArrayList<Cliente> getLosClientes() {
		return losClientes;
	}

	public void setLosClientes(ArrayList<Cliente> losClientes) {
		this.losClientes = losClientes;
	}

	public int getCantidadJefesProyecto() {
		return cantidadJefesProyecto;
	}

	public void setCantidadJefesProyecto(int cantidadJefesProyecto) {
		this.cantidadJefesProyecto = cantidadJefesProyecto;
	}

	public int getCantidadDisenadores() {
		return cantidadDisenadores;
	}

	public void setCantidadDisenadores(int cantidadDisenadores) {
		this.cantidadDisenadores = cantidadDisenadores;
	}

	public int getCantidadProgramadores() {
		return cantidadProgramadores;
	}

	public void setCantidadProgramadores(int cantidadProgramadores) {
		this.cantidadProgramadores = cantidadProgramadores;
	}
	
	public void incrementarCantidadDisenadores() {
	    cantidadDisenadores++;
	}

    public void decrementarCantidadDisenadores() {
        cantidadDisenadores--;
    }

    public void incrementarCantidadProgramadores() {
        cantidadProgramadores++;
    }

    public void decrementarCantidadProgramadores() {
        cantidadProgramadores--;
    }
    
    public void incrementarCantidadJefesProyecto() {
        cantidadJefesProyecto++;
    }

    public void decrementarCantidadJefesProyecto() {
        cantidadJefesProyecto--;
    }
}
