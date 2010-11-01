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

	// el constructor de la clase proyecto
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
	 agrega a la lista de tareas del proyecto
	 una tarea pasada x parametro
	 * 
	 */
	public void agregarTarea(Tarea tarea) {
		this.getListaTareas().add(tarea);

	}

	public void agregarMiembro(Usuario usuario, String rol) {
		// agrega a la lista de miembros un miembro nuevo
		// creado a partir de un usuario y un rol,ambos pasados por parametro

		this.getListaDeMiembros().add(new Miembro(usuario, rol));
	}

	public void asignarMiembroATarea(Miembro miembro, TareaSimple tarea) {

		// a una tarea se le asigna miembro

		tarea.modificarMiembroAsignado(miembro);
	}

	public void eliminarTarea(Tarea tarea) {
		// elimina una tarea

		this.getListaTareas().remove(tarea);

	}

	public void cerrarTarea(Tarea tarea) {
		tarea.cerrate();

	}

	public void cerrarProyecto() {

		for (Tarea t : this.getListaTareas()) {

			this.cerrarTarea(t);
		}

	}

	public void reabrirUnaTarea(Tarea tarea,String comentario) {

		tarea.reAbrite(comentario);
	}

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

}
