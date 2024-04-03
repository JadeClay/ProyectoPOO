package logico;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Contrato implements Serializable {

	private String id;
	private Cliente cliente;
	private Proyecto proyecto;
	private Date fechaInicio;
	private Date fechaEntrega;
	private boolean prorrogado;
	private int diasContrato;
	
	
	public Contrato(String id, Cliente cliente, Proyecto proyecto, int diasContrato) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.proyecto = proyecto;
		this.fechaInicio = new Date();
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.DAY_OF_MONTH, diasContrato);	
		this.fechaEntrega = new Date();
		fechaEntrega.setTime(calendario.getTimeInMillis());
		this.prorrogado = false;
		this.diasContrato = diasContrato;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Proyecto getProyecto() {
		return proyecto;
	}


	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaEntrega() {
		return fechaEntrega;
	}


	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}


	public boolean isProrrogado() {
		return prorrogado;
	}


	public void setProrrogado(boolean prorrogado) {
		this.prorrogado = prorrogado;
	}


	public int getDiasContrato() {
		return diasContrato;
	}


	public void setDiasContrato(int diasContrato) {
		this.diasContrato = diasContrato;
	}

	
}
