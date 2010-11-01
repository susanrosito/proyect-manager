package tareas;

import usuarioMiembroYFecha.Miembro;
import usuarioMiembroYFecha.Fecha;
import estados.Estado;
import estados.NoPuedeCambiarseElEstadoExcepccion;

public class TareaSimple extends Tarea {
	private Miembro miembroAsignado;

	/**
	 * Constructor
	 * 
	 * @param nombre
	 *            String
	 * @param descrip
	 *            String
	 * @param fechaEstFinalizacion
	 *            Fecha
	 */
	public TareaSimple(String nombre, String descrip, Fecha fechaEstFinalizacion) {
		super(nombre, descrip, fechaEstFinalizacion);
	}

	/**
	 * Cambia el estado de la Tarea,a EnTrabajo. si no puede Tira una
	 * excepeccion.
	 * 
	 * @throws NoPuedeCambiarseElEstadoExcepccion
	 */
	public void poneteEnTrabajo() throws NoPuedeCambiarseElEstadoExcepccion {
		this.getEstado().enTrabajo(this);
	}

	/**
	 * Cambia el estado de la Tarea,a Iniciada. si no puede Tira una
	 * excepeccion.
	 * 
	 * @throws NoPuedeCambiarseElEstadoExcepccion
	 */
	public void iniciate() throws NoPuedeCambiarseElEstadoExcepccion {
		this.getEstado().iniciada(this);
	}

	/**
	 * Cambia el estado de la Tarea,a Finalizate. si no puede Tira una
	 * excepeccion.
	 * 
	 * @throws NoPuedeCambiarseElEstadoExcepccion
	 */
	public void finalizate() throws NoPuedeCambiarseElEstadoExcepccion {
		this.getEstado().finalizada(this);
	}

	/**
	 * Cambia el estado de la Tarea,a Pausate. si no puede Tira una excepeccion.
	 * 
	 * @throws NoPuedeCambiarseElEstadoExcepccion
	 */
	public void pausate() throws NoPuedeCambiarseElEstadoExcepccion {
		this.getEstado().pausada(this);
	}

	public void cerrate() {
		this.getEstado().cerrada(this);
	}

	public Estado verEstado() {
		return super.getEstado();
	}

	public void reAbrite() {
		try {
			this.iniciate();
		} catch (NoPuedeCambiarseElEstadoExcepccion ex) {
			
			ex.printStackTrace();
		}

	}

	/**
	 * Este metodo modifica el miembro actual, por el del parametro.
	 * 
	 * @param miembro
	 *            Miembro
	 * @throws NoPuedeCambiarseElEstadoExcepccion
	 */
	public void modificarMiembroAsignado(Miembro miembro)
			throws NoPuedeCambiarseElEstadoExcepccion {
		if (this.getMiembroAsignado() == null) {
			this.iniciate();
		}
		this.setMiembroAsignado(miembro);
	}

	public boolean tieneOrden() {

		return false;
	}

	public boolean verificarSiEstaPausada() {
		return this.getEstado().verificarSiEstaPausada();
	}

	public boolean verificarSiEstaIniciada() {
		return this.getEstado().verificarSiEstaIniciada();
	}

	public boolean verificarSiEstaCerrada() {
		return this.getEstado().verificarSiEstaCerrada();
	}

	public boolean verificarSiEstaEnTrabajo() {
		return this.getEstado().verificarSiEstaEnTrabajo();
	}

	public boolean verificarSiEstaFinalizada() {
		return this.getEstado().verificarSiEstaFinalizada();
	}

	public boolean verificarSiEstaCreada() {
		return this.getEstado().verificarSiEstaCreada();
	}

	public void setMiembroAsignado(Miembro miembro) {
		this.miembroAsignado = miembro;

	}

	public Miembro getMiembroAsignado() {
		return this.miembroAsignado;
	}

}
