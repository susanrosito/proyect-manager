package proyectoYSistema;

import java.util.LinkedList;
import java.util.List;

import usuarioMiembroYFecha.*;

public class Sistema {

	private static Sistema instance = null;
	private List<Proyecto> proyectos;
	private List<Usuario> usuarios;

	/**
	 * el constructor de Sistema
	 */
	private Sistema() {
		this.proyectos = new LinkedList<Proyecto>();
		this.usuarios = new LinkedList<Usuario>();
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

	}

	/**
	 * Este metodo quita de una lista de usuarios un usuario específico pasado
	 * por parametro.
	 * 
	 * @param usuario
	 */
	public void eliminarUsuario(Usuario usuario) {
		this.getUsuarios().remove(usuario);
	}


	/**
	 * Este metodo quita de una lista de proyectos un proyecto específico pasado
	 * por parametro.
	 * 
	 * @param usuario
	 */
	public void eliminarProyecto(Proyecto proyecto) {
		this.getProyectos().remove(proyecto);
	}
	public List<Proyecto> getProyectos() {

		return this.proyectos;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

}
