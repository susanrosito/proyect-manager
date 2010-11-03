package tareas;

import static org.easymock.EasyMock.*;
import junit.framework.Assert;
import junit.framework.TestCase;
import tareas.TareaSimple;
import usuarioMiembroYFecha.Miembro;
import usuarioMiembroYFecha.Usuario;
import usuarioMiembroYFecha.Fecha;
import estados.*;

public class TestTareaSimple extends TestCase {
	private TareaSimple tareaSimple;
	private String nombreTs;
	private String descripcionTs;
	private Fecha fechaTs;
	private TareaSimple tareaSimpleConMiembro;
	private String nombreTsCM;
	private String descripcionTsCM;
	private Fecha fechaTsCM;
	private Estado creada;
	private Cerrada cerrada;
	private Iniciada iniciada;
	private EnTrabajo enTrabajo;
	private Pausada pausada;
	private Finalizada finalizada;
	private String nombreUsuario;
	private String emailUsuario;
	private Usuario usuario;
	private Miembro miembro;
	private String rol;
	private Usuario nuevoUsuario;
	private String nombreNuevoUsuario;
	private String emailNuevoUsuario;
	private Miembro nuevoMiembro;
	private String nuevoRol;

	protected void setUp() throws Exception {
		super.setUp();

	// instancio una tareaSimple, con estos parametros.

		this.nombreTs = "Realizar Un Test";
		this.descripcionTs = "Para una clase especifica, en este caso la clase TareaSimple";
		this.fechaTs = new Fecha("20101012");
		this.tareaSimple = new TareaSimple(this.nombreTs, this.descripcionTs,
				this.fechaTs);

		// cada estado es un Mock.

		this.creada = createMock(Creada.class);
		this.tareaSimple.setEstado(this.creada);
		this.cerrada = createMock(Cerrada.class);
		this.iniciada = createMock(Iniciada.class);
		this.enTrabajo = createMock(EnTrabajo.class);
		this.pausada = createMock(Pausada.class);
		this.finalizada = createMock(Finalizada.class);

		// cree un usuario mock

		this.usuario = createMock(Usuario.class);
		this.nombreUsuario = "Rodrigo";
		this.emailUsuario = "rodrigo@gmail.com";

		// verifico que si le mando estos mensajes al usuario tiene que retornar
		// esos valores

		expect(usuario.getNombre()).andReturn(this.nombreUsuario);
		expect(usuario.getEmail()).andReturn(this.emailUsuario);

		// declaro roles.

		this.rol = "Tester";
		this.nuevoRol = "Desarrollador";

		// cree un miembro Mock. 

		this.miembro = createMock(Miembro.class);

		// verifico que si le mando estos mensajes al miembro va a retornar esos
		// valores.

		expect(miembro.getUsuario()).andReturn(this.usuario);
		expect(miembro.getRol()).andReturn(this.rol);

		// instancio otra TareaSimple, con esos parametros.

		this.nombreTsCM = "Modificar Un Metodo";
		this.descripcionTsCM = "El metodo que va a ser modificado es getNombre()";
		this.fechaTsCM = new Fecha("20101123");
		this.tareaSimpleConMiembro = new TareaSimple(this.nombreTsCM,
				this.descripcionTsCM, this.fechaTsCM);

		// aca le seteo el miembro. (estoy en duda de como lo estoy haciendo,
		// porque pareciera que yo estoy
		// seteandolo para que funcione todo, pero en realidad, deberia de
		// mandarle el mensaje no? )

		//this.tareaSimpleConMiembro.setMiembroAsignado(this.miembro);

		// le seteo el estado, ya que cuando uno asigna un Miembro, tiene que
		// pasar a iniciada.

		//this.tareaSimpleConMiembro.setEstado(iniciada);

		// cree otro usuario mock -- lo cree por el metodo
		// modificarMiembroAsignado

		this.nuevoUsuario = createMock(Usuario.class);
		this.nombreNuevoUsuario = "Tereza";
		this.emailNuevoUsuario = "teresita@gmail.com";

		// verifico que los mensajes que le mando, retornen estos valores

		expect(nuevoUsuario.getNombre()).andReturn(this.nombreNuevoUsuario);
		expect(nuevoUsuario.getEmail()).andReturn(this.emailNuevoUsuario);

		// cree otro Miembro Mock -- lo cree por la misma razon que el usuario

		this.nuevoMiembro = createMock(Miembro.class);
		expect(miembro.getUsuario()).andReturn(this.nuevoUsuario);
		expect(miembro.getRol()).andReturn(this.nuevoRol);

	}

	/**
	 * Test del contructor de una tareaSimple. Verifica que el constructor anda
	 * de 10!! xD
	 */
	public void testConstructor() {
		
		Assert.assertEquals("El nombre no fue el esperado", this.nombreTs,
				this.tareaSimple.getNombre());
		Assert.assertEquals("No coinciden las Fechas", this.fechaTs,
				this.tareaSimple.getFechaEstimadaFinalizacion());
		Assert.assertEquals("la descripcion no coincide", this.descripcionTs,
				this.tareaSimple.getDescripcion());
		expect(this.creada.verificarSiEstaCreada()).andReturn(true).anyTimes();
		replay(this.creada);
		this.tareaSimple.verificarSiEstaCreada();
		Assert.assertTrue("",this.tareaSimple.verificarSiEstaCreada());
		verify(this.creada);
	}

	/**
	 * Test verEstado de una TareaSimple. Muestra que el metodo verEstado
	 * funciona bien, o como se espera
	 */
	public void testVerEstado() {
		String nombreIniciada = "Iniciada";
		String nombreCreada = "Creada";
		Assert.assertEquals("el estado no es creada",nombreCreada,
				this.tareaSimple.verEstado());
		Assert.assertEquals("el estado no es iniciada",nombreIniciada,
				this.tareaSimpleConMiembro.verEstado());
	}

	/**
	 * Test cerrate para TareaSimple. Verifica que el metodo cerrate trabaja
	 * Perfecto
	 */
	public void testCerrate() {
		this.creada.cerrada(this.tareaSimple);
		replay(this.creada);
		this.tareaSimple.cerrate();
		verify(this.creada);
		iniciada.cerrada(this.tareaSimpleConMiembro);
		replay(this.iniciada);
		this.tareaSimpleConMiembro.cerrate();
		verify(this.iniciada);
	}

	/**
	 * Test reAbrite para TareaSimple
	 */
	public void testReAbrite() {
		String motivo = "Reabro esta Tarea por que necesita ser finalizada";
		this.creada.cerrada(this.tareaSimple);
		expect(this.cerrada.verificarSiEstaCerrada()).andReturn(true).anyTimes();
		this.cerrada.iniciada(this.tareaSimple);
		expect(this.iniciada.verificarSiEstaIniciada()).andReturn(true).anyTimes();
		replay(this.creada,this.cerrada,this.iniciada);
		this.tareaSimple.cerrate();
		this.tareaSimple.setEstado(this.cerrada);
		this.tareaSimple.verificarSiEstaCerrada();
		this.tareaSimple.reAbrite(motivo);
		this.tareaSimple.setEstado(this.iniciada);
		this.tareaSimple.verificarSiEstaIniciada();
		verify(this.creada,this.cerrada,this.iniciada);
	}
	/**
	 * Test modificarMiembroAsignado para TareaSimple
	 */
	public void testmodificarMiembroAsignado() {

		this.tareaSimpleConMiembro.modificarMiembroAsignado(this.nuevoMiembro);
		Assert.assertSame("el miembro no es el mismo", this.nuevoMiembro,
				this.tareaSimpleConMiembro.getMiembroAsignado());
		expect(this.iniciada.verificarSiEstaCreada()).andReturn(true);
		replay(this.creada);
		this.tareaSimple.verificarSiEstaCreada();
		verify(this.creada);
	}

	/**
	 * Test finalizate para TareaSimple
	 */
	public void testFinalizate() {
		/*
		 try {
		        this.getEstado().enTrabajo(TSConEstadoQueNoCambia);	
		        fail("No exception caught :(");
		    }
		    catch (Exception NoPuedeCambiarseElEstadoExcepccion) {
		       // assertEquals(this.getTSConEstadoQueNoCambia().getEstado()., NoPuedeCambiarseElEstadoExcepccion.getCause().getClass());
		        //assertEquals("Message",NoPuedeCambiarseElEstadoExcepccion.getMessage());
		    }*/
		this.creada.finalizada(this.tareaSimple);
		replay(this.creada);
		this.tareaSimple.finalizate();
		verify(this.creada);
		// Assert.assertSame("no coinciden",this.fina,this.tareaSimple.getEstado());
		this.iniciada.finalizada(this.tareaSimpleConMiembro);
		replay(this.iniciada);
		this.tareaSimpleConMiembro.finalizate();
		verify(this.iniciada);
		// Assert.assertSame(this.fina,this.tareaSimpleConMiembro.getEstado());

		this.tareaSimpleConMiembro.setEstado(this.enTrabajo);
		this.enTrabajo.finalizada(this.tareaSimpleConMiembro);
		replay(this.enTrabajo);
		this.tareaSimpleConMiembro.finalizate();
		verify(this.enTrabajo);
		// Assert.assertSame(this.fina,this.tareaSimpleConMiembro.getEstado());

		this.tareaSimpleConMiembro.setEstado(this.pausada);
		this.pausada.finalizada(this.tareaSimpleConMiembro);
		replay(this.pausada);
		this.tareaSimpleConMiembro.finalizate();
		verify(this.pausada);
		// Assert.assertSame(this.fina,this.tareaSimpleConMiembro.getEstado());
	}

	/**
	 * Test iniciate para TareaSimple
	 */
	public void testIniciate() {
		/*
		 try {
		        this.getEstado().enTrabajo(TSConEstadoQueNoCambia);	
		        fail("No exception caught :(");
		    }
		    catch (Exception NoPuedeCambiarseElEstadoExcepccion) {
		       // assertEquals(this.getTSConEstadoQueNoCambia().getEstado()., NoPuedeCambiarseElEstadoExcepccion.getCause().getClass());
		        //assertEquals("Message",NoPuedeCambiarseElEstadoExcepccion.getMessage());
		    }*/
		
		try {
			this.creada.iniciada(this.tareaSimple);
			replay(this.creada);
			this.tareaSimple.iniciate();
			verify(this.creada);
			//Assert.assertSame(this.tareaSimple.getEstado(), this.iniciada);
			fail("fallo");
		} catch (NoPuedeCambiarseElEstadoExcepccion e) {
			e.printStackTrace();
		}
		// este me parece que no va
		this.iniciada.iniciada(this.tareaSimpleConMiembro);
		replay(this.iniciada);
		this.tareaSimpleConMiembro.iniciate();
		verify(this.iniciada);

		// Assert.assertSame(this.tareaSimpleConMiembro.getEstado(),this.iniciada);
		// no va!!
		this.tareaSimpleConMiembro.setEstado(this.enTrabajo);
		this.enTrabajo.iniciada(this.tareaSimpleConMiembro);
		replay(this.enTrabajo);
		this.tareaSimpleConMiembro.iniciate();
		verify(this.enTrabajo);
		// Assert.assertSame(this.tareaSimpleConMiembro.getEstado(),this.enTrabajo);
		// no va
		this.tareaSimpleConMiembro.setEstado(this.pausada);
		this.pausada.iniciada(this.tareaSimpleConMiembro);
		replay(this.pausada);
		this.tareaSimpleConMiembro.iniciate();
		verify(this.pausada);
		// Assert.assertSame(this.tareaSimpleConMiembro.getEstado(),
		// this.pausada);
		// no se si hace falta probar con finalizada
		this.tareaSimpleConMiembro.setEstado(this.finalizada);
		this.finalizada.iniciada(this.tareaSimpleConMiembro);
		replay(this.finalizada);
		this.tareaSimpleConMiembro.iniciate();
		verify(this.finalizada);
		// Assert.assertSame(this.tareaSimpleConMiembro.getEstado(),this.finalizada);

	}

	/**
	 * Test poneteEnTrabajo para TareaSimple
	 */
	public void testPoneteEnTrabajo() {
		this.creada.enTrabajo(this.tareaSimple);
		replay(this.creada);
		this.tareaSimple.poneteEnTrabajo();
		verify(this.creada);
		// Assert.assertSame(this.tareaSimple.getEstado(), this.finalizada);
		this.iniciada.enTrabajo(this.tareaSimpleConMiembro);
		replay(this.iniciada);
		this.tareaSimpleConMiembro.poneteEnTrabajo();
		verify(this.iniciada);
		// Assert.assertSame(this.tareaSimpleConMiembro.getEstado(),this.finalizada);

		this.tareaSimpleConMiembro.setEstado(this.enTrabajo);
		this.enTrabajo.enTrabajo(this.tareaSimpleConMiembro);
		replay(this.enTrabajo);
		this.tareaSimpleConMiembro.poneteEnTrabajo();
		verify(this.enTrabajo);
		// Assert.assertSame(this.tareaSimpleConMiembro.getEstado(),this.finalizada);

		this.tareaSimpleConMiembro.setEstado(this.pausada);
		this.pausada.enTrabajo(this.tareaSimpleConMiembro);
		replay(this.pausada);
		this.tareaSimpleConMiembro.poneteEnTrabajo();
		verify(this.pausada);
		// Assert.assertSame(this.tareaSimpleConMiembro.getEstado(),this.finalizada);
	}

	/**
	 * Test modificarMiembroAsignado para TareaSimple
	 */
	public void testPausate() {

	}

}
