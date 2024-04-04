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
	
	public void registrarCliente(Cliente cliente) {
		idClientes++;
		misclientes.add(cliente);
	}
	
	public void eliminarCliente(Cliente cliente) {
		misclientes.remove(cliente);
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
	
	
	public void asignarTrabajadorAProyecto(String idProyecto, Trabajador trabajador) {
		if (losproyectos != null) {
		    for (Proyecto proyecto : losproyectos) {
		        if (proyecto.getId().equals(idProyecto)) {
		            // Verificar el tipo de trabajador y aplicar las restricciones
		            if (trabajador instanceof JefeProyecto && proyecto.getCantidadJefesProyecto() == 0) {
		                proyecto.getLosTrabajadores().add(trabajador);
		                proyecto.incrementarCantidadJefesProyecto();
		            } else if (trabajador instanceof Disenador && proyecto.getCantidadDisenadores() == 0) {
		                proyecto.getLosTrabajadores().add(trabajador);
		                proyecto.incrementarCantidadDisenadores();
		            } else if (trabajador instanceof Programador && proyecto.getCantidadProgramadores() < 3) {
		                proyecto.getLosTrabajadores().add(trabajador);
		                proyecto.incrementarCantidadProgramadores();
		            } else if (trabajador instanceof Planificador) {
		                proyecto.getLosTrabajadores().add(trabajador);
		            } else {
		                System.out.println("No se puede agregar más trabajadores de este tipo al proyecto.");
		                return;
		            }
		            return;
		        }
		    }
		}
		else {
	        System.out.println("No hay proyectos disponibles para asignar trabajadores.");
	    }
	}

	public void desasignarTrabajadorDeProyecto(String idProyecto, Trabajador trabajador) {
	    for (Proyecto proyecto : losproyectos) {
	        if (proyecto.getId().equals(idProyecto)) {
	            proyecto.getLosTrabajadores().remove(trabajador);
	            if (trabajador instanceof JefeProyecto) {
	                proyecto.decrementarCantidadJefesProyecto();
	            } else if (trabajador instanceof Disenador) {
	                proyecto.decrementarCantidadDisenadores();
	            } else if (trabajador instanceof Programador) {
	                proyecto.decrementarCantidadProgramadores();
	            }
	            return;
	        }
	    }
	}

	public void asignarClienteAProyecto(String idProyecto, Cliente cliente) {
	    for (Proyecto proyecto : losproyectos) {
	        if (proyecto.getId().equals(idProyecto)) {
	            if (proyecto.getLosClientes().size() < 5) {
	                proyecto.getLosClientes().add(cliente);
	                return;
	            } else {
	                System.out.println("El proyecto ya tiene el máximo de clientes.");
	                return;
	            }
	        }
	    }
	}

	public void desasignarClienteDeProyecto(String idProyecto, Cliente cliente) {
	    for (Proyecto proyecto : losproyectos) {
	        if (proyecto.getId().equals(idProyecto)) {
	            proyecto.getLosClientes().remove(cliente);
	            return;
	        }
	    }
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

	public Proyecto getProyectoById(String id) {
		
		for (Proyecto p : losproyectos) {
			if (p.getId().equalsIgnoreCase(id)) {
				return p;
			}
		}
		return null;
	}
	
	public Cliente getClienteById(String id) {
		
		for (Cliente c : misclientes) {
			if (c.getId().equalsIgnoreCase(id)) {
				return c;
			}
		}
		return null;
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
