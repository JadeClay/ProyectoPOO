package logico;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Contrato implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private Cliente cliente;
	private Proyecto proyecto;
	private Date fechaInicio;
	private Date fechaEntrega;
	private boolean prorrogado;
	private int horas;
	
	
	public Contrato(String id, Cliente cliente, Proyecto proyecto, int horas) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.proyecto = proyecto;
		this.fechaInicio = new Date();
		
		int diasNecesarios = horas/6;
		int trabajadoresAsignados = proyecto.getLosTrabajadores().size();
		int duracionEstimadaEnDias = Math.round(diasNecesarios/trabajadoresAsignados);
		
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.DAY_OF_MONTH, duracionEstimadaEnDias);
		
		this.fechaEntrega = new Date();
		fechaEntrega.setTime(calendario.getTimeInMillis());
		this.prorrogado = false;
		this.horas = horas;
		
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


	public int getHoras() {
		return horas;
	}


	public void setDiasContrato(int horas) {
		this.horas = horas;
	}
	
	public boolean activo(Date fecha) {
		boolean result = false;
		
		if(this.fechaEntrega.after(fecha)) {
			result = true;
		}
		
		return result;
	}
	
	public void prorrogarProyecto(int horasProrrogadas) {
	    if (horasProrrogadas > 0) {
	        this.horas += horasProrrogadas;
	        this.prorrogado = true; 
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(this.fechaEntrega);
	        calendar.add(Calendar.HOUR_OF_DAY, horasProrrogadas);
	        this.fechaEntrega = calendar.getTime();
	    }
	}


	
}
