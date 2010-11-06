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
	Estado estadoIniciada;
	Estado estadoCerrada;
	TareaSimple tarea;
	Tarea tarea2;
	

	public void setUp() {

		creador = createMock(Miembro.class);
		usuario1 = createMock(Usuario.class);
		miembro1 = createMock(Miembro.class);
		miembro2 = createMock(Miembro.class);
		tarea = createMock(TareaSimple.class);
		tarea2 = createMock(Tarea.class);
		estadoIniciada=createMock(Iniciada.class);
		estadoCerrada=createMock(Iniciada.class);
		proyecto = new Proyecto("soy_proyecto1", "este es mi primer proyecto",
				usuario1);
		/*
		 * miembroCreador = new Miembro(usuario1, "creador"); miembro1 = new
		 * Miembro(usuario2, "administrador"); miembro2 = new Miembro(usuario3,
		 * "administrador2");
		 */

		/*
		 * tarea = new TareaSimple("tarea_1", "mi_primera_Tarea", new
		 * Fecha("19990303")); tarea2= new TareaSimple("tarea_2",
		 * "mi_segunda_Tarea",new Fecha("19980202"));
		 */

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

	public void testAgregarMiembro() {
		// es la cantidad de miembros antes de agregar uno
		int cantidadEsperada = this.getProyecto().getListaDeMiembros().size();

		// agrego un miembro
		this.getProyecto().agregarMiembro(this.getUsuario1(), "administrador");

		// verifico si se incremento en 1 la cantidad de miembros en el proyecto
		assertEquals(cantidadEsperada + 1, this.getProyecto()
				.getListaDeMiembros().size());
	}

	public void testAsignarMiembroATarea() {
		
		// asigno un miembro a una tarea simple
		this.getProyecto().asignarMiembroATarea(getMiembro1(), getTarea());
		//le seteo un valor de retorno para comprobar el assert
		expect(tarea.getMiembroAsignado()).andReturn(miembro1);
		replay(tarea);
		
		// compruebo dentro de la tarea si el miembro asignado es el mismo
		assertEquals(miembro1, tarea.getMiembroAsignado());

	}

	public void testObtenerTareasYEstados() {
		// creo un map y agrego tareas
		Map<Tarea, String> diccionarioPrueba = new HashMap<Tarea, String>();
		diccionarioPrueba.put(this.getTarea(), this.getTarea().verEstado());
		diccionarioPrueba.put(this.getTarea2(), this.getTarea2().verEstado());

		// agrego las tareas al proyecto
		this.getProyecto().agregarTarea(this.getTarea());
		this.getProyecto().agregarTarea(this.getTarea2());

		// ejecuto el metodo
		Map<Tarea, String> resultado = this.getProyecto()
				.obtenerTareasYEstados();

		// ?? debo chequear si las claves del resultado se encuentran
		// dentro de las claves del diccionario para testear

		// diccionario contiene las claves de resultado
		assertTrue(diccionarioPrueba.keySet().containsAll(resultado.keySet()));
		// resultado contiene las claves de diccionario
		assertTrue(resultado.keySet().containsAll(diccionarioPrueba.keySet()));

		// resultado contiene los valores de diccionario
		assertTrue(resultado.values().containsAll(diccionarioPrueba.values()));
		// diccionario contiene los valores de resultado
		assertTrue(diccionarioPrueba.values().containsAll(resultado.values()));

	}

	public void testObtenerMiembrosConRoles() {

		// creo un map y agrego miembro
		Map<Miembro, String> diccionarioPrueba = new HashMap<Miembro, String>();
		
		expect(miembro1.getRol()).andReturn("comprador");
		expect(miembro2.getRol()).andReturn("vendedor");
		replay(miembro1);
		replay(miembro2);
		
		diccionarioPrueba.put(this.getMiembro1(), this.getMiembro1().getRol());
		diccionarioPrueba.put(this.getMiembro2(), this.getMiembro2().getRol());

		// agrego los miembros al proyecto 
		this.getProyecto().getListaDeMiembros().add(getMiembro1());
		this.getProyecto().getListaDeMiembros().add(getMiembro2());
		Map<Miembro, String> resultado = this.getProyecto().obtenerMiembrosConRoles();

		assertTrue("el miembro 1 tiene el mismo rol", miembro1.getRol()== diccionarioPrueba.get(miembro1));
		assertTrue("el miembro 2 tiene el mismo rol", miembro2.getRol()== diccionarioPrueba.get(miembro2));
	}

	public void testReabrirUnaTarea() {
		//******* USO MOCK
		String comentario = "se reabre porque ahora ahi luz";
		
		tarea.reAbrite(comentario);
		expect(tarea.getEstado()).andReturn(Cerrada.GetInstance());
		
		replay(tarea);

		// envio el mensaje para reabrir la tarea lo que modificaria su estado
		//a iniciada
		this.getProyecto().reabrirUnaTarea(getTarea(), comentario);
		
	
		//verifico si se le cambio el estado de la tarea a iniciada
		assertSame(Cerrada.GetInstance(),getTarea().getEstado());
		assertEquals(getTarea().getDescripcion()+comentario, getTarea().getDescripcion());
	}

	public void testHsTotalesTrabajadas() {

		// agrego los miembros
		this.getProyecto().getListaDeMiembros().add(this.getMiembro1());
		this.getProyecto().getListaDeMiembros().add(this.getMiembro2());
		
		// utilizo el metodo hsTotalesTrabajadas
		//int resultadoReal = this.getProyecto().hsTotalesTrabajadas();
		
		// creo los mock como miembros 
		expect(miembro1.getHsTrabajadas()).andReturn(7);
		expect(miembro2.getHsTrabajadas()).andReturn(15);
		replay(miembro1,miembro2);
		
		int resultado = this.getProyecto().hsTotalesTrabajadas();
		verify(this.miembro1,this.miembro2);
		assertEquals(22,resultado);
	}

	public void testCerrarTarea() {

		// agrego 2 tareas//
		this.getProyecto().agregarTarea(this.getTarea());
		this.getProyecto().agregarTarea(this.getTarea2());

		// cierro una tarea especifica
		this.getProyecto().cerrarTarea(this.getTarea2());

		assertSame(Cerrada.GetInstance(), this.getTarea2().verEstado());

		System.out.println("test para cerrar una tarea pasada por parametro");
	}

	public void testCerrarProyecto() {

		// agrego 2 tareas al proyecto
		this.getProyecto().agregarTarea(this.getTarea());
		this.getProyecto().agregarTarea(this.getTarea2());

		// deberia modificarle a todas las tareas el estado actual a cerrada
		this.getProyecto().cerrarProyecto();

		// todas las tareas deben tener como estado cerrada
		assertEquals(Cerrada.GetInstance(), this.getTarea2().verEstado());
		assertEquals(Cerrada.GetInstance(), this.getTarea().verEstado());

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
		return tarea;
	}

	public void setTarea(TareaSimple tarea) {
		this.tarea = tarea;
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

}
