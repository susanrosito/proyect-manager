package tareas;

import estados.Estado;

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
	
	protected boolean puedeTrabajarseEnElla()
	{
	  return this.getTareaAnterior().verificarSiEstaFinalizada();
		
	}
	
	public void cerrate() {
		this.getTarea().cerrate();
		
	}

	
	public boolean estaEnTiempo() {
		return this.getTarea().estaEnTiempo();
	}

	
	public boolean estaProximaAVencer() {
		return this.getTarea().estaProximaAVencer();
	}

	
	public void reAbrite() {
		this.getTarea().reAbrite();
		
	}

	
	public boolean tieneOrden() {
		
		return true;
	}

	
	public Estado verEstado() {
		
		return this.getTarea().verEstado();
	}

	
	public boolean verificarSiEstaCerrada() {
		return this.getTarea().verificarSiEstaCerrada();
	}


	public boolean verificarSiEstaCreada() {
		
		return this.getTarea().verificarSiEstaCreada();
	}

	
	public boolean verificarSiEstaEnTrabajo() {
	
		return this.getTarea().verificarSiEstaEnTrabajo();
	}

	
	public boolean verificarSiEstaFinalizada() {
		
		return this.getTarea().verificarSiEstaFinalizada();
	}

	
	public boolean verificarSiEstaIniciada() {
		
		return this.getTarea().verificarSiEstaIniciada();
	}

	
	public boolean verificarSiEstaPausada() {
		
		return this.getTarea().verificarSiEstaPausada();
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

	

	
}
