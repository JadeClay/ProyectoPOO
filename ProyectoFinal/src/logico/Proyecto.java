package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Proyecto implements Serializable {

	private String id;
	private String nombre;
	private ArrayList<Trabajador> losTrabajadores;
	private int cantidadJefesProyecto;
	private int cantidadDisenadores;
	private int cantidadProgramadores;
	
	public Proyecto(String id, String nombre) {
		super();
	    this.id = id;
	    this.nombre = nombre;
	    this.losTrabajadores = new ArrayList<>();
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
    
    public void asignarTrabajadorAProyecto(Trabajador trabajador) {
        if (trabajador instanceof JefeProyecto && this.cantidadJefesProyecto == 0) {
            this.losTrabajadores.add(trabajador);
            this.incrementarCantidadJefesProyecto();
        } else if (trabajador instanceof Disenador && this.cantidadDisenadores == 0) {
            this.losTrabajadores.add(trabajador);
            this.incrementarCantidadDisenadores();
        } else if (trabajador instanceof Programador && this.cantidadProgramadores < 3) {
            this.losTrabajadores.add(trabajador);
            this.incrementarCantidadProgramadores();
        } else if (trabajador instanceof Planificador) {
            this.losTrabajadores.add(trabajador);
        } else {
            System.out.println("No se puede agregar más trabajadores de este tipo al proyecto.");
            return;
        }
    }

    public void desasignarTrabajadorDeProyecto(Trabajador trabajador) {
        this.losTrabajadores.remove(trabajador);
        if (trabajador instanceof JefeProyecto) {
            this.decrementarCantidadJefesProyecto();
        } else if (trabajador instanceof Disenador) {
            this.decrementarCantidadDisenadores();
        } else if (trabajador instanceof Programador) {
            this.decrementarCantidadProgramadores();
        }
    }


    
}
