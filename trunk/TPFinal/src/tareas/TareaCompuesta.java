package tareas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import estados.*;

import usuarioMiembroYFecha.Fecha;
import usuarioMiembroYFecha.Miembro;

/**
 * La clase tareaCompuesta se va a encargar de englobar las tareas que posean
 * sub tareas y deban ser reflejadas en la modalidad del trabajo.
 * 
 */

public class TareaCompuesta extends Tarea {

	private List<AdministradorTarea> tareasQueLaComponenen = new ArrayList<AdministradorTarea>();

	public TareaCompuesta(String nombre, String descrip,
			Fecha fechaEstimadaFinalizacion) {
		super(nombre, descrip, fechaEstimadaFinalizacion);
	}

	public TareaCompuesta() {
	}

	/**
	 * Este mensaje se encarga de cerrar todas las sub tareas de la tarea
	 * compuesta.
	 */
	public void cerrate(String note) {

		for (AdministradorTarea at : this.getTareasQueLaComponenen()) {
			at.cerrate(note);
		}

	}

	/**
	 * Este mensaje se encarga de reabrir todas las sub tareas de la tarea
	 * compuesta.
	 */
	public void reAbrite(String note) {
		this.setDescripcion( this.getDescripcion()+ "\n" + note );
		for (AdministradorTarea at : this.getTareasQueLaComponenen()) {
			at.reAbrite(note);
		}

	}

	/**
	 * Este mensaje retorna si la tarea compuesta en su totalidad debe ser
	 * realizada despues de otra tarea. Como si no esta dentro de un
	 * orgnaizadorDeTarea quiere decir que no tiene orden entonces este metodo
	 * retorna false.
	 */
	public boolean tieneOrden() {

		return false;
	}

	/**
	 * Este mensaje retorna si el estado de la tarea compuesta es Finalizada.
	 * Consideramos que el estado de una tarea compuesta es finalizada cuando
	 * todas sus subtareas estan finalizadas.
	 */
	public boolean verificarSiEstaFinalizada() {

		boolean estaFinalizada = true;
		for (AdministradorTarea at : this.getTareasQueLaComponenen()) {
			estaFinalizada = estaFinalizada && at.verificarSiEstaFinalizada();
		}
		return estaFinalizada;
	}

	/**
	 * Este mensaje retorna si el estado de la tarea compuesta es cerrada.
	 * Consideramos que el estado de una tarea compuesta es cerrada cuando todas
	 * sus subtareas estan cerradas.
	 */
	public boolean verificarSiEstaCerrada() {

		boolean estaCerrada = true;
		for (AdministradorTarea at : this.getTareasQueLaComponenen()) {
			estaCerrada = estaCerrada && at.verificarSiEstaCerrada();
		}
		return estaCerrada;
	}

	/**
	 * Este mensaje retorna si el estado de la tarea compuesta es creada.
	 * Consideramos que el estado de una tarea compuesta es creada cuando todas
	 * sus subtareas estan creadas.
	 */
	public boolean verificarSiEstaCreada() {

		boolean estaCreada = true;
		for (AdministradorTarea at : this.getTareasQueLaComponenen()) {
			estaCreada = estaCreada && at.verificarSiEstaCreada();
		}

		return estaCreada;
	}

	/**
	 * Este mensaje retorna si el estado de la tarea compuesta es enTrabajo.
	 * Consideramos que el estado de una tarea compuesta es enTrabajo cuando
	 * todas sus subtareas que no esten iniciadas, creadas o finalizadas esten
	 * enTrabajo.
	 */
	public boolean verificarSiEstaEnTrabajo() {

		//Con esta variable nos aseguramos que por lo menos una este en trabajo
		boolean algunaEstaEnTrabajo = false; 
		//Con esta variable verificamos que las demas esten o finalizadas, creadas, o iniciadas.
		boolean estaEnTrabajo = true;
		for (AdministradorTarea at : this.getTareasQueLaComponenen()) {
			estaEnTrabajo = estaEnTrabajo
					&& (at.verificarSiEstaEnTrabajo()
							| at.verificarSiEstaFinalizada()
							| at.verificarSiEstaCreada() | at
							.verificarSiEstaIniciada());
			algunaEstaEnTrabajo = algunaEstaEnTrabajo
			|| (at.verificarSiEstaEnTrabajo());
		}
		//Retorna si al menos una esta en trabaja y las demas finalizadas, creadas o iniciadas.
		return estaEnTrabajo&&algunaEstaEnTrabajo;}
		
	

	/**
	 * Este mensaje retorna si el estado de la tarea compuesta es iniciada.
	 * Consideramos que el estado de una tarea compuesta es iniciada cuando
	 * todas sus subtareas que no esten creadas estan iniciadas.
	 */

	public boolean verificarSiEstaIniciada() {

		//Con esta variable nos aseguramos que almenos una este iniciada
		boolean algunaEstaIniciada = false; 
		//Con esta variable nos aseguramos que las que no esten iniciadas esten creadas.
		boolean estaIniciada = true;
		for (AdministradorTarea at : this.getTareasQueLaComponenen()) {
			estaIniciada = estaIniciada
					&& (at.verificarSiEstaIniciada() | at.verificarSiEstaCreada());
			algunaEstaIniciada = algunaEstaIniciada
			|| (at.verificarSiEstaIniciada());
		}
		//Retorna si al menos una esta iniciada y las demas creadas o iniciadas.
		return estaIniciada && algunaEstaIniciada;
		
	}

	/**
	 * Este mensaje retorna si el estado de la tarea compuesta es pausada.
	 * Consideramos que el estado de una tarea compuesta es pausada cuando
	 * alguna de todas sus subtareas este pausada.
	 */
	public boolean verificarSiEstaPausada() {

		boolean estaPausada = false;
		for (AdministradorTarea at : this.getTareasQueLaComponenen()) {
			estaPausada = estaPausada | at.verificarSiEstaPausada();
		}
		return estaPausada;
	}

	/**
	 * Este mensaje retorna el estado de la tarea compuesta basandose en los
	 * estados de sus subtareas.
	 */

	public String verEstado() {

		if (this.verificarSiEstaCreada()) {
			return Creada.GetInstance().toString();
		} else {
			if (this.verificarSiEstaCerrada()) {
				return Cerrada.GetInstance().toString();
			} else {
				if (this.verificarSiEstaFinalizada()) {
					return Finalizada.GetInstance().toString();
				} else {
					if (this.verificarSiEstaPausada()) {
						return Pausada.GetInstance().toString();
					} else {
						if (this.verificarSiEstaIniciada()) {
							return Iniciada.GetInstance().toString();
						} else {
							return EnTrabajo.GetInstance().toString();
						}	}  } }	} }

	/**
	 * Este mensaje agrega a la tarea pasada por parametro a la tarea compuesta.
	 * 
	 */
	public void agregarTarea(AdministradorTarea tarea) {
		
		this.getTareasQueLaComponenen().add(tarea);
	}



	/**
	 * Este metodo retorna una lista con todos los miembros de la
	 * tareaCompuesta.
	 */
	
	public List<Miembro> obtenerMiembros() {
		
		List<Miembro> miembros = new LinkedList<Miembro>();

		for (AdministradorTarea at : this.getTareasQueLaComponenen()) {
			
			for (Miembro m : at.obtenerMiembros()){
				
				miembros.add(m); }
			
		}

		return miembros;

	}

	public void setTareasQueLaComponenen(
			List<AdministradorTarea> tareasQueLaComponenen) {
		this.tareasQueLaComponenen = tareasQueLaComponenen;
	}

	public List<AdministradorTarea> getTareasQueLaComponenen() {
		return tareasQueLaComponenen;
	}

	
	public boolean sosTareaSimple() {
		return false;
	}

	
	public Miembro getMiembroAsignado() {
		
		return null;
	}


	public void pausate() throws NoPuedeCambiarseElEstadoExcepccion {

		
	}

	
	public void poneteEnTrabajo() throws NoPuedeCambiarseElEstadoExcepccion {
	
		
	}

	
	public void iniciate() throws NoPuedeCambiarseElEstadoExcepccion {
		
		
	}

	
	public void finalizate() {
		
		
	}

	




}
