package proyectoYSistema;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.easymock.EasyMock.*;

import tareas.*;
import usuarioMiembroYFecha.*;
import estados.*;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ProyectoTestCase extends TestCase {

	Usuario usuario1;

	Proyecto proyecto;

	Miembro miembro1;
	Miembro miembro2;
	Miembro creador;
	TareaSimple tareaSimple;
	Tarea tarea2;
	Tarea tareaCompuesta;

	public void setUp() {

		creador = createMock(Miembro.class);
		usuario1 = createMock(Usuario.class);
		miembro1 = createMock(Miembro.class);
		miembro2 = createMock(Miembro.class);
		tareaSimple = createMock(TareaSimple.class);
		tarea2 = createMock(Tarea.class);
		tareaCompuesta = createMock(TareaCompuesta.class);
		proyecto = new Proyecto("soy_proyecto1", "este es mi primer proyecto",
				usuario1);
	}

	public void testConstructorProyecto() {

		// 
		Assert.assertEquals("verifica el nombre", "soy_proyecto1", this
				.getProyecto().getNombre());

		// verifica la descripcion
		Assert.assertEquals("este es mi primer proyecto", this.getProyecto()
				.getDescripcion());
		// verifica si se crea una lista de tareas nueva
		assertEquals(new LinkedList<Tarea>(), this.getProyecto()
				.getListaTareas());
		// verifica si se crea una lista de miembros nueva
		assertEquals(new LinkedList<Miembro>(), this.getProyecto()
				.getListaDeMiembros());

		// compara los usuarios del primerMiembro
		expect(miembro1.getUsuario()).andReturn(usuario1);

		replay(miembro1);

		Assert
				.assertEquals(this.getUsuario1(), this.getMiembro1()
						.getUsuario());

	}

	public void testAgregarTarea() {

		int tamanhoAntesAgregarTarea = this.getProyecto().getListaTareas()
				.size();

		// agrega la tarea a la lista de tareas
		this.getProyecto().agregarTarea(this.getTarea());

		// comprueba si la tarea que se agrego existe dentro de la lista de
		// tareas
		assertTrue(this.getProyecto().getListaTareas()
				.contains(this.getTarea()));
		// comprueba si la tarea se agrego verificando si el tamaño de la lista
		// se incremento en 1
		assertEquals(tamanhoAntesAgregarTarea + 1, this.getProyecto()
				.getListaTareas().size());
	}

	public void testAgregarMiembroNoPuedeAgregar() {
		// ** buscar el caso en que tire la exepcion **
		// agrego el miembro al proyecto
		this.getProyecto().getListaDeMiembros().add(miembro2);

		// el miembro 1 y 2 son creados a partir del mismo usuario.
		// al ser la fechaFin igual a NULL quiere decir que el miembro todavia
		// se desempeña en el rol actual y por lo tanto no se puede crear un
		// nuevo miembro
		// a partir de ese usuario y el metodo debe generar una exepcion
		expect(miembro2.getUsuario()).andReturn(usuario1);
		expect(miembro2.getFechaFin()).andReturn(null);
		expect(miembro1.getUsuario()).andReturn(usuario1);
		expect(usuario1.equals(usuario1)).andReturn(true);
		replay(miembro1, miembro2, usuario1);
		try {
			this.getProyecto().agregarMiembro(usuario1, "administrador");
			fail();
		} catch (UsuarioYaTieneRolExepcion e) {

			e.printStackTrace();
		}

	}

	public void testAgregarMiembroSiPuedeAgregar() {

		// **busco el caso en que no tire exepcion **

		expect(miembro1.getUsuario()).andReturn(usuario1);
		replay(miembro1);
		// es la cantidad de miembros antes de agregar uno
		int cantidadEsperada = this.getProyecto().getListaDeMiembros().size();

		try {
			// agrego un miembro
			this.getProyecto().agregarMiembro(this.getUsuario1(),
					"administrador");
		} catch (UsuarioYaTieneRolExepcion e) {

			e.printStackTrace();
		}

		// verifico si se incremento en 1 la cantidad de miembros en el proyecto
		assertEquals(cantidadEsperada + 1, this.getProyecto()
				.getListaDeMiembros().size());

	}

	public void testAsignarMiembroATarea() {

		// asigno un miembro a una tarea simple
		this.getProyecto().asignarMiembroATarea(getMiembro1(), getTarea());
		// le seteo un valor de retorno para comprobar el assert
		expect(tareaSimple.getMiembroAsignado()).andReturn(miembro1);
		replay(tareaSimple);

		// compruebo dentro de la tarea si el miembro asignado es el mismo
		assertEquals(miembro1, tareaSimple.getMiembroAsignado());

	}

	public void testObtenerTareasYEstados() {

		// agrego las tareas al proyecto
		this.getProyecto().agregarTarea(this.getTarea());
		this.getProyecto().agregarTarea(this.getTareaCompuesta());
		String estadoCreada = "Creada";
		String estadoIniciada = "Iniciada";

		expect(tareaSimple.verEstado()).andReturn(estadoCreada);
		expect(tareaCompuesta.verEstado()).andReturn(estadoIniciada);
		replay(tareaSimple, tareaCompuesta);
		// ejecuto el metodo
		Map<Tarea, String> resultado = this.getProyecto()
				.obtenerTareasYEstados();

		assertEquals(estadoCreada, resultado.get(this.getTarea()));
		assertEquals(estadoIniciada, resultado.get(this.getTareaCompuesta()));
	}

	public void testObtenerMiembrosConRoles() {
		// roles que se van a usar en el test
		String rol1 = "comprador";
		String rol2 = "vendedor";

		// agrego los miembros al proyecto
		this.getProyecto().getListaDeMiembros().add(getMiembro1());
		this.getProyecto().getListaDeMiembros().add(getMiembro2());

		// mensajes que esperan los mock y que deberian retornar
		expect(miembro1.getRol()).andReturn(rol1);
		expect(miembro2.getRol()).andReturn(rol2);

		replay(miembro1, miembro2);

		Map<Miembro, String> resultado = this.getProyecto()
				.obtenerMiembrosConRoles();

		verify(miembro1, miembro2);

		assertEquals(rol1, resultado.get(miembro1));
		assertEquals(rol2, resultado.get(miembro2));

	}

	public void testReabrirUnaTarea() {
		// ******* USO MOCK
		String comentario = "se reabre porque ahora ahi luz";
		String descripcion = "una tarea simple";
		tareaSimple.reAbrite(comentario);
		expect(tareaSimple.getEstado()).andReturn(Cerrada.GetInstance());
		expect(tareaSimple.getDescripcion())
				.andReturn(descripcion + comentario);
		replay(tareaSimple);

		// envio el mensaje para reabrir la tarea lo que modificaria su estado
		// a iniciada
		this.getProyecto().reabrirUnaTarea(getTarea(), comentario);

		// verifico si se le cambio el estado de la tarea a iniciada
		assertSame(Cerrada.GetInstance(), getTarea().getEstado());
		assertEquals(descripcion + comentario, getTarea().getDescripcion());
	}

	public void testHsTotalesTrabajadas() {

		// agrego los miembros
		this.getProyecto().getListaDeMiembros().add(this.getMiembro1());
		this.getProyecto().getListaDeMiembros().add(this.getMiembro2());

		// utilizo el metodo hsTotalesTrabajadas
		// int resultadoReal = this.getProyecto().hsTotalesTrabajadas();

		// creo los mock como miembros
		expect(miembro1.getHsTrabajadas()).andReturn(7);
		expect(miembro2.getHsTrabajadas()).andReturn(15);
		replay(miembro1, miembro2);

		int resultado = this.getProyecto().hsTotalesTrabajadas();
		verify(this.miembro1, this.miembro2);
		assertEquals(22, resultado);
	}

	public void testCerrarTarea() {

		// agrego 1 tarea al proyecto//
		this.getProyecto().agregarTarea(this.getTarea());

		// agrego los mensajes del mock de tarea
		tareaSimple.cerrate();
		expect(tareaSimple.verEstado()).andReturn("Cerrada");

		replay(tareaSimple);

		// cierro una tarea especifica
		this.getProyecto().cerrarTarea(this.getTarea());

		// compruebo si esta el estado es cerrada
		assertSame("Cerrada", this.getTarea().verEstado());
	}

	public void testCerrarProyecto() {

		// agrego 2 tareas al proyecto una simple y otra compuesta
		this.getProyecto().agregarTarea(this.getTarea());
		this.getProyecto().agregarTarea(this.getTareaCompuesta());

		// agrego los mensajes del mock de tarea y tareaCompuesta
		tareaSimple.cerrate();
		expect(tareaSimple.verEstado()).andReturn("Cerrada");
		tareaCompuesta.cerrate();
		expect(tareaCompuesta.verEstado()).andReturn("Cerrada");

		replay(tareaSimple, tareaCompuesta);

		// cierro el proyecto
		this.getProyecto().cerrarProyecto();

		// compruebo si esta el estado es cerrada
		assertSame("Cerrada", this.getTarea().verEstado());
		assertSame("Cerrada", this.getTareaCompuesta().verEstado());

	}

	public void testEliminarTarea() {

		// agrego 2 tareas//
		this.getProyecto().agregarTarea(this.getTarea());
		this.getProyecto().agregarTarea(this.getTarea2());

		int resultadoEsperado = this.getProyecto().getListaTareas().size();

		// elimino 1
		this.getProyecto().eliminarTarea(this.getTarea());

		// deberia quedar 1 sola tarea en la lista

		assertEquals(resultadoEsperado - 1, getProyecto().getListaTareas()
				.size());

		// verifico que la tarea removida no existe dentro del proyecto
		assertFalse(
				"la tarea no esta dentro del proyecto, fue removida con exito",
				this.getProyecto().getListaTareas().contains(this.getTarea()));
	}

	public Miembro getMiembro2() {
		return miembro2;
	}

	public void setMiembro2(Miembro miembro2) {
		this.miembro2 = miembro2;
	}

	public Miembro getCreador() {
		return creador;
	}

	public void setCreador(Miembro creador) {
		this.creador = creador;
	}

	public Tarea getTarea2() {
		return tarea2;
	}

	public void setTarea2(Tarea tarea2) {
		this.tarea2 = tarea2;
	}

	public TareaSimple getTarea() {
		return tareaSimple;
	}

	public void setTarea(TareaSimple tarea) {
		this.tareaSimple = tarea;
	}

	public Usuario getUsuario1() {
		return usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Miembro getMiembro1() {
		return miembro1;
	}

	public void setMiembro1(Miembro miembro1) {
		this.miembro1 = miembro1;
	}

	public Tarea getTareaCompuesta() {
		return tareaCompuesta;
	}

	public void setTareaCompuesta(Tarea tareaCompuesta) {
		this.tareaCompuesta = tareaCompuesta;
	}

}
