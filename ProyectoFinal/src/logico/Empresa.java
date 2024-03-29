package logico;


import java.util.ArrayList;

public class Empresa {
	
	private ArrayList<Trabajador> mistabajadores;
	private ArrayList<Cliente> misclientes;
	private ArrayList<Proyecto> losproyectos;
	private ArrayList<Contrato> loscontratos;
	
	
	public Empresa(ArrayList<Trabajador> mistabajadores, ArrayList<Cliente> misclientes,
			ArrayList<Proyecto> losproyectos, ArrayList<Contrato> loscontratos) {
		super();
		this.mistabajadores = mistabajadores;
		this.misclientes = misclientes;
		this.losproyectos = losproyectos;
		this.loscontratos = loscontratos;
	}


	public ArrayList<Trabajador> getMistabajadores() {
		return mistabajadores;
	}


	public void setMistabajadores(ArrayList<Trabajador> mistabajadores) {
		this.mistabajadores = mistabajadores;
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


}
