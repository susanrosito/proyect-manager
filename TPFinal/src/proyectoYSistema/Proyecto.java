package proyectoYSistema;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import tareas.*;
import usuarioMiembroYFecha.*;

public class Proyecto {
	private String nombre;
	private String descripcion;
	private Miembro creador;
	private Vector<AdministradorTarea> listaTareas;
	private Vector<Miembro> listaDeMiembros;

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
		this.setListaTareas(new Vector<AdministradorTarea>());
		this.setListaDeMiembros(new Vector<Miembro>());

	}

	/**
	 * agrega a la lista de tareas del proyecto una tarea pasada x parametro.
	 * 
	 */
	public void agregarTarea(AdministradorTarea tarea) {
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
			// todavia esta desempe�ando funciones en el proyecto
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
	public void eliminarTarea(AdministradorTarea tarea) {
		this.getListaTareas().remove(tarea);
	}

	/**
	 * cierra una tarea especifica.
	 * 
	 * @param t
	 */
	public void cerrarTarea(AdministradorTarea t,String nota) {
		t.cerrate(nota);

	}

	/**
	 * cierra el proyecto y todas sus tareas.
	 */
	public void cerrarProyecto(String nota) {

		for (AdministradorTarea t : this.getListaTareas()) {

			this.cerrarTarea(t,nota);
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
	public Map<AdministradorTarea, String> obtenerTareasYEstados() {

		Map<AdministradorTarea, String> aux = new HashMap<AdministradorTarea, String>();

		for (AdministradorTarea t : this.getListaTareas()) {

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

	public void setListaTareas(Vector<AdministradorTarea> listaTareas) {
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

	public Vector<AdministradorTarea> getListaTareas() {

		return this.listaTareas;
	}

	public Vector<Miembro> getListaDeMiembros() {

		return this.listaDeMiembros;
	}

	public void setListaDeMiembros(Vector<Miembro> listaDeMiembros) {
		this.listaDeMiembros = listaDeMiembros;
	}

}
