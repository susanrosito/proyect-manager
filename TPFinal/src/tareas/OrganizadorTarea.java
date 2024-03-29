package tareas;


import java.util.List;

import estados.NoPuedeCambiarseElEstadoExcepccion;

import usuarioMiembroYFecha.Fecha;
import usuarioMiembroYFecha.Miembro;


/**
 * Esta clase va a representar en contenedor con las tareas con orden. Por eso va a conocer
 * a la tarea y a su anterior para mantener el orden.
 */
public class OrganizadorTarea implements AdministradorTarea  {

	protected AdministradorTarea tarea;
	protected AdministradorTarea tareaAnterior;
	
	/**
	 * El constructor recibe dos parametros de cualquier clase que utilice la
	 * interfaz administradorTarea, por lo tanto puede ser de la clase tareaSimple, 
	 * TareaCompuesta o tambien de la clase OrganizadorTarea.
	 * El primer parametro es para la tarea que es la anterior (o sea, la que debe
	 * estar finalizada antes de que se pueda comenzar a trabajar en la otra) y el
	 * otro parametro es para la tarea en cuestion.
	 */
	
	public OrganizadorTarea(AdministradorTarea tareaAnterior, AdministradorTarea tarea)
	{
		this.setTarea(tarea);
		this.setTareaAnterior(tareaAnterior);
		
	}
	
	/**
	 * Ya que el organizador comunica los mensajes a la tarea que contiene
	 * este metodo retorna lo que retornaria enviarle en mensaje 
	 * puedeTrabajarseEnElla() a la tarea.
	 */
	protected boolean puedeTrabajarseEnElla()
	{
	  return this.getTareaAnterior().verificarSiEstaFinalizada();
		
	}
	
		
	
	/**
	 * Ya que el organizador comunica los mensajes a la tarea que contiene
	 * este metodo tiene el mismo efecto que enviarle el mensaje a cerrate()
	 * a la tarea.
	 */
	public void cerrate(String note) {
		this.getTarea().cerrate(note);
		
	}

	/**
	 * Ya que el organizador comunica los mensajes a la tarea que contiene
	 * este metodo retorna lo que retornaria enviarle en mensaje 
	 * estaEnTiempo() a la tarea.
	 */
	public boolean estaEnTiempo() {
		return this.getTarea().estaEnTiempo();
	}

	/**
	 * Ya que el organizador comunica los mensajes a la tarea que contiene
	 * este metodo retorna lo que retornaria enviarle en mensaje 
	 * estaProximaAVencer() a la tarea.
	 */
	public boolean estaProximaAVencer() {
		return this.getTarea().estaProximaAVencer();
	}

	/**
	 * Ya que el organizador comunica los mensajes a la tarea que contiene
	 * este metodo tiene el mismo efecto que enviarle el mensaje a cerrate()
	 * a la tarea.
	 */
	public void reAbrite(String note) {
		this.getTarea().reAbrite(note);
		
	}

	/**
	 * Ya que el organizador justamente se utiliza para crear un orden entre
	 * las tareas al preguntarle si tiene orden respondera true.
	 */
	public boolean tieneOrden() {
		
		return true;
	}

	/**
	 * Ya que el organizador de tarea comunica a los otros objetos con la tarea
	 * este metodo justamente retornara el estado en el que se encuentra la tarea.
	 */
	public String verEstado() {
		
		return this.getTarea().verEstado().toString();
	}


	/**
	 * Ya que el organizador de tarea comunica a los otros objetos con la tarea
	 * este metodo justamente retornara lo que retornaria enviarle el mensaje
	 * verificarSiEstaCerrada() a la tarea.
	 */
	public boolean verificarSiEstaCerrada() {
		return this.getTarea().verificarSiEstaCerrada();
	}

	/**
	 * Ya que el organizador de tarea comunica a los otros objetos con la tarea
	 * este metodo justamente retornara lo que retornaria enviarle el mensaje
	 * verificarSiEstaCreada() a la tarea.
	 */
	public boolean verificarSiEstaCreada() {
		
		return this.getTarea().verificarSiEstaCreada();
	}

	/**
	 * Ya que el organizador de tarea comunica a los otros objetos con la tarea
	 * este metodo justamente retornara lo que retornaria enviarle el mensaje
	 * verificarSiEstaEnTrabajo() a la tarea.
	 */
	public boolean verificarSiEstaEnTrabajo() {
	
		return this.getTarea().verificarSiEstaEnTrabajo();
	}

	/**
	 * Ya que el organizador de tarea comunica a los otros objetos con la tarea
	 * este metodo justamente retornara lo que retornaria enviarle el mensaje
	 * verificarSiEstaFinalizada() a la tarea.
	 */
	public boolean verificarSiEstaFinalizada() {
		
		return this.getTarea().verificarSiEstaFinalizada();
	}

	/**
	 * Ya que el organizador de tarea comunica a los otros objetos con la tarea
	 * este metodo justamente retornara lo que retornaria enviarle el mensaje
	 * verificarSiEstaIniciada() a la tarea.
	 */
	public boolean verificarSiEstaIniciada() {
		
		return this.getTarea().verificarSiEstaIniciada();
	}

	/**
	 * Ya que el organizador de tarea comunica a los otros objetos con la tarea
	 * este metodo justamente retornara lo que retornaria enviarle el mensaje
	 * verificarSiEstaPausada() a la tarea.
	 */
	public boolean verificarSiEstaPausada() {
		
		return this.getTarea().verificarSiEstaPausada();
	}
	
	/**
	 * Ya que el organizador de tarea comunica a los otros objetos con la tarea
	 * este metodo justamente retornara lo que retornaria enviarle el mensaje
	 * obtenerMiembros() a la tarea.
	 */
	public List<Miembro> obtenerMiembros()
	{
		return this.getTarea().obtenerMiembros();
		
	}
	public AdministradorTarea getTarea() {
		return tarea;
	}

	public void setTarea(AdministradorTarea tarea) {
		this.tarea = tarea;
	}

	public AdministradorTarea getTareaAnterior() {
		return tareaAnterior;
	}

	public void setTareaAnterior(AdministradorTarea tareaAnterior) {
		this.tareaAnterior = tareaAnterior;
	}

	@Override
	public String getDescripcion() {
		return tarea.getDescripcion();
	}

	@Override
	public String getNombre() {
		return tarea.getNombre();
	}


	public boolean sosTareaSimple() {
		return tarea.sosTareaSimple();
	}

	
	public Miembro getMiembroAsignado() {
		return this.tarea.getMiembroAsignado();
	}


	public Fecha getFechaCreacion() {
		return this.tarea.getFechaCreacion();
	}

	
	public Fecha getFechaEstimadaFinalizacion() {
		
		return this.tarea.getFechaEstimadaFinalizacion();
	}

	
	public Fecha getFechaFinalizacion() {
		
		return this.tarea.getFechaFinalizacion();
	}

	
	public void setPorcentajeFinalizacion(float porcentajeFinalizacion) {
		this.tarea.setPorcentajeFinalizacion(porcentajeFinalizacion);
		
	}

	
	public float getPorcentajeFinalizacion() {
		
		return this.tarea.getPorcentajeFinalizacion();
	}


	public void pausate() throws NoPuedeCambiarseElEstadoExcepccion {
		this.getTarea().pausate();
		
	}

	
	public void poneteEnTrabajo() throws NoPuedeCambiarseElEstadoExcepccion {
		this.getTarea().poneteEnTrabajo();
		
	}


	public void iniciate() throws NoPuedeCambiarseElEstadoExcepccion {
	this.getTarea().iniciate();
		
	}

	@Override
	public void finalizate() {
		// TODO Auto-generated method stub
		
	}

	

	
}
