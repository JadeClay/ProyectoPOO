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
		this.idClientes = 1;
		this.idContratos = 1;
		this.idProyectos = 1;
		this.idTrabajadores = 1;
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
		this.idTrabajadores++;
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
		this.idContratos++;
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
		this.idClientes++;
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
		this.idProyectos++;
		losproyectos.add(proyecto);
		Thread hilo = new Thread(guardarProyectos());
		hilo.start();
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
		
		f = new FileOutputStream("contratos.dat");
		oos = new ObjectOutputStream(f);
		oos.writeInt(0);
		oos.close();
		
		f = new FileOutputStream("clientes.dat");
		oos = new ObjectOutputStream(f);
		oos.writeInt(0);
		oos.close();
		
		f = new FileOutputStream("proyectos.dat");
		oos = new ObjectOutputStream(f);
		oos.writeInt(0);
		oos.close();

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
			FileInputStream file;
			try {
				file = new FileInputStream("trabajadores.dat");
				ObjectInputStream ois = new ObjectInputStream(file);
				int size = ois.readInt();
				
				if(size > 0) {
					idTrabajadores = size+1;
					for(int i = 0; i < size; i++) {
						Trabajador t = (Trabajador)ois.readObject();
						mistrabajadores.add(t);
					}
				}
				ois.close();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		return tarea;
	}
	
	private Runnable cargarContratos() {
		Runnable tarea = () -> {
			FileInputStream file;
			try {
				file = new FileInputStream("contratos.dat");
				ObjectInputStream ois = new ObjectInputStream(file);
				int size = ois.readInt();
				
				
				if(size > 0) {
					for(int i = 0; i < size; i++) {
						Contrato c = (Contrato)ois.readObject();
						this.loscontratos.add(c);
					}
				}
				ois.close();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		return tarea;
	}
	
	private Runnable cargarProyectos() {
		Runnable tarea = () -> {
			FileInputStream file;
			try {
				file = new FileInputStream("proyectos.dat");
				ObjectInputStream ois = new ObjectInputStream(file);
				int size = ois.readInt();
				
				if(size > 0) {
					for(int i = 0; i < size; i++) {
						Proyecto p = (Proyecto)ois.readObject();
						this.losproyectos.add(p);
					}
				}
				ois.close();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		return tarea;
	}
	
	private Runnable cargarClientes() {
		Runnable tarea = () -> {
			FileInputStream file;
			try {
				file = new FileInputStream("clientes.dat");
				ObjectInputStream ois = new ObjectInputStream(file);
				int size = ois.readInt();

				if(size > 0) {
					for(int i = 0; i < size; i++) {
						Cliente c = (Cliente)ois.readObject();
						this.misclientes.add(c);
					}
				}
				ois.close();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		return tarea;
	}
	
	private Runnable guardarTrabajadores() {
		Runnable tarea = () -> {
			try {
				FileOutputStream file = new FileOutputStream("trabajadores.dat", false);
				ObjectOutputStream oos = new ObjectOutputStream(file);
				
				oos.writeInt(mistrabajadores.size());
				for(Trabajador trabajador : mistrabajadores) {
					oos.writeObject(trabajador);
				}
				oos.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		return tarea;
	}
	
	private Runnable guardarProyectos() {
		Runnable tarea = () -> {
			try {
				FileOutputStream file = new FileOutputStream("proyectos.dat", false);
				ObjectOutputStream oos = new ObjectOutputStream(file);
				
				oos.writeInt(losproyectos.size());
				for(Proyecto proyecto : losproyectos) {
					oos.writeObject(proyecto);
				}
				oos.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		return tarea;
	}
	
	private Runnable guardarClientes() {
		Runnable tarea = () -> {
			try {
				FileOutputStream file = new FileOutputStream("clientes.dat", false);
				ObjectOutputStream oos = new ObjectOutputStream(file);
				
				oos.writeInt(misclientes.size());
				for(Cliente cliente : misclientes) {
					oos.writeObject(cliente);
				}
				oos.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		return tarea;
	}
	
	private Runnable guardarContratos() {
		Runnable tarea = () -> {
			try {
				FileOutputStream file = new FileOutputStream("contratos.dat", false);
				ObjectOutputStream oos = new ObjectOutputStream(file);
				
				oos.writeInt(loscontratos.size());
				for(Contrato contrato : loscontratos) {
					oos.writeObject(contrato);
				}
				oos.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		return tarea;
	}
}
