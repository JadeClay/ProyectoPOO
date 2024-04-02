package logico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Empresa {
	
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
		Thread hilo = new Thread(guardarTrabajadores());
		hilo.start();
	}
	
	public void eliminarTrabajador(Trabajador trabajador) {
		mistrabajadores.remove(trabajador);
		Thread hilo = new Thread(guardarTrabajadores());
		hilo.start();
	}
	
	public void registrarContrato(Contrato contrato) {
		idContratos++;
		loscontratos.add(contrato);
		Thread hilo = new Thread(guardarContratos());
		hilo.start();
	}
	
	public void eliminarContrato(Contrato contrato) {
		loscontratos.remove(contrato);
		Thread hilo = new Thread(guardarContratos());
		hilo.start();
	}
	
	public void registrarCliente(Cliente cliente) {
		idClientes++;
		misclientes.add(cliente);
		Thread hilo = new Thread(guardarClientes());
		hilo.start();
	}
	
	public void eliminarCliente(Cliente cliente) {
		misclientes.remove(cliente);
		Thread hilo = new Thread(guardarClientes());
		hilo.start();
	}
	
	public void registarProyecto(Proyecto proyecto) {
		idProyectos++;
		losproyectos.add(proyecto);
		Thread hilo = new Thread(guardarProyectos());
		hilo.start();
	}
	
	public void modificarProyecto(String idProyecto, Proyecto nuevoProyecto) {
	    for (Proyecto proyecto : losproyectos) {
	        if (proyecto.getId().equals(idProyecto)) {
	            
	            proyecto.setNombre(nuevoProyecto.getNombre());
	            proyecto.setLosTrabajadores(nuevoProyecto.getLosTrabajadores());
	            Thread hilo = new Thread(guardarProyectos());
	            hilo.start();
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
		            Thread hilo = new Thread(guardarProyectos());
		            hilo.start();
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
	            Thread hilo = new Thread(guardarProyectos());
	            hilo.start();
	            return;
	        }
	    }
	}

	public void asignarClienteAProyecto(String idProyecto, Cliente cliente) {
	    for (Proyecto proyecto : losproyectos) {
	        if (proyecto.getId().equals(idProyecto)) {
	            if (proyecto.getLosClientes().size() < 5) {
	                proyecto.getLosClientes().add(cliente);
	                Thread hilo = new Thread(guardarProyectos());
	                hilo.start();
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
	            Thread hilo = new Thread(guardarProyectos());
	            hilo.start();
	            return;
	        }
	    }
	}


	
	// Manejo de Ficheros y Persistencia de Datos
	public void verificarDatos() {
		File file = new File("trabajadores.dat");
		File file1 = new File("contratos.dat");
		File file2 = new File("clientes.dat");
		File file3 = new File("proyectos.dat");
		
		if(file.exists() && file1.exists() && file2.exists() && file3.exists()) {
			cargarDatos();
		}else{
			try {
				inicializarFicheros();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
	}
	
	private void inicializarFicheros() throws IOException {
		File file = new File("trabajadores.dat");
		File file1 = new File("contratos.dat");
		File file2 = new File("clientes.dat");
		File file3 = new File("proyectos.dat");
		file.createNewFile();
		file1.createNewFile();
		file2.createNewFile();
		file3.createNewFile();
		
		FileOutputStream f = new FileOutputStream("trabajadores.dat");
		ObjectOutputStream oos = new ObjectOutputStream(f);
		oos.writeInt(0);
		oos.close();
		f.close();
		
		f = new FileOutputStream("contratos.dat");
		oos = new ObjectOutputStream(f);
		oos.writeInt(0);
		oos.close();
		f.close();
		
		f = new FileOutputStream("clientes.dat");
		oos = new ObjectOutputStream(f);
		oos.writeInt(0);
		oos.close();
		f.close();
		
		f = new FileOutputStream("proyectos.dat");
		oos = new ObjectOutputStream(f);
		oos.writeInt(0);
		oos.close();
		f.close();

	}
	
	private void cargarDatos() {
		Thread hilo1 = new Thread(cargarTrabajadores());
		hilo1.start();
		
		Thread hilo2 = new Thread(cargarClientes());
		hilo2.start();
		
		Thread hilo3 = new Thread(cargarProyectos());
		hilo3.start();
		
		Thread hilo4 = new Thread(cargarClientes());
		hilo4.start();
	}
	
	private Runnable cargarTrabajadores() {
		Runnable tarea = () -> {
			FileInputStream file = null;
			ObjectInputStream ois = null;
			try {
				file = new FileInputStream("trabajadores.dat");
				ois = new ObjectInputStream(file);
				int size = ois.readInt();
				
				if(size > 0) {
					idTrabajadores = size+1;
					for(int i = 0; i < size; i++) {
						Trabajador t = (Trabajador)ois.readObject();
						mistrabajadores.add(t);
					}
				}
				recuperarUltimoIdTrabajador();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (ois != null) {
		            try {
		                ois.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		        if (file != null) {
		            try {
		                file.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
			}
		};
		
		return tarea;
	}
	
	private Runnable cargarContratos() {
		Runnable tarea = () -> {
			FileInputStream file = null;
			ObjectInputStream ois = null;
			try {
				file = new FileInputStream("contratos.dat");
				ois = new ObjectInputStream(file);
				int size = ois.readInt();
				
				if(size > 0) {
					for(int i = 0; i < size; i++) {
						Contrato c = (Contrato)ois.readObject();
						this.loscontratos.add(c);
					}
				}
				recuperarUltimoIdContrato();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (ois != null) {
		            try {
		                ois.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		        if (file != null) {
		            try {
		                file.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
			}
		};
		
		return tarea;
	}
	
	private Runnable cargarProyectos() {
		Runnable tarea = () -> {
			FileInputStream file = null;
			ObjectInputStream ois = null;
			try {
				file = new FileInputStream("proyectos.dat");
				ois = new ObjectInputStream(file);
				int size = ois.readInt();
				
				if(size > 0) {
					for(int i = 0; i < size; i++) {
						Proyecto p = (Proyecto)ois.readObject();
						this.losproyectos.add(p);
					}
				}
				recuperarUltimoIdProyecto();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (ois != null) {
		            try {
		                ois.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		        if (file != null) {
		            try {
		                file.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
			}
		};
		
		return tarea;
	}
	
	private Runnable cargarClientes() {
		Runnable tarea = () -> {
			FileInputStream file = null;
			ObjectInputStream ois = null;
			try {
				file = new FileInputStream("clientes.dat");
				ois = new ObjectInputStream(file);
				int size = ois.readInt();

				if(size > 0) {
					for(int i = 0; i < size; i++) {
						Cliente c = (Cliente)ois.readObject();
						this.misclientes.add(c);
					}
				}
				recuperarUltimoIdCliente();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (ois != null) {
		            try {
		                ois.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		        if (file != null) {
		            try {
		                file.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
			}
		};
		
		return tarea;
	}
	
	public Runnable guardarTrabajadores() {
		Runnable tarea = () -> {
			FileOutputStream file = null;
			ObjectOutputStream oos = null;
			try {
				file = new FileOutputStream("trabajadores.dat", false);
				oos = new ObjectOutputStream(file);
				
				oos.writeInt(mistrabajadores.size());
				for(Trabajador trabajador : mistrabajadores) {
					oos.writeObject(trabajador);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (oos != null) {
		            try {
		                oos.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		        if (file != null) {
		            try {
		                file.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
			}
		};
		
		return tarea;
	}
	
	public Runnable guardarProyectos() {
		Runnable tarea = () -> {
			FileOutputStream file = null;
			ObjectOutputStream oos = null;
			try {
				file = new FileOutputStream("proyectos.dat", false);
				oos = new ObjectOutputStream(file);
				
				oos.writeInt(losproyectos.size());
				for(Proyecto proyecto : losproyectos) {
					oos.writeObject(proyecto);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (oos != null) {
		            try {
		                oos.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		        if (file != null) {
		            try {
		                file.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
			}

		};
		
		return tarea;
	}
	
	public Runnable guardarClientes() {
		Runnable tarea = () -> {
			FileOutputStream file = null;
			ObjectOutputStream oos = null;
			try {
				file = new FileOutputStream("clientes.dat", false);
				oos = new ObjectOutputStream(file);
				
				oos.writeInt(misclientes.size());
				for(Cliente cliente : misclientes) {
					oos.writeObject(cliente);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (oos != null) {
		            try {
		                oos.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		        if (file != null) {
		            try {
		                file.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
			}

		};
		
		return tarea;
	}
	
	public Runnable guardarContratos() {
		Runnable tarea = () -> {
			FileOutputStream file = null;
			ObjectOutputStream oos = null;
			try {
				file = new FileOutputStream("contratos.dat", false);
				oos = new ObjectOutputStream(file);
				
				oos.writeInt(loscontratos.size());
				for(Contrato contrato : loscontratos) {
					oos.writeObject(contrato);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (oos != null) {
		            try {
		                oos.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		        if (file != null) {
		            try {
		                file.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
			}

		};
		
		return tarea;
	}
	
	private void recuperarUltimoIdTrabajador() {
		Trabajador aux = null;
		aux = mistrabajadores.get(mistrabajadores.size()-1);
		
		idTrabajadores = new Integer(aux.getId().substring(2)) + 1;
	}
	
	private void recuperarUltimoIdCliente() {
		Cliente aux = null;
		aux = misclientes.get(misclientes.size()-1);
		
		idClientes = new Integer(aux.getId().substring(2)) + 1;
	}
	
	private void recuperarUltimoIdContrato() {
		Contrato aux = null;
		aux = loscontratos.get(loscontratos.size()-1);
		
		idContratos = new Integer(aux.getId().substring(2)) + 1;
	}
	
	private void recuperarUltimoIdProyecto() {
		Proyecto aux = null;
		aux = losproyectos.get(losproyectos.size() - 1);
		
		idProyectos = new Integer(aux.getId().substring(2)) + 1;
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
}
