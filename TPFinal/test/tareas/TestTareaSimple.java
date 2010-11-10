package tareas;

import static org.easymock.EasyMock.*;
import junit.framework.Assert;
import junit.framework.TestCase;
import tareas.TareaSimple;
import usuarioMiembroYFecha.Miembro;
import usuarioMiembroYFecha.Usuario;
import usuarioMiembroYFecha.Fecha;
import estados.*;

//si tengo un if.. deberia de verificar si entra o no!!
// dividi en dos.. los test
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

		// cada estado es un Mock.
		this.creada = createMock(Creada.class);
		this.cerrada = createMock(Cerrada.class);
		this.iniciada = createMock(Iniciada.class);
		this.enTrabajo = createMock(EnTrabajo.class);
		this.pausada = createMock(Pausada.class);
		this.finalizada = createMock(Finalizada.class);

		// instancio una tareaSimple, con estos parametros.
		this.nombreTs = "Realizar Un Test";
		this.descripcionTs = "Para una clase especifica, en este caso la clase TareaSimple";

		// creo una fecha Mock.
		this.fechaTs = createMock(Fecha.class);

		// creo una tarea simple
		this.tareaSimple = new TareaSimple(this.nombreTs, this.descripcionTs,
				this.fechaTs);

		// seteo el mock creada.
		this.tareaSimple.setEstado(this.creada);

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
		// Esta vez le asigno un miembro.
		this.nombreTsCM = "Modificar Un Metodo";
		this.descripcionTsCM = "El metodo que va a ser modificado es getNombre()";

		// creo una fecha mock.
		this.fechaTsCM = createMock(Fecha.class);

		// creo otra tarea simple
		this.tareaSimpleConMiembro = new TareaSimple(this.nombreTsCM,
				this.descripcionTsCM, this.fechaTsCM);

		// seteo el mock creada.
		this.tareaSimpleConMiembro.setEstado(this.creada);

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
	 * Test del contructor de una tareaSimple. Verifica que el constructor anda.
	 */
	public void testConstructor() {

		// Verifico que el nombre de la tarea coincida, con el que se crea.
		Assert.assertEquals("El nombre no fue el esperado", this.nombreTs,
				this.tareaSimple.getNombre());

		// Verifico que la fecha de la tarea coincida, con la que se crea.
		Assert.assertEquals("No coinciden las Fechas", this.fechaTs,
				this.tareaSimple.getFechaEstimadaFinalizacion());

		// Verifico que la descripcion de la tarea coincida, con la que se crea.
		Assert.assertEquals("la descripcion no coincide", this.descripcionTs,
				this.tareaSimple.getDescripcion());

		// Verifica que la tarea tenga el estado que tiene que tener cuando
		// apenas se crea.
		// Por medio de mock lo logro. Expecifico que metodo va a recibir el
		// mock creada y que deberia devolver.
		expect(this.creada.verificarSiEstaCreada()).andReturn(true).times(4);
		replay(this.creada);

		// realizo las acciones,en las dos tareas.
		this.tareaSimple.verificarSiEstaCreada();
		this.tareaSimpleConMiembro.verificarSiEstaCreada();

		// me fijo si devolvio lo que que corresponde.
		Assert.assertTrue("La tarea no tiene el estado creada",
				this.tareaSimple.verificarSiEstaCreada());
		Assert.assertTrue("la tarea no tiene el estado creada",
				this.tareaSimpleConMiembro.verificarSiEstaCreada());

		// verifico que al mock le llego el metodo que mencione antes.
		verify(this.creada);
	}

	/**
	 * Test verEstado de una TareaSimple. Muestra que el metodo verEstado
	 * funciona bien, o como se espera.
	 */
	public void testVerEstado() {

		// declaro las variables, que son el valor deseado.
		String nombreCreada = this.creada.toString();
		String nombreIniciada = this.iniciada.toString();
		String nombreEnTrabajo = this.enTrabajo.toString();
		String nombrePausada = this.pausada.toString();
		String nombreCerrada = this.cerrada.toString();
		String nombreFinalizada = this.finalizada.toString();

		// especifico los mensajes que podrian recibir los mock a probar.
		expect(this.creada.toString()).andReturn("Creada").times(4);
		expect(this.iniciada.toString()).andReturn("Iniciada").times(4);
		expect(this.cerrada.toString()).andReturn("Cerrada").times(4);
		expect(this.enTrabajo.toString()).andReturn("En Trabajo").times(4);
		expect(this.finalizada.toString()).andReturn("Finalizada").times(4);
		expect(this.pausada.toString()).andReturn("Pausada").times(4);

		replay(this.creada, this.cerrada, this.enTrabajo, this.iniciada,
				this.pausada, this.finalizada);

		// realizo las acciones.
		this.tareaSimple.verEstado();
		// Verifico que el estado sea el esperado.
		Assert.assertEquals("el nombre no es Creada", nombreCreada,
				this.tareaSimple.verEstado());

		this.tareaSimpleConMiembro.verEstado();
		// Verifico que el estado sea el esperado.
		Assert.assertEquals("el nombre no es Creada", nombreCreada,
				this.tareaSimpleConMiembro.verEstado());
		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.iniciada);
		this.tareaSimpleConMiembro.verEstado();

		// Verifico que el estado sea el esperado.
		Assert.assertEquals("el nombre no es Iniciada", nombreIniciada,
				this.tareaSimpleConMiembro.verEstado());
		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.enTrabajo);
		this.tareaSimpleConMiembro.verEstado();

		// Verifico que el estado sea el esperado.
		Assert.assertEquals("el nombre no es En Trabajo", nombreEnTrabajo,
				this.tareaSimpleConMiembro.verEstado());
		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.pausada);
		this.tareaSimpleConMiembro.verEstado();

		// Verifico que el estado sea el esperado.
		Assert.assertEquals("el nombre no es Pausada", nombrePausada,
				this.tareaSimpleConMiembro.verEstado());

		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.finalizada);
		this.tareaSimpleConMiembro.verEstado();
		// verifico que el valor que devuelve,con el que espero.
		Assert.assertEquals("el nombre no es Finalizada", nombreFinalizada,
				this.tareaSimpleConMiembro.verEstado());

		this.tareaSimpleConMiembro.setEstado(this.cerrada);
		this.tareaSimpleConMiembro.verEstado();
		Assert.assertEquals("el nombre no es Cerrada", nombreCerrada,
				this.tareaSimpleConMiembro.verEstado());

		// vefirica si recibio el mensaje, las veces que indique.
		verify(this.creada, this.iniciada, this.pausada, this.cerrada,
				this.finalizada, this.enTrabajo);
	}

	/**
	 * Test cerrate para TareaSimple. Verifica que el metodo cerrate trabaja
	 * Perfecto.
	 */
	public void testCerrate() {
		// falta comentar!!! xD
		this.creada.cerrada(this.tareaSimple);
		expect(this.cerrada.verificarSiEstaCerrada()).andReturn(true).times(10);
		this.creada.cerrada(this.tareaSimpleConMiembro);
		this.iniciada.cerrada(this.tareaSimpleConMiembro);
		this.pausada.cerrada(this.tareaSimpleConMiembro);
		this.enTrabajo.cerrada(this.tareaSimpleConMiembro);

		replay(this.creada,this.cerrada,this.enTrabajo, this.pausada, this.iniciada);

		this.tareaSimple.cerrate();
		this.tareaSimple.setEstado(this.cerrada);
		this.tareaSimple.verificarSiEstaCerrada();
		Assert.assertTrue("", this.tareaSimple.verificarSiEstaCerrada());
		this.tareaSimpleConMiembro.cerrate();
		this.tareaSimpleConMiembro.setEstado(this.cerrada);
		this.tareaSimpleConMiembro.verificarSiEstaCerrada();
		Assert.assertTrue("", this.tareaSimpleConMiembro
				.verificarSiEstaCerrada());
		this.tareaSimpleConMiembro.setEstado(this.iniciada);
		this.tareaSimpleConMiembro.cerrate();
		this.tareaSimpleConMiembro.setEstado(this.cerrada);
		this.tareaSimpleConMiembro.verificarSiEstaCerrada();
		Assert.assertTrue("", this.tareaSimpleConMiembro
				.verificarSiEstaCerrada());
		this.tareaSimpleConMiembro.setEstado(this.enTrabajo);
		this.tareaSimpleConMiembro.cerrate();
		this.tareaSimpleConMiembro.setEstado(this.cerrada);
		this.tareaSimpleConMiembro.verificarSiEstaCerrada();
		Assert.assertTrue("", this.tareaSimpleConMiembro
				.verificarSiEstaCerrada());
		this.tareaSimpleConMiembro.setEstado(this.pausada);
		this.tareaSimpleConMiembro.cerrate();
		this.tareaSimpleConMiembro.setEstado(this.cerrada);
		this.tareaSimpleConMiembro.verificarSiEstaCerrada();
		Assert.assertTrue("", this.tareaSimpleConMiembro
				.verificarSiEstaCerrada());

		verify(this.creada,this.cerrada, this.enTrabajo, this.pausada, this.iniciada);
	}

	/**
	 * Test reAbrite para TareaSimple.
	 */
	public void testReAbriteCuandoEstaCerrada() {
		// La tarea solo se puede abrir cuando esta cerrada.
		// declaro el motivo de reabrir la tarea
		String motivo = "Reabro esta Tarea por que necesita ser finalizada";

		// especifico los mensajes que tendria que recibir los mock.
		this.creada.cerrada(this.tareaSimple);
		expect(this.cerrada.verificarSiEstaCerrada()).andReturn(true).times(2);
		this.cerrada.iniciada(this.tareaSimple);
		expect(this.iniciada.verificarSiEstaIniciada()).andReturn(true)
				.times(2);

		replay(this.creada, this.cerrada, this.iniciada);

		// realizo las acciones necesarias.
		this.tareaSimple.cerrate();

		// seteo porque el metodo cerrate le asigna a tareaSimple una
		// instancia de Cerrada real y yo estoy trabajando con mock.
		this.tareaSimple.setEstado(this.cerrada);
		this.tareaSimple.verificarSiEstaCerrada();
		Assert.assertTrue("",this.tareaSimple.verificarSiEstaCerrada());
		this.tareaSimple.reAbrite(motivo);

		// seteo porque el metodo reAbrite le asigna a tareaSimple una
		// instancia de Iniciada real y yo estoy trabajando con mock.
		this.tareaSimple.setEstado(this.iniciada);
		this.tareaSimple.verificarSiEstaIniciada();
		Assert.assertTrue("",this.tareaSimple.verificarSiEstaIniciada());
		// vefirico que los mock recibieron los mensajes que mencione antes.
		verify(this.creada, this.cerrada, this.iniciada);
		// faltan los demas casos.. cuando va a fallar!!!
	}

	
	public void testReAbriteCuandoEstaPausada() {
		String motivo = "Se necesita para poder terminar con otra tarea";
		
		try {
			this.pausada.iniciada(tareaSimple);
//			expect(this.pausada.verificarSiEstaIniciada()).andReturn(false).times(2);
						
			replay(this.pausada);
			
			this.tareaSimple.setEstado(this.pausada);
			this.tareaSimple.reAbrite(motivo);
			fail();
			verify(this.pausada);
		} catch (NoPuedeCambiarseElEstadoExcepccion e) {
//			Assert.assertTrue("", this.tareaSimple.verificarSiEstaIniciada());
		}
	}
	public void testReAbriteCuandoEstaEnTrabajo() {
		try {
			this.creada.iniciada(tareaSimple);
			expect(this.creada.verificarSiEstaIniciada()).andReturn(false).times(2);
			this.enTrabajo.iniciada(tareaSimple);
			expect(this.enTrabajo.verificarSiEstaIniciada()).andReturn(false).times(2);
			this.pausada.iniciada(tareaSimple);
			expect(this.pausada.verificarSiEstaIniciada()).andReturn(false).times(2);
			this.finalizada.iniciada(tareaSimple);
			expect(this.finalizada.verificarSiEstaIniciada()).andReturn(false).times(2);
			this.tareaSimple.reAbrite("susy");
			replay(this.creada,this.enTrabajo,this.pausada,this.finalizada);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void testReAbriteCuandoEstaFinalizada() {
		try {
			this.creada.iniciada(tareaSimple);
			expect(this.creada.verificarSiEstaIniciada()).andReturn(false).times(2);
			this.enTrabajo.iniciada(tareaSimple);
			expect(this.enTrabajo.verificarSiEstaIniciada()).andReturn(false).times(2);
			this.pausada.iniciada(tareaSimple);
			expect(this.pausada.verificarSiEstaIniciada()).andReturn(false).times(2);
			this.finalizada.iniciada(tareaSimple);
			expect(this.finalizada.verificarSiEstaIniciada()).andReturn(false).times(2);
			this.tareaSimple.reAbrite("susy");
			replay(this.creada,this.enTrabajo,this.pausada,this.finalizada);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void testReAbriteCuandoEstaIniciada() {
		try {
			this.creada.iniciada(tareaSimple);
			expect(this.creada.verificarSiEstaIniciada()).andReturn(false).times(2);
			this.enTrabajo.iniciada(tareaSimple);
			expect(this.enTrabajo.verificarSiEstaIniciada()).andReturn(false).times(2);
			this.pausada.iniciada(tareaSimple);
			expect(this.pausada.verificarSiEstaIniciada()).andReturn(false).times(2);
			this.finalizada.iniciada(tareaSimple);
			expect(this.finalizada.verificarSiEstaIniciada()).andReturn(false).times(2);
			this.tareaSimple.reAbrite("susy");
			replay(this.creada,this.enTrabajo,this.pausada,this.finalizada);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test modificarMiembroAsignado para TareaSimple, cuando asigno por primera
	 * vez.
	 */
	public void testmodificarMiembroAsignadoPorPrimeraVez() {

		try {// especifico los mensajes que tendria que recibir el mock creada
			this.creada.iniciada(this.tareaSimpleConMiembro);

			// especifico los mensajes que tendria que recibir el mock iniciada
			expect(this.iniciada.verificarSiEstaIniciada()).andReturn(true)
					.times(1);

			replay(this.creada, this.iniciada);
			// realizo las acciones necesarias.
			this.tareaSimpleConMiembro
					.modificarMiembroAsignado(this.miembro);
			// Compruebo si el miembro es igual al que eh asignado antes.
			Assert.assertEquals("el miembro no es el mismo", this.miembro,
					this.tareaSimpleConMiembro.getMiembroAsignado());
			
			// seteo el mock iniciada en la tarea, porq el metodo
			// modiciarMiembroAsignado
			// me cambia el estado por Iniciada real y estoy trabajando con
			// mock.
			this.tareaSimpleConMiembro.setEstado(this.iniciada);
			Assert.assertTrue("", this.tareaSimpleConMiembro.verificarSiEstaIniciada());
			// verifico si los mensajes le llegaron a los mock creada y iniciada
			verify(this.creada, this.iniciada);
		} catch (NoPuedeCambiarseElEstadoExcepccion ex) {
		
		}

	}
	/**
	 * Test modificarMiembroAsignado para TareaSimple teniendo un miembro asignado
	 */
	public void testModificarMiembroAsignadoYaTeniendoUno() {
		// como ya vimos en el anterior test, si asignas por primera vez cambia el estado.
		this.tareaSimpleConMiembro.modificarMiembroAsignado(this.miembro);
		this.tareaSimpleConMiembro.setEstado(this.iniciada);
		// ahora asignar la segunda vez.. no deberia de cambiar el estado y si tendria que estar el nuevo miembro.
		this.tareaSimpleConMiembro.modificarMiembroAsignado(this.nuevoMiembro);
		
		Assert.assertEquals("",this.iniciada, this.tareaSimpleConMiembro.getEstado());
		Assert.assertEquals("",this.nuevoMiembro, this.tareaSimpleConMiembro.getMiembroAsignado());
		Assert.assertNotSame(this.miembro, this.tareaSimpleConMiembro.getMiembroAsignado());
	}

	/**
	 * Test finalizate para TareaSimple
	 */
	public void testFinalizate() {

		this.creada.finalizada(this.tareaSimple);
		this.creada.finalizada(this.tareaSimpleConMiembro);
		this.iniciada.finalizada(this.tareaSimpleConMiembro);
		this.enTrabajo.finalizada(this.tareaSimpleConMiembro);
		this.pausada.finalizada(this.tareaSimpleConMiembro);
		replay(this.creada, this.iniciada, this.enTrabajo, this.pausada);

		this.tareaSimple.finalizate();
		this.tareaSimpleConMiembro.finalizate();
		this.tareaSimpleConMiembro.setEstado(this.iniciada);
		this.tareaSimpleConMiembro.finalizate();
		this.tareaSimpleConMiembro.setEstado(this.enTrabajo);
		this.tareaSimpleConMiembro.finalizate();
		this.tareaSimpleConMiembro.setEstado(this.pausada);
		this.tareaSimpleConMiembro.finalizate();

		// Assert.assertSame("no coinciden",this.fina,this.tareaSimple.getEstado());

		// Assert.assertSame(this.fina,this.tareaSimpleConMiembro.getEstado());

		// Assert.assertSame(this.fina,this.tareaSimpleConMiembro.getEstado());

		verify(this.creada, this.iniciada, this.enTrabajo, this.pausada);
		// Assert.assertSame(this.fina,this.tareaSimpleConMiembro.getEstado());
	}

	/**
	 * Test iniciate para TareaSimple
	 */
	public void testIniciateCuandoSePuede() {
		/*
		 * try { this.getEstado().enTrabajo(TSConEstadoQueNoCambia);
		 * fail("No exception caught :("); } catch (Exception
		 * NoPuedeCambiarseElEstadoExcepccion) { //
		 * assertEquals(this.getTSConEstadoQueNoCambia().getEstado().,
		 * NoPuedeCambiarseElEstadoExcepccion.getCause().getClass());
		 * //assertEquals
		 * ("Message",NoPuedeCambiarseElEstadoExcepccion.getMessage()); }
		 */

		try {
			this.creada.iniciada(this.tareaSimple);
			replay(this.creada);
			this.tareaSimple.iniciate();
			verify(this.creada);
			// Assert.assertSame(this.tareaSimple.getEstado(), this.iniciada);
		} catch (NoPuedeCambiarseElEstadoExcepccion e) {
			e.printStackTrace();
		}
		/*
		 * // este me parece que no va
		 * this.iniciada.iniciada(this.tareaSimpleConMiembro);
		 * replay(this.iniciada); this.tareaSimpleConMiembro.iniciate();
		 * verify(this.iniciada);
		 * 
		 * //
		 * Assert.assertSame(this.tareaSimpleConMiembro.getEstado(),this.iniciada
		 * ); // no va!! this.tareaSimpleConMiembro.setEstado(this.enTrabajo);
		 * this.enTrabajo.iniciada(this.tareaSimpleConMiembro);
		 * replay(this.enTrabajo); this.tareaSimpleConMiembro.iniciate();
		 * verify(this.enTrabajo); //
		 * Assert.assertSame(this.tareaSimpleConMiembro
		 * .getEstado(),this.enTrabajo); // no va
		 * this.tareaSimpleConMiembro.setEstado(this.pausada);
		 * this.pausada.iniciada(this.tareaSimpleConMiembro);
		 * replay(this.pausada); this.tareaSimpleConMiembro.iniciate();
		 * verify(this.pausada); //
		 * Assert.assertSame(this.tareaSimpleConMiembro.getEstado(), //
		 * this.pausada); // no se si hace falta probar con finalizada
		 * this.tareaSimpleConMiembro.setEstado(this.finalizada);
		 * this.finalizada.iniciada(this.tareaSimpleConMiembro);
		 * replay(this.finalizada); this.tareaSimpleConMiembro.iniciate();
		 * verify(this.finalizada); //
		 * Assert.assertSame(this.tareaSimpleConMiembro
		 * .getEstado(),this.finalizada);
		 */
	}
	public void testIniciateCuandoNoSePuede(){
		
	}
	/**
	 * Test poneteEnTrabajo para TareaSimple
	 */
	public void testPoneteEnTrabajoCuandoPuede() {
		/*
		 * this.creada.enTrabajo(this.tareaSimple); replay(this.creada);
		 * this.tareaSimple.poneteEnTrabajo(); verify(this.creada); //
		 * Assert.assertSame(this.tareaSimple.getEstado(), this.finalizada);
		 * this.iniciada.enTrabajo(this.tareaSimpleConMiembro);
		 * replay(this.iniciada); this.tareaSimpleConMiembro.poneteEnTrabajo();
		 * verify(this.iniciada); //
		 * Assert.assertSame(this.tareaSimpleConMiembro
		 * .getEstado(),this.finalizada);
		 * 
		 * this.tareaSimpleConMiembro.setEstado(this.enTrabajo);
		 * this.enTrabajo.enTrabajo(this.tareaSimpleConMiembro);
		 * replay(this.enTrabajo); this.tareaSimpleConMiembro.poneteEnTrabajo();
		 * verify(this.enTrabajo); //
		 * Assert.assertSame(this.tareaSimpleConMiembro
		 * .getEstado(),this.finalizada);
		 * 
		 * this.tareaSimpleConMiembro.setEstado(this.pausada);
		 * this.pausada.enTrabajo(this.tareaSimpleConMiembro);
		 * replay(this.pausada); this.tareaSimpleConMiembro.poneteEnTrabajo();
		 * verify(this.pausada);
		 */// Assert.assertSame(this.tareaSimpleConMiembro.getEstado(),this.finalizada);
	}

	public void testPoneteEnTrabajoCuandoNoPuede() {

	}

	
	public void testPausateCuandoPuede() {

	}

	public void testPausateCuandoNoPuede() {

	}

	public void testVerificarSiEstaPausada() {
		expect(this.pausada.verificarSiEstaPausada()).andReturn(true).times(1);
		replay(this.pausada);
		this.tareaSimple.setEstado(this.pausada);
		Assert.assertTrue("", this.tareaSimple.verificarSiEstaPausada());
		verify(this.pausada);
	}

	public void testVerificarSiEstaEnTrabajo() {
		expect(this.enTrabajo.verificarSiEstaEnTrabajo()).andReturn(true).times(1);
		replay(this.enTrabajo);
		this.tareaSimple.setEstado(this.enTrabajo);
		Assert.assertTrue("", this.tareaSimple.verificarSiEstaEnTrabajo());
		verify(this.enTrabajo);
	}

	public void testVerificarSiEstaIniciada() {
		expect(this.iniciada.verificarSiEstaIniciada()).andReturn(true).times(1);
		replay(this.iniciada);
		this.tareaSimple.setEstado(this.iniciada);
		Assert.assertTrue("", this.tareaSimple.verificarSiEstaIniciada());
		verify(this.iniciada);
	}

	public void testVerificarSiEstaCreada() {
		expect(this.creada.verificarSiEstaCreada()).andReturn(true).times(1);
		replay(this.creada);
		Assert.assertTrue("", this.tareaSimple.verificarSiEstaCreada());
		verify(this.creada);
	}

	public void testVerificarSiEstaFinalizada() {
		expect(this.finalizada.verificarSiEstaFinalizada()).andReturn(true).times(1);
		replay(this.finalizada);
		this.tareaSimple.setEstado(this.finalizada);
		Assert.assertTrue("", this.tareaSimple.verificarSiEstaFinalizada());
		verify(this.finalizada);
	}

	public void testVerificarSiEstaCerrada() {
		expect(this.cerrada.verificarSiEstaCerrada()).andReturn(true).times(1);
		replay(this.cerrada);
		this.tareaSimple.setEstado(this.cerrada);
		Assert.assertTrue("", this.tareaSimple.verificarSiEstaCerrada());
		verify(this.cerrada);
	}

}
