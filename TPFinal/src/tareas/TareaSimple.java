package tareas;

import java.util.List;
import java.util.ArrayList;
import usuarioMiembroYFecha.Miembro;
import usuarioMiembroYFecha.Fecha;
import estados.Creada;
import estados.Estado;
import estados.NoPuedeCambiarseElEstadoExcepccion;

public class TareaSimple extends Tarea {
	private Miembro miembroAsignado;
	private Estado estado;

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
		this.setEstado(Creada.GetInstance());
	}
	/**
	 * Cambia el estado de la Tarea a EnTrabajo. Si no puede, tira una
	 * excepcion.
	 * 
	 * @throws NoPuedeCambiarseElEstadoExcepccion
	 */
	public void poneteEnTrabajo() throws NoPuedeCambiarseElEstadoExcepccion {
		this.getEstado().enTrabajo(this);
	}

	/**
	 * Cambia el estado de la Tarea a Iniciada. Si no puede, tira una excepcion.
	 * 
	 * @throws NoPuedeCambiarseElEstadoExcepccion
	 */
	public void iniciate() throws NoPuedeCambiarseElEstadoExcepccion {
		this.getEstado().iniciada(this);
	}

	/**
	 * Cambia el estado de la Tarea a Finalizada.
	 */
	public void finalizate() {
		this.getEstado().finalizada(this);
	}

	/**
	 * Cambia el estado de la Tarea a Pausada. Si no puede, tira una excepcion.
	 * 
	 * @throws NoPuedeCambiarseElEstadoExcepccion
	 */
	public void pausate() throws NoPuedeCambiarseElEstadoExcepccion {
		this.getEstado().pausada(this);
	}

	public void cerrate(String note) {
		this.getEstado().cerrada(this);
		this.setDescripcion( this.getDescripcion()+ "\n" +note );
	}

	public String verEstado() {
		return this.getEstado().toString();
	}

	public void reAbrite(String note) {
		try {
			this.iniciate();
			this.setDescripcion( this.getDescripcion()+ "\n" +note );
		} catch (NoPuedeCambiarseElEstadoExcepccion ex) {

		}

	}

	/**
	 * Este metodo modifica el miembro actual, por el del parametro.
	 * 
	 * @param miembro
	 *            Miembro
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

	public static void main(String[] args) {

	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Estado getEstado() {
		return estado;
	}
	@Override
	public boolean sosTareaSimple() {
		return true;
	}
}
