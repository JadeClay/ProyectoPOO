package logico;


import java.io.Serializable;
import java.util.ArrayList;

public class Empresa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Trabajador> mistrabajadores;
	private ArrayList<Cliente> misclientes;
	private ArrayList<Proyecto> losproyectos;
	private ArrayList<Contrato> loscontratos;
	public static int idTrabajadores;
	public static int idContratos;
	public static int idProyectos;
	public static int idClientes;
	public static Empresa empresa;
	
	public Empresa() {
		super();
		this.mistrabajadores = new ArrayList<Trabajador>();
		this.misclientes = new ArrayList<Cliente>();
		this.losproyectos = new ArrayList<Proyecto>();
		this.loscontratos = new ArrayList<Contrato>();
		idClientes = 1;
		idContratos = 1;
		idProyectos = 1;
		idTrabajadores = 1;
	}

	public static Empresa getInstance(){
		if(empresa == null){
			empresa = new Empresa();
		}
		return empresa;
	}
	
	public static void setEmpresa(Empresa temp) {
		empresa = temp;
		empresa.recuperarUltimoIdCliente();
		empresa.recuperarUltimoIdContrato();
		empresa.recuperarUltimoIdProyecto();
		empresa.recuperarUltimoIdTrabajador();
	}
	
	public ArrayList<Trabajador> getMistabajadores() {
		return mistrabajadores;
	}


	public void setMistabajadores(ArrayList<Trabajador> mistabajadores) {
		this.mistrabajadores = mistabajadores;
	}


	public ArrayList<Cliente> getMisclientes() {
		return misclientes;
	}


	public void setMisclientes(ArrayList<Cliente> misclientes) {
		this.misclientes = misclientes;
	}


	public ArrayList<Proyecto> getLosproyectos() {
		return losproyectos;
	}


	public void setLosproyectos(ArrayList<Proyecto> losproyectos) {
		this.losproyectos = losproyectos;
	}


	public ArrayList<Contrato> getLoscontratos() {
		return loscontratos;
	}


	public void setLoscontratos(ArrayList<Contrato> loscontratos) {
		this.loscontratos = loscontratos;
	}
	
	public void registrarTrabajador(Trabajador trabajador) {
		idTrabajadores++;
		mistrabajadores.add(trabajador);
	}
	
	public void eliminarTrabajador(Trabajador trabajador) {
		mistrabajadores.remove(trabajador);
	}
	
	public void registrarContrato(Contrato contrato) {
		idContratos++;
		loscontratos.add(contrato);
	}
	
	public void eliminarContrato(Contrato contrato) {
		loscontratos.remove(contrato);
	}
	
	public void registarProyecto(Proyecto proyecto) {
		idProyectos++;
		losproyectos.add(proyecto);
	}
	
	public void modificarProyecto(String idProyecto, Proyecto nuevoProyecto) {
	    for (Proyecto proyecto : losproyectos) {
	        if (proyecto.getId().equals(idProyecto)) {
	            
	            proyecto.setNombre(nuevoProyecto.getNombre());
	            proyecto.setLosTrabajadores(nuevoProyecto.getLosTrabajadores());
	            return; 
	        }
	    }
	}
	
	public void eliminarCliente(Cliente client) {
		
		for (Proyecto p : client.getLosProyectos()) {
			p.setEstado(false);//o eliminarlos
		}
		misclientes.remove(client);
	}
	
	private void recuperarUltimoIdTrabajador() {
		Trabajador aux = null;
		if(mistrabajadores.size() != 0) {
			aux = mistrabajadores.get(mistrabajadores.size()-1);
			
			idTrabajadores = new Integer(aux.getId().substring(2)) + 1;
		} else {
			idTrabajadores = 1;
		}
	}
	
	private void recuperarUltimoIdCliente() {
		Cliente aux = null;
		if(misclientes.size() != 0) {
			aux = misclientes.get(misclientes.size()-1);
			
			idClientes = new Integer(aux.getId().substring(2)) + 1;
		} else {
			idClientes = 1;
		}
	}
	
	private void recuperarUltimoIdContrato() {
		Contrato aux = null;
		if(losproyectos.size() != 0) {
			aux = loscontratos.get(loscontratos.size()-1);
			
			idContratos = new Integer(aux.getId().substring(2)) + 1;
		} else {
			idContratos = 1;
		}
	}
	
	private void recuperarUltimoIdProyecto() {
		Proyecto aux = null;
		if(losproyectos.size() != 0) {
			aux = losproyectos.get(losproyectos.size() - 1);
			
			idProyectos = new Integer(aux.getId().substring(2)) + 1;
		} else {
			idProyectos = 1;
		}
	}

	public Trabajador buscarTrabajadorById(String id) {
		Trabajador result = null;
		boolean encontrado = false;
		int i=0;
		
		while(!encontrado && i<mistrabajadores.size()) {
			if(mistrabajadores.get(i).getId().equalsIgnoreCase(id)) {
				result = mistrabajadores.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return result;
	}

	public Proyecto buscarProyectoById(String id) {
		Proyecto aux = null;
		
		for (Proyecto p : losproyectos) {
			if (p.getId().equalsIgnoreCase(id)) {
				aux = p;
			}
		}
		
		return aux;
	}
	
	public Cliente buscarClienteById(String id) {
		Cliente aux = null;
		
		for (Cliente c : misclientes) {
			if (c.getId().equalsIgnoreCase(id)) {
				aux = c;
			}
		}
		
		return aux;
	}

	public Cliente buscarClienteByIdentificador(String id) {
		Cliente aux = null;
		boolean encontrado = false;
		
		int i=0;
		while(!encontrado && i<misclientes.size()) {
			if(misclientes.get(i).getIdentificacion().equalsIgnoreCase(id)) {
				aux = misclientes.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return aux;
	}
	
	public ArrayList<JefeProyecto> getJefeProyectosDisponibles() {
		ArrayList<JefeProyecto> aux = new ArrayList<JefeProyecto>();
		
		for(Trabajador t : mistrabajadores) {
			if(t instanceof JefeProyecto && t.getCantDeProyectosAsignados() < 2) {
				aux.add((JefeProyecto) t);
			}
		}
		
		return aux;
	}

	public ArrayList<Disenador> getDisenadoresDisponibles() {
		ArrayList<Disenador> aux = new ArrayList<Disenador>();
		
		for(Trabajador t : mistrabajadores) {
			if(t instanceof Disenador && t.getCantDeProyectosAsignados() < 2) {
				aux.add((Disenador) t);
			}
		}
		
		return aux;
	}

	public ArrayList<Planificador> getPlanificadoresDisponibles() {
		ArrayList<Planificador> aux = new ArrayList<Planificador>();
		
		for(Trabajador t : mistrabajadores) {
			if(t instanceof Planificador) {
				aux.add((Planificador) t);
			}
		}
		
		return aux;
	}
	
	public ArrayList<Programador> getProgramadoresDisponibles() {
		ArrayList<Programador> aux = new ArrayList<Programador>();
		
		for(Trabajador t : mistrabajadores) {
			if(t instanceof Programador && t.getCantDeProyectosAsignados() < 1) {
				aux.add((Programador) t);
			}
		}
		
		return aux;
	}

}
