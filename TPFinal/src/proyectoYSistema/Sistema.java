package proyectoYSistema;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JPanel;

import usuarioMiembroYFecha.*;

public class Sistema extends Observable {

	private static Sistema instance = null;
	private Vector<Proyecto> proyectos;
	private Vector<Usuario> usuarios;

	/**
	 * el constructor de Sistema
	 */
	private Sistema() {

		this.proyectos = new Vector<Proyecto>();
		this.usuarios = new Vector<Usuario>();
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
		// actualizar observer :P o algo asi ;P

	}

	/**
	 * Este metodo quita de una lista de usuarios un usuario espec�fico pasado
	 * por parametro.
	 * 
	 * @param usuario
	 */
	public void eliminarUsuario(Usuario usuario) {
		this.getUsuarios().remove(usuario);

	}

	/**
	 * Este metodo quita de una lista de proyectos un proyecto espec�fico
	 * pasado por parametro.
	 * 
	 * @param usuario
	 */
	public void eliminarProyecto(Proyecto proyecto) {
		this.getProyectos().remove(proyecto);

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

	
	public void addObserver(Observer o) {

		super.addObserver(o);
	}

	public synchronized void deleteObserver(Observer o) {

		super.deleteObserver(o);
	}

	public synchronized void deleteObservers() {
	
		super.deleteObservers();
	}

 
	protected void setChanged() {
	
		super.setChanged();
 	}

	
	public void notifyObservers() {
	
		super.notifyObservers();
	}


}
