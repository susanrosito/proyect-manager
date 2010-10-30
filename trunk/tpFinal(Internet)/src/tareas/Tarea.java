package tareas;

import java.util.Date;
import usuarioMiembroYFecha.Fecha;
import estados.*;

public abstract class Tarea implements AdministradorTarea{
	private String nombre;
	private String descripcion;
	private int tiempoParaRealizarla;
	private Fecha fechaCreacion;
	private Estado estado;
	private Fecha fechaEstimadaFinalizacion;
	private Fecha fechaFinalizacion;
	private float porcentajeFinalizacion;

	public Tarea(String nombre, String descrip,Fecha fechaEstFinalizacion) {
		this.setDescripcion(descrip);
		this.setNombre(nombre);
		this.fechaCreacion = new Fecha();
		this.fechaEstimadaFinalizacion = fechaEstFinalizacion;
		this.tiempoParaRealizarla = 0;
		this.estado = Creada.GetInstance();
		this.porcentajeFinalizacion = 0;
	}

	public abstract Estado verEstado();

	public abstract void reAbrite();

	public abstract boolean tieneOrden();

	public abstract void cerrate();

	public boolean estaEnTiempo() {
		/* TODO */
		return false;
	}

	public boolean estaProximaAVencer() {
		/* TODO */
		return false;
	}

	public Fecha getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Fecha fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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
