package proyectoYSistema;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tareas.*;
import usuarioMiembroYFecha.*;

public class Proyecto {
	private String nombre;
	private String descripcion;
	private Miembro creador;
	private List<Tarea> listaTareas;
	private List<Miembro> listaDeMiembros;

	/**
	 * El constructor de la clase proyecto.
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param usuario
	 */
	public Proyecto(String nombre, String descripcion, Usuario usuario) {

		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setCreador(new Miembro(usuario, "creador"));
		this.setListaTareas(new LinkedList<Tarea>());
		this.setListaDeMiembros(new LinkedList<Miembro>());

	}

	/**
	 * agrega a la lista de tareas del proyecto una tarea pasada x parametro.
	 * 
	 */
	public void agregarTarea(Tarea tarea) {
		this.getListaTareas().add(tarea);

	}

	/**
	 * Agrega a la lista de miembros un miembro nuevo creado a partir de un
	 * usuario y un rol,ambos pasados por parametro. Lanza una exepcion si el
	 * usuario todavia tiene un miembro activo dentro del proyecto. (miembro
	 * activo se refiere al miembro que tiene su variable fecha de fin en NULL).
	 * 
	 * @param usuario
	 * @param rol
	 * @throws UsuarioYaTieneRolExepcion
	 */
	public void agregarMiembro(Usuario usuario, String rol)
			throws UsuarioYaTieneRolExepcion {

		// recorre la lista de miembros dentro del proyecto
		for (Miembro m : this.getListaDeMiembros()) {

			// busca los miembros creados a partir del mismo usuario
			// si la fechaFin del miembro es NULL qiere decir que el miembro
			// todavia esta desempeñando funciones en el proyecto
			// y un miembro solo puede tener un rol dentro del proyecto a la
			// vez
			if (usuario.equals(m.getUsuario()) & (m.getFechaFin() == null)) {
				throw new UsuarioYaTieneRolExepcion();

			}

		}

		// si no tiene otro rol el miembro es creado y agregado al proyecto
		this.getListaDeMiembros().add(new Miembro(usuario, rol));

	}

	/**
	 * elimina una tarea recibida por parametro.
	 * 
	 * @param tarea
	 */
	public void eliminarTarea(Tarea tarea) {
		this.getListaTareas().remove(tarea);
	}

	/**
	 * cierra una tarea especifica.
	 * 
	 * @param tarea
	 */
	public void cerrarTarea(Tarea tarea) {
		tarea.cerrate();

	}

	/**
	 * cierra el proyecto y todas sus tareas.
	 */
	public void cerrarProyecto() {

		for (Tarea t : this.getListaTareas()) {

			this.cerrarTarea(t);
		}

	}

	/**
	 * Recibe una tarea por parametro,la tarea es reabierta si es posible y
	 * ademas se le agrega un comentario a la descripcion de la tarea.
	 * 
	 * @param tarea
	 * @param comentario
	 */
	public void reabrirUnaTarea(Tarea tarea, String comentario) {

		tarea.reAbrite(comentario);
	}

	/**
	 * retorna un HasMap con una tarea como clave y un string que es el estado
	 * como clave.
	 * 
	 * @return Map<Tarea, String>
	 */
	public Map<Tarea, String> obtenerTareasYEstados() {

		Map<Tarea, String> aux = new HashMap<Tarea, String>();

		for (Tarea t : this.getListaTareas()) {

			aux.put(t, t.verEstado());
		}
		return aux;
	}

	/**
	 * Se recorre la lista de miembros del proyecto retorna un HashMap.
	 * @return HashMap <Miembro,String>
	 */

	public Map<Miembro, String> obtenerMiembrosConRoles() {

		Map<Miembro, String> aux = new HashMap<Miembro, String>();

		for (Miembro m : this.getListaDeMiembros()) {

			aux.put(m, m.getRol());
		}
		return aux;
	}

	/**
	 * recorre la coleccion de miembros del proyecto.
	 * 
	 * @return cantidadHorasTotalesTrabajadas
	 */
	public int hsTotalesTrabajadas() {
		int contador = 0;

		for (Miembro m : this.getListaDeMiembros()) {

			contador = contador + m.getHsTrabajadas();
		}

		return contador;
	}

	/**
	 * le asigna a una tarea simple un miembro.
	 * 
	 * @param miembro1
	 * @param tarea
	 */
	public void asignarMiembroATarea(Miembro miembro1, TareaSimple tarea) {

		tarea.modificarMiembroAsignado(miembro1);

	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCreador(Miembro creador) {

		this.creador = creador;
	}

	public void setListaTareas(List<Tarea> listaTareas) {
		this.listaTareas = listaTareas;
	}

	public String getNombre() {

		return this.nombre;
	}

	public String getDescripcion() {

		return this.descripcion;
	}

	public Miembro getCreador() {

		return this.creador;
	}

	public List<Tarea> getListaTareas() {

		return this.listaTareas;
	}

	public List<Miembro> getListaDeMiembros() {

		return this.listaDeMiembros;
	}

	public void setListaDeMiembros(List<Miembro> listaDeMiembros) {
		this.listaDeMiembros = listaDeMiembros;
	}

}
