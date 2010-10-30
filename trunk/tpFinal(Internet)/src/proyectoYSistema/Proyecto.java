package proyectoYSistema;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import estados.Estado;
import estados.Iniciada;
import usuarioMiembroYFecha.*;
import tareas.*;

public class Proyecto {
	private String nombre;
	private String descripcion;
	private Miembro creador;
	private List<Tarea> listaTareas;
	private List<Miembro> listaDeMiembros;

	public Proyecto(String nombre, String descripcion, Usuario usuario) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setCreador(new Miembro(usuario, "creador"));
		this.listaTareas = new LinkedList<Tarea>();
		this.listaDeMiembros = new LinkedList<Miembro>();

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

	public void agregarTarea(Tarea tarea) {
		this.getListaTareas().add(tarea);

	}

	public void agregarMiembro(Miembro miembro1) {

		this.getListaDeMiembros().add(miembro1);
	}

	public void asignarMiembroATarea(Miembro miembro1, TareaSimple tarea) {
		tarea.setMiembroAsignado(miembro1);
	}

	public void eliminarTarea(Tarea tarea) {
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

	public void reabrirUnaTarea(Tarea tarea) {

		tarea.setEstado(Iniciada.GetInstance());
	}

	public Map<Tarea, Estado> obtenerTareasYEstados() {

		Map<Tarea, Estado> aux = new HashMap<Tarea, Estado>();

		for (Tarea t : this.getListaTareas()) {

			aux.put(t, t.getEstado());
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
