package proyectoYSistema;

import java.util.Vector;

import usuarioMiembroYFecha.*;
import ventanaTarea.InterfaceObserversSistema;

public class Sistema {

	private static Sistema instance = null;
	private Vector<Proyecto> proyectos;
	private Vector<Usuario> usuarios;
	private Vector<InterfaceObserversSistema> observadores;

	/**
	 * el constructor de Sistema
	 */
	private Sistema() {

		this.proyectos = new Vector<Proyecto>();
		this.usuarios = new Vector<Usuario>();
		this.observadores = new Vector<InterfaceObserversSistema>();
	}

	/**
	 * Constructor de Sistema. Sistema es un singleton.
	 */
	public static Sistema GetInstance() {

		if (instance == null) {
			instance = new Sistema();
		}

		return instance;
	}

	/**
	 * crea un proyecto a partir de un nombre,una descripcion y un usuario (el
	 * cual se va a convertir en el creador) pasados por parametro y lo agrega a
	 * la lista de usuarios del sistema.
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param usuario
	 */
	public void crearUnProyecto(String nombre, String descripcion,
			Usuario usuario) {
		this.getProyectos().add(new Proyecto(nombre, descripcion, usuario));
		notificarObservadores();
	}

	/**
	 * crea un usuario a partir de un nombre y email pasados por parametro y lo
	 * agrega a la lista de usuarios del sistema.
	 * 
	 * @param nombre
	 * @param email
	 */
	public void crearUnUsuario(String nombre, String email) {

		this.getUsuarios().add(new Usuario(nombre, email));
		notificarObservadores();
	}

	/**
	 * Este metodo quita de una lista de usuarios un usuario especifico pasado
	 * por parametro.
	 * 
	 * @param usuario
	 */
	public void eliminarUsuario(Usuario usuario) {
		this.getUsuarios().remove(usuario);
		notificarObservadores();

	}

	/**
	 * Este metodo quita de una lista de proyectos un proyecto especifico pasado
	 * por parametro.
	 * 
	 * @param usuario
	 */
	public void eliminarProyecto(Proyecto proyecto) {
		this.getProyectos().remove(proyecto);
		notificarObservadores();

	}

	public Vector<Proyecto> getProyectos() {

		return this.proyectos;
	}

	public Vector<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Vector<Usuario> usuarios) {
		this.usuarios = usuarios;

	}

	public void setProyectos(Vector<Proyecto> proyectos) {
		this.proyectos = proyectos;

	}

	/**
	 * notifica a todos los observadores que hubo un cambio recorriendo la lista
	 * de observadores.
	 */
	public void notificarObservadores() {

		for (InterfaceObserversSistema obs : this.observadores) {

			obs.actualizarObservadores(this);
		}

	}

	/**
	 * agrega un observador a la lista de observadores.
	 * 
	 * @param un
	 *            observer que implemente la interfaz
	 *            'InterfaceObserversSistema'
	 */
	public void agregarObservador(InterfaceObserversSistema obs) {
		observadores.add(obs);
	}

	/**
	 * elimina un observador de la lista de observadores.
	 * 
	 * @param un
	 *            observer que implemente la interfaz
	 *            'InterfaceObserversSistema'
	 */
	public void eliminarObservador(InterfaceObserversSistema obs) {
		observadores.remove(obs);
	}

}
