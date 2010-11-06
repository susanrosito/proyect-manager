package tareas;

import java.util.List;

import usuarioMiembroYFecha.Fecha;
import usuarioMiembroYFecha.Miembro;
import tareas.AdministradorTarea;

public abstract class Tarea implements AdministradorTarea{
	private String nombre;
	private String descripcion;
	private int tiempoParaRealizarla;
	private Fecha fechaCreacion;
	private Fecha fechaEstimadaFinalizacion;
	private Fecha fechaFinalizacion;
	private float porcentajeFinalizacion;
	/**
	 * Constructor 
	 * @param nombre tipo String
	 * @param descrip tipo String
	 * @param fechaEstFinalizacion tipo Fecha
	 */
	public Tarea(String nombre, String descrip,Fecha fechaEstFinalizacion) {
		this.setDescripcion(descrip);
		this.setNombre(nombre);
		this.fechaCreacion = new Fecha();
		this.fechaEstimadaFinalizacion = fechaEstFinalizacion;
		this.tiempoParaRealizarla = 0;
		this.porcentajeFinalizacion = 0;
	}
	/**
	 * Se fija si la Tarea esta en estado Pausada por medio de un booleano. 
	 */
	public abstract boolean verificarSiEstaPausada();
	/**
	 * Se fija si la Tarea esta en estado Cerrada por medio de un booleano.
	 */
	public abstract boolean verificarSiEstaCerrada();
	/**
	 * Se fija si la Tarea esta en estado Creada por medio de un booleano.
	 */
	public abstract boolean verificarSiEstaCreada();
	/**
	 * Se fija si la Tarea esta en estado Finalizada por medio de un booleano.
	 */
	public abstract boolean verificarSiEstaFinalizada();
	/**
	 * Se fija si la Tarea esta en estado Iniciada por medio de un booleano.
	 */
	public abstract boolean verificarSiEstaIniciada();
	/**
	 * Se fija si la Tarea esta en estado EnTrabajo por medio de un booleano.
	 */
	public abstract boolean verificarSiEstaEnTrabajo();
	/**
	 * Muestra en que estado se encuentra la Tarea.
	 */
	public abstract String verEstado();
	/**
	 * Este metodo reAbre una tarea. 
	 */
	public abstract void reAbrite(String note);
	/**
	 * Se fija si tiene orden o no, la tarea, por medio de un booleano.
	 */
	public abstract boolean tieneOrden();
	/**
	 * Este metodo Cierra una tarea.
	 */
	public abstract void cerrate();
	/**
	 * Verifica si la Tarea esta a tiempo para seguir realizandola.
	 */
	public boolean estaEnTiempo() {
		/* TODO */
		return false;
	}
	/**
	 * Se fija si la Tarea se esta aproximando a la fecha estimada de finalizacion.
	 */
	public boolean estaProximaAVencer() {
		/* TODO */
		return false;
	}
	/**
	 * Verifica si el miembro se encuentra en la Tarea,por medio de un booleano.
	 * @param miembro Miembro
	 * @return boolean
	 */
	
	public Fecha getFechaCreacion() {
		return fechaCreacion;
	}
	public abstract List<Miembro> obtenerMiembros();
	public void setFechaCreacion(Fecha fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setTiempoParaRealizarla(int tiempoParaRealizarla) {
		this.tiempoParaRealizarla = tiempoParaRealizarla;
	}

	public int getTiempoParaRealizarla() {
		return tiempoParaRealizarla;
	}

	public void setFechaEstimadaFinalizacion(Fecha fechaEstimadaFinalizacion) {
		this.fechaEstimadaFinalizacion = fechaEstimadaFinalizacion;
	}

	public Fecha getFechaEstimadaFinalizacion() {
		return fechaEstimadaFinalizacion;
	}

	public void setFechaFinalizacion(Fecha fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public Fecha getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setPorcentajeFinalizacion(float porcentajeFinalizacion) {
		this.porcentajeFinalizacion = porcentajeFinalizacion;
	}

	public float getPorcentajeFinalizacion() {
		return porcentajeFinalizacion;
	}

}
