package proyectoYSistema;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import estados.Estado;

import tareas.*;
import usuarioMiembroYFecha.*;

public class Proyecto {
	private String nombre;
	private String descripcion;
	private Miembro creador;
	private List<Tarea> listaTareas;
	private List<Miembro> listaDeMiembros;

	/**
	 * el constructor de la clase proyecto
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

	// getters y setters
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

	/**
	 * agrega a la lista de tareas del proyecto una tarea pasada x parametro
	 * 
	 */
	public void agregarTarea(Tarea tarea) {
		this.getListaTareas().add(tarea);

	}

	/**
	 * agrega a la lista de miembros un miembro nuevo creado a partir de un
	 * usuario y un rol,ambos pasados por parametro
	 * 
	 * @param usuario
	 * @param rol
	 */
	public void agregarMiembro(Usuario usuario, String rol) {
		this.getListaDeMiembros().add(new Miembro(usuario, rol));
	}

	/**
	 * Le asigna un Miembro a una TareaSimple
	 * 
	 * @param miembro
	 * @param tarea
	 */
	public void asignarMiembroATarea(Miembro miembro, TareaSimple tarea)
			throws NoTieneQueEstarEnOtraTareaException {

		if (this.miembroYaEstaAsignado(miembro)) {

			throw new NoTieneQueEstarEnOtraTareaException();

		} else {
			tarea.modificarMiembroAsignado(miembro);
		}

	}

	/**
	 * elimina una tarea recibida por parametro
	 * 
	 * @param tarea
	 */
	public void eliminarTarea(Tarea tarea) {
		this.getListaTareas().remove(tarea);
	}

	/**
	 * cierra una tarea especifica
	 * 
	 * @param tarea
	 */
	public void cerrarTarea(Tarea tarea) {
		tarea.cerrate();

	}

	/**
	 * cierra el proyecto y todas sus tareas
	 */
	public void cerrarProyecto() {

		for (Tarea t : this.getListaTareas()) {

			this.cerrarTarea(t);
		}

	}

	/**
	 * A recibida por parametro la reabre siendo su estado cerrada y le agrega
	 * un comentario a la descripcion de la tarea
	 * 
	 * @param tarea
	 * @param comentario
	 */
	public void reabrirUnaTarea(Tarea tarea, String comentario) {

		tarea.reAbrite(comentario);
	}

	/**
	 * retorna un HasMap con una tarea como clave y un string que es el estado
	 * como clave
	 * 
	 * @return
	 */
	public Map<Tarea, String> obtenerTareasYEstados() {

		Map<Tarea, String> aux = new HashMap<Tarea, String>();

		for (Tarea t : this.getListaTareas()) {

			aux.put(t, t.verEstado());
		}
		return aux;
	}

	public Map<Miembro, String> obtenerMiembrosConRoles() {

		Map<Miembro, String> aux = new HashMap<Miembro, String>();

		for (Miembro m : this.getListaDeMiembros()) {

			aux.put(m, m.getRol());
		}
		return aux;
	}

	public int hsTotalesTrabajadas() {
		int contador = 0;

		for (Miembro m : this.getListaDeMiembros()) {

			contador = contador + m.getHsTrabajadas();
		}

		return contador;
	}

	public boolean miembroYaEstaAsignado(Miembro m) {

		boolean existe = false;

		for (Tarea t : this.getListaTareas()) {

			if (t.contieneMiembro(m)) {
				existe = true;
			}
		}

		return existe;
	}
}
