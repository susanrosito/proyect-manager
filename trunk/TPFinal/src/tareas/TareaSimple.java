package tareas;

import java.util.List;
import java.util.ArrayList;
import usuarioMiembroYFecha.Miembro;
import usuarioMiembroYFecha.Fecha;
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
	 * excepcion.
	 * 
	 * @throws NoPuedeCambiarseElEstadoExcepccion
	 */
	public void poneteEnTrabajo() throws NoPuedeCambiarseElEstadoExcepccion {
		this.getEstado().enTrabajo(this);
	}

	/**
	 * Cambia el estado de la Tarea,a Iniciada. si no puede Tira una
	 * excepcion.
	 * 
	 * @throws NoPuedeCambiarseElEstadoExcepccion
	 */
	public void iniciate() throws NoPuedeCambiarseElEstadoExcepccion {
		this.getEstado().iniciada(this);
	}

	/**
	 * Cambia el estado de la Tarea,a Finalizate. si no puede Tira una
	 * excepcion.
	 * 
	 * @throws NoPuedeCambiarseElEstadoExcepccion
	 */
	public void finalizate(){
		this.getEstado().finalizada(this);
	}

	/**
	 * Cambia el estado de la Tarea a Pausate. si no puede tira una excepcion.
	 * 
	 * @throws NoPuedeCambiarseElEstadoExcepccion
	 */
	public void pausate() throws NoPuedeCambiarseElEstadoExcepccion {
		this.getEstado().pausada(this);
	}

	public void cerrate() {
		this.getEstado().cerrada(this);
	}

	public String verEstado() {
		return super.getEstado().toString();
	}

	public void reAbrite(String note) {
		try {
			this.iniciate();
			this.setDescripcion(note+"\n"+this.getDescripcion());
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
	public void modificarMiembroAsignado(Miembro miembro) {
		if (this.getMiembroAsignado() == null) {
			try {
				this.iniciate();
			} catch (NoPuedeCambiarseElEstadoExcepccion e) {
				
				e.printStackTrace();
			}
		}
		this.setMiembroAsignado(miembro);
	}

	public boolean tieneOrden() {

		return false;
	}
	
	public List<Miembro> obtenerMiembros() {
		List<Miembro> listaMiembro = new ArrayList<Miembro>();
		listaMiembro.add(this.getMiembroAsignado());
		return listaMiembro;
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
