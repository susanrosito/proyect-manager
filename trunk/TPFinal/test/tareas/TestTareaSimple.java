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
	private Fecha fechaActual;

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

		// creo un ultimo mock fecha
		this.fechaActual = createMock(Fecha.class);
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
		expect(this.creada.verificarSiEstaCreada()).andReturn(true).times(2);
		replay(this.creada);

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
		expect(this.creada.toString()).andReturn("Creada").times(2);
		expect(this.iniciada.toString()).andReturn("Iniciada").times(1);
		expect(this.enTrabajo.toString()).andReturn("En Trabajo").times(1);
		expect(this.pausada.toString()).andReturn("Pausada").times(1);
		expect(this.finalizada.toString()).andReturn("Finalizada").times(1);
		expect(this.cerrada.toString()).andReturn("Cerrada").times(1);

		replay(this.creada, this.cerrada, this.enTrabajo, this.iniciada,
				this.pausada, this.finalizada);

		// realizo las acciones y verifico que el estado esa el esperado.
		Assert.assertEquals("el nombre no es Creada", nombreCreada,
				this.tareaSimple.verEstado());

		// realizo las acciones y verifico que el estado esa el esperado.
		Assert.assertEquals("el nombre no es Creada", nombreCreada,
				this.tareaSimpleConMiembro.verEstado());

		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.iniciada);

		// Verifico que el estado sea el esperado.
		Assert.assertEquals("el nombre no es Iniciada", nombreIniciada,
				this.tareaSimpleConMiembro.verEstado());
		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.enTrabajo);

		// Verifico que el estado sea el esperado.
		Assert.assertEquals("el nombre no es En Trabajo", nombreEnTrabajo,
				this.tareaSimpleConMiembro.verEstado());
		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.pausada);

		// Verifico que el estado sea el esperado.
		Assert.assertEquals("el nombre no es Pausada", nombrePausada,
				this.tareaSimpleConMiembro.verEstado());

		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.finalizada);

		// verifico que el valor que devuelve,con el que espero.
		Assert.assertEquals("el nombre no es Finalizada", nombreFinalizada,
				this.tareaSimpleConMiembro.verEstado());

		this.tareaSimpleConMiembro.setEstado(this.cerrada);

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

		// especifico los mensajes que podrian recibir los mock a probar.
		this.creada.cerrada(this.tareaSimple);
		expect(this.cerrada.verificarSiEstaCerrada()).andReturn(true).times(5);
		this.creada.cerrada(this.tareaSimpleConMiembro);
		this.iniciada.cerrada(this.tareaSimpleConMiembro);
		this.pausada.cerrada(this.tareaSimpleConMiembro);
		this.enTrabajo.cerrada(this.tareaSimpleConMiembro);

		replay(this.creada, this.cerrada, this.enTrabajo, this.pausada,
				this.iniciada);

		// realizo las acciones.
		this.tareaSimple.cerrate();

		// / seteo porque el metodo cerrate le asigna a tareaSimple una
		// instancia de Cerrada real y yo estoy trabajando con mock.
		this.tareaSimple.setEstado(this.cerrada);

		// Verifico que la tarea este cerrada.
		Assert.assertTrue("la tareaS con el estado Creada, no esta cerrada",
				this.tareaSimple.verificarSiEstaCerrada());
		this.tareaSimpleConMiembro.cerrate();

		// / seteo porque el metodo cerrate le asigna a tareaSimple una
		// instancia de Cerrada real y yo estoy trabajando con mock.
		this.tareaSimpleConMiembro.setEstado(this.cerrada);

		// Verifico que la tarea este cerrada.
		Assert.assertTrue("la tareaCM con el estado Creada, no esta cerrada",
				this.tareaSimpleConMiembro.verificarSiEstaCerrada());

		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.iniciada);
		this.tareaSimpleConMiembro.cerrate();

		// / seteo porque el metodo cerrate le asigna a tareaSimple una
		// instancia de Cerrada real y yo estoy trabajando con mock.
		this.tareaSimpleConMiembro.setEstado(this.cerrada);

		// Verifico que la tarea este cerrada.
		Assert.assertTrue("la tareaCM con el estado iniciada no esta cerrada",
				this.tareaSimpleConMiembro.verificarSiEstaCerrada());

		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.enTrabajo);
		this.tareaSimpleConMiembro.cerrate();

		// seteo porque el metodo cerrate le asigna a tareaSimple una
		// instancia de Cerrada real y yo estoy trabajando con mock.
		this.tareaSimpleConMiembro.setEstado(this.cerrada);

		// Verifico que la tarea este cerrada.
		Assert.assertTrue("la tareaCM con el estado enTrabajo no esta cerrada",
				this.tareaSimpleConMiembro.verificarSiEstaCerrada());

		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.pausada);
		this.tareaSimpleConMiembro.cerrate();

		// seteo porque el metodo cerrate le asigna a tareaSimple una
		// instancia de Cerrada real y yo estoy trabajando con mock.
		this.tareaSimpleConMiembro.setEstado(this.cerrada);

		// Verifico que la tarea este cerrada.
		Assert.assertTrue("la tareaCM con el estado pausada no esta cerrada",
				this.tareaSimpleConMiembro.verificarSiEstaCerrada());

		verify(this.creada, this.cerrada, this.enTrabajo, this.pausada,
				this.iniciada);
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
		expect(this.cerrada.verificarSiEstaCerrada()).andReturn(true).times(1);
		this.cerrada.iniciada(this.tareaSimple);
		expect(this.iniciada.verificarSiEstaIniciada()).andReturn(true)
				.times(1);

		replay(this.creada, this.cerrada, this.iniciada);

		// realizo las acciones necesarias.
		this.tareaSimple.cerrate();

		// seteo porque el metodo cerrate le asigna a tareaSimple una
		// instancia de Cerrada real y yo estoy trabajando con mock.
		this.tareaSimple.setEstado(this.cerrada);
		Assert.assertTrue("la tareaS con estado creada, no esta cerrada",
				this.tareaSimple.verificarSiEstaCerrada());
		this.tareaSimple.reAbrite(motivo);

		// seteo porque el metodo reAbrite le asigna a tareaSimple una
		// instancia de Iniciada real y yo estoy trabajando con mock.
		this.tareaSimple.setEstado(this.iniciada);
		Assert.assertTrue("la tareaS con estado Cerrada, no esta iniciada",
				this.tareaSimple.verificarSiEstaIniciada());

		// vefirico que los mock recibieron los mensajes que mencione antes.
		verify(this.creada, this.cerrada, this.iniciada);

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
			this.tareaSimpleConMiembro.modificarMiembroAsignado(this.miembro);

			// Compruebo si el miembro es igual al que eh asignado antes.
			Assert.assertEquals("el miembro no es el mismo", this.miembro,
					this.tareaSimpleConMiembro.getMiembroAsignado());

			// seteo el mock iniciada en la tarea, porq el metodo
			// modiciarMiembroAsignado
			// me cambia el estado por Iniciada real y estoy trabajando con
			// mock.
			this.tareaSimpleConMiembro.setEstado(this.iniciada);
			Assert.assertTrue("la tareaCM con estado creada, no esta iniciada",
					this.tareaSimpleConMiembro.verificarSiEstaIniciada());

			// verifico si los mensajes le llegaron a los mock creada y iniciada
			verify(this.creada, this.iniciada);
		} catch (NoPuedeCambiarseElEstadoExcepccion ex) {

		}

	}

	/**
	 * Test modificarMiembroAsignado para TareaSimple teniendo un miembro
	 * asignado
	 */
	public void testModificarMiembroAsignadoYaTeniendoUno() {

		// como ya vimos en el anterior test, si asignas por primera vez cambia
		// el estado.
		this.tareaSimpleConMiembro.modificarMiembroAsignado(this.miembro);
		this.tareaSimpleConMiembro.setEstado(this.iniciada);

		// ahora asignar la segunda vez.. no deberia de cambiar el estado y si
		// tendria que estar el nuevo miembro.
		this.tareaSimpleConMiembro.modificarMiembroAsignado(this.nuevoMiembro);

		// Verifico que el miembro ha sido modificado correctamente.
		Assert.assertEquals("El estado no fue el esperado", this.iniciada,
				this.tareaSimpleConMiembro.getEstado());
		Assert.assertEquals("Son distintos los miembros", this.nuevoMiembro,
				this.tareaSimpleConMiembro.getMiembroAsignado());
		Assert.assertNotSame("No cambio de miembro", this.miembro,
				this.tareaSimpleConMiembro.getMiembroAsignado());
	}

	/**
	 * Test finalizate para TareaSimple
	 */
	public void testFinalizate() {

		// especifico los mensajes que podrian recibir los mock a probar.
		this.creada.finalizada(this.tareaSimple);
		expect(this.finalizada.verificarSiEstaFinalizada()).andReturn(true)
				.times(5);
		this.creada.finalizada(this.tareaSimpleConMiembro);
		this.iniciada.finalizada(this.tareaSimpleConMiembro);
		this.enTrabajo.finalizada(this.tareaSimpleConMiembro);
		this.pausada.finalizada(this.tareaSimpleConMiembro);

		replay(this.creada, this.iniciada, this.enTrabajo, this.pausada,
				this.finalizada);

		// realizo las acciones necesarias.
		this.tareaSimple.finalizate();

		// seteo porque el metodo finalizate le asigna a tareaSimple una
		// instancia de Finalizada real y yo estoy trabajando con mock.
		this.tareaSimple.setEstado(this.finalizada);

		// Verifico que la tarea este finalizada.
		Assert.assertTrue("la tareaS con estado creada, no esta finalizada",
				this.tareaSimple.verificarSiEstaFinalizada());
		this.tareaSimpleConMiembro.finalizate();

		// seteo porque el metodo finalizate le asigna a tareaSimple una
		// instancia de Finalizada real y yo estoy trabajando con mock.
		this.tareaSimpleConMiembro.setEstado(this.finalizada);

		// Verifico que la tarea este finalizada.
		Assert.assertTrue("la tareaCM con estado creada, no esta finalizada",
				this.tareaSimpleConMiembro.verificarSiEstaFinalizada());

		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.iniciada);
		this.tareaSimpleConMiembro.finalizate();

		// seteo porque el metodo finalizate le asigna a tareaSimple una
		// instancia de Finalizada real y yo estoy trabajando con mock.
		this.tareaSimpleConMiembro.setEstado(this.finalizada);

		// Verifico que la tarea este finalizada.
		Assert.assertTrue("la tareaCM con estado iniciada, no esta finalizada",
				this.tareaSimpleConMiembro.verificarSiEstaFinalizada());

		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.enTrabajo);
		this.tareaSimpleConMiembro.finalizate();

		// seteo porque el metodo finalizate le asigna a tareaSimple una
		// instancia de Finalizada real y yo estoy trabajando con mock.
		this.tareaSimpleConMiembro.setEstado(this.finalizada);

		// Verifico que la tarea este finalizada.
		Assert.assertTrue(
				"la tareaCM con estado enTrabajo, no esta finalizada",
				this.tareaSimpleConMiembro.verificarSiEstaFinalizada());

		// seteo ya que necesito probar con este mock ahora.
		this.tareaSimpleConMiembro.setEstado(this.pausada);
		this.tareaSimpleConMiembro.finalizate();

		// seteo porque el metodo finalizate le asigna a tareaSimple una
		// instancia de Finalizada real y yo estoy trabajando con mock.
		this.tareaSimpleConMiembro.setEstado(this.finalizada);

		// Verifico que la tarea este finalizada.
		Assert.assertTrue("la tareaCM con estado pausada, no esta finalizada",
				this.tareaSimpleConMiembro.verificarSiEstaFinalizada());

		verify(this.creada, this.iniciada, this.enTrabajo, this.pausada,
				this.finalizada);
	}

	/**
	 * Test iniciate para TareaSimple
	 */
	public void testIniciateCuandoSePuede() {

		// utilizo el try catch, porque los metodos referidos a los estados de
		// una tarea
		// podrian tirar una expeccion entonces necesito que este dentro de try
		// ya que si no me marca error.
		try {

			// especifico los metodos que va a recibir los mock creada e
			// iniciada
			this.creada.iniciada(this.tareaSimple);
			expect(this.iniciada.verificarSiEstaIniciada()).andReturn(true);

			replay(this.creada, this.iniciada);

			// realizo las acciones necesarias!
			this.tareaSimple.iniciate();
			this.tareaSimple.setEstado(this.iniciada);

			// Verifico si se paso realmente al estado que queria!!(Iniciada)
			Assert.assertTrue("la tareaS con estado creada, no esta iniciada",
					this.tareaSimple.verificarSiEstaIniciada());

			verify(this.creada, this.iniciada);

		} catch (NoPuedeCambiarseElEstadoExcepccion e) {

		}

	}

	/**
	 * Test poneteEnTrabajo para TareaSimple
	 */
	public void testPoneteEnTrabajoCuandoPuede() {
		// utilizo el try catch, porque los metodos referidos a los estados de
		// una tarea
		// podrian tirar una expeccion entonces necesito que este dentro de try
		// ya que si no me marca error.
		try {
			// especifico los metodos que va a recibir los mock iniciada e
			// enTrabajo
			this.iniciada.enTrabajo(this.tareaSimple);
			expect(this.enTrabajo.verificarSiEstaEnTrabajo()).andReturn(true)
					.times(1);

			replay(this.iniciada, this.enTrabajo);
			// realizo las acciones necesarias!
			this.tareaSimple.setEstado(this.iniciada);
			this.tareaSimple.poneteEnTrabajo();
			this.tareaSimple.setEstado(this.enTrabajo);
			// Verifico si se paso realmente al estado que queria!!(enTrabajo)
			Assert.assertTrue(
					"la tareaS con estado iniciada, no esta EnTrabajo",
					this.tareaSimple.verificarSiEstaEnTrabajo());

			verify(this.iniciada, this.enTrabajo);

		} catch (NoPuedeCambiarseElEstadoExcepccion e) {

		}

	}

	/**
	 * Test pausateCuendoPuede para TareaSimple
	 */
	public void testPausateCuandoPuede() {
		// utilizo el try catch, porque los metodos referidos a los estados de
		// una tarea
		// podrian tirar una expeccion entonces necesito que este dentro de try
		// ya que si no me marca error.
		try {
			// especifico los metodos que va a recibir los mock enTrabajo e
			// pausada
			this.enTrabajo.pausada(this.tareaSimple);
			expect(this.pausada.verificarSiEstaPausada()).andReturn(true)
					.times(1);

			replay(this.enTrabajo, this.pausada);
			// realizo las acciones necesarias!
			this.tareaSimple.setEstado(this.enTrabajo);
			this.tareaSimple.pausate();
			this.tareaSimple.setEstado(this.pausada);
			// Verifico si se paso realmente al estado que queria!!(Pausada)
			Assert.assertTrue(
					"la tareaS con estado enTrabajo, no esta pausada",
					this.tareaSimple.verificarSiEstaPausada());

			verify(this.enTrabajo, this.pausada);

		} catch (NoPuedeCambiarseElEstadoExcepccion e) {

		}
	}

	/**
	 * Test verificarSiEstaPausada para TareaSimple
	 */
	public void testVerificarSiEstaPausada() {

		expect(this.pausada.verificarSiEstaPausada()).andReturn(true).times(1);

		replay(this.pausada);

		// realizo las acciones necesarias!
		this.tareaSimple.setEstado(this.pausada);
		// Verifico si la tarea se esta pausada
		Assert.assertTrue("la tarea no esta pausada", this.tareaSimple
				.verificarSiEstaPausada());

		verify(this.pausada);
	}

	/**
	 * Test verificarSiEstaEnTrabajo para TareaSimple
	 */
	public void testVerificarSiEstaEnTrabajo() {

		expect(this.enTrabajo.verificarSiEstaEnTrabajo()).andReturn(true)
				.times(1);

		replay(this.enTrabajo);
		// realizo las acciones necesarias!
		this.tareaSimple.setEstado(this.enTrabajo);
		// Verifico si la tarea esta enTrabajo
		Assert.assertTrue("la tarea no esta enTrabajo", this.tareaSimple
				.verificarSiEstaEnTrabajo());

		verify(this.enTrabajo);
	}

	/**
	 * Test verificarSiEstaIniciada para TareaSimple
	 */
	public void testVerificarSiEstaIniciada() {

		expect(this.iniciada.verificarSiEstaIniciada()).andReturn(true)
				.times(1);

		replay(this.iniciada);
		// realizo las acciones necesarias!
		this.tareaSimple.setEstado(this.iniciada);
		// Verifico si la tarea esta iniciada
		Assert.assertTrue("la tarea no esta iniciada", this.tareaSimple
				.verificarSiEstaIniciada());

		verify(this.iniciada);
	}

	/**
	 * Test verificarSiEstaCerrada para TareaSimple
	 */
	public void testVerificarSiEstaCreada() {

		expect(this.creada.verificarSiEstaCreada()).andReturn(true).times(1);

		replay(this.creada);
		// realizo las acciones necesarias!
		Assert.assertTrue("la tarea no esta creada", this.tareaSimple
				.verificarSiEstaCreada());

		verify(this.creada);
	}

	/**
	 * Test verificarSiEstaFinalizada para TareaSimple
	 */
	public void testVerificarSiEstaFinalizada() {

		expect(this.finalizada.verificarSiEstaFinalizada()).andReturn(true)
				.times(1);

		replay(this.finalizada);
		// realizo las acciones necesarias!
		this.tareaSimple.setEstado(this.finalizada);

		Assert.assertTrue("la tarea no esta finalizada", this.tareaSimple
				.verificarSiEstaFinalizada());

		verify(this.finalizada);
	}

	/**
	 * Test verificarSiEstaCerrada para TareaSimple
	 */
	public void testVerificarSiEstaCerrada() {

		expect(this.cerrada.verificarSiEstaCerrada()).andReturn(true).times(1);

		replay(this.cerrada);
		// realizo las acciones necesarias!
		this.tareaSimple.setEstado(this.cerrada);

		Assert.assertTrue("la tarea no esta cerrada", this.tareaSimple
				.verificarSiEstaCerrada());

		verify(this.cerrada);
	}

	/**
	 * Test estaEnTiempo para TareaSimple
	 */
	public void testEstaEnTiempo() {

		// expect(this.fechaActual.diasQFaltan(this.fechaTs)).andReturn(10);
		// expect(this.fechaActual.diasQFaltan(this.fechaTsCM)).andReturn(2);

		// replay(this.fechaActual);
		// Assert.assertTrue("",this.tareaSimple.estaEnTiempo());
		// Assert.assertTrue("",this.tareaSimpleConMiembro.estaEnTiempo());

		// verify(this.fechaActual);

	}

	/**
	 * Test proximaAVencer para TareaSimple
	 * 
	 */
	public void testProximaAVencer() {

	}

	/**
	 * Test ReAbriteCuendoEstaPausada para TareaSimple
	 */
	public void testReAbriteCuandoEstaPausada() {
		// utilizo el try catch, porque los metodos referidos a los estados de
		// una tarea
		// podrian tirar una expeccion entonces necesito que este dentro de try
		// ya que si no me marca error.
		try {
			String motivo = "Tiene que estar culminada para poder seguir trabajando";

			// especifico los metodos que va a recibir los mock pausada
			this.pausada.iniciada(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			expect(this.pausada.verificarSiEstaIniciada()).andReturn(false);
			replay(this.pausada);

			// realizo las acciones necesarias!
			this.tareaSimple.setEstado(this.pausada);
			this.tareaSimple.reAbrite(motivo);

			// Verifico si no esta iniciada y q la descripccion no cambie.
			Assert.assertFalse("la tarea esta iniciada", this.tareaSimple
					.verificarSiEstaIniciada());
			Assert.assertEquals("No coinciden las descripcion",
					this.descripcionTs, this.tareaSimple.getDescripcion());

			verify(this.pausada);
		} catch (Exception e) {
		}
	}

	/**
	 * Test ReAbriteCuandoEstaEnTrabajo para TareaSimple
	 */
	public void testReAbriteCuandoEstaEnTrabajo() {
		// utilizo el try catch, porque los metodos referidos a los estados de
		// una tarea
		// podrian tirar una expeccion entonces necesito que este dentro de try
		// ya que si no me marca error.
		try {
			String motivo = "Tiene que estar culminada para poder seguir trabajando";

			// especifico los metodos que va a recibir los mock enTrabajo
			this.enTrabajo.iniciada(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			expect(this.enTrabajo.verificarSiEstaIniciada()).andReturn(false);
			replay(this.enTrabajo);
			
			// realizo las acciones necesarias!
			this.tareaSimple.setEstado(this.enTrabajo);
			this.tareaSimple.reAbrite(motivo);
			
			// Verifico si no esta iniciada y q la descripccion no cambie.
			Assert.assertFalse("la tarea esta iniciada", this.tareaSimple
					.verificarSiEstaIniciada());
			Assert.assertEquals("No coinciden las descripcion",
					this.descripcionTs, this.tareaSimple.getDescripcion());

			verify(this.enTrabajo);
		} catch (Exception e) {
		}
	}

	/**
	 * Test ReAbriteCuandoEstaFinalizada para TareaSimple
	 */
	public void testReAbriteCuandoEstaFinalizada() {

		// utilizo el try catch, porque los metodos referidos a los estados de
		// una tarea
		// podrian tirar una expeccion entonces necesito que este dentro de try
		// ya que si no me marca error.
		try {
			String motivo = "Tiene que estar culminada para poder seguir trabajando";
			
			// especifico los metodos que va a recibir los mock finalizada
			this.finalizada.iniciada(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			expect(this.finalizada.verificarSiEstaIniciada()).andReturn(false);
			replay(this.finalizada);
			
			// realizo las acciones necesarias!
			this.tareaSimple.setEstado(this.finalizada);
			this.tareaSimple.reAbrite(motivo);
			
			// Verifico si no esta iniciada y q la descripccion no cambie.
			Assert.assertFalse("la tarea esta iniciada", this.tareaSimple
					.verificarSiEstaIniciada());
			Assert.assertEquals("No coinciden las descripcion",
					this.descripcionTs, this.tareaSimple.getDescripcion());

			verify(this.finalizada);
		} catch (Exception e) {
		}
	}

	/**
	 * Test ReAbriteCuandoEstaIniciada para TareaSimple
	 */
	public void testReAbriteCuandoEstaIniciada() {
		// utilizo el try catch, porque los metodos referidos a los estados de
		// una tarea
		// podrian tirar una expeccion entonces necesito que este dentro de try
		// ya que si no me marca error.
		try {
			String motivo = "Tiene que estar culminada para poder seguir trabajando";
			
			// especifico los metodos que va a recibir los mock iniciada
			this.iniciada.iniciada(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			expect(this.iniciada.verificarSiEstaIniciada()).andReturn(false);
			replay(this.iniciada);
			
			// realizo las acciones necesarias!
			this.tareaSimple.setEstado(this.iniciada);
			this.tareaSimple.reAbrite(motivo);
			
			// Verifico si no esta iniciada y q la descripccion no cambie.
			Assert.assertFalse("la tarea esta iniciada", this.tareaSimple
					.verificarSiEstaIniciada());
			Assert.assertEquals("No coinciden las descripcion",
					this.descripcionTs, this.tareaSimple.getDescripcion());

			verify(this.iniciada);
		} catch (Exception e) {
		}
	}

	/**
	 * Test iniciateCuandoNoSePuede para TareaSimple
	 */
	public void testIniciateCuandoNoSePuede() {
		// utilizo el try catch, porque los metodos referidos a los estados de
		// una tarea
		// podrian tirar una expeccion entonces necesito que este dentro de try
		// ya que si no me marca error.
		try {
			
			// especifico los metodos que va a recibir todos lo mock menos
			// creada
			this.iniciada.iniciada(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			this.iniciada.verificarSiEstaIniciada();
			this.enTrabajo.iniciada(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			this.enTrabajo.verificarSiEstaIniciada();
			this.pausada.iniciada(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			this.pausada.verificarSiEstaIniciada();
			this.finalizada.iniciada(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			this.finalizada.verificarSiEstaIniciada();
			replay(this.pausada, this.enTrabajo, this.iniciada, this.finalizada);
			
			// realizo las acciones necesarias!
			this.tareaSimple.setEstado(this.iniciada);
			this.tareaSimple.iniciate();
			
			// Verifico si no esta iniciada
			Assert.assertFalse("la tarea esta iniciada", this.tareaSimple
					.verificarSiEstaIniciada());
			this.tareaSimple.setEstado(this.enTrabajo);
			this.tareaSimple.iniciate();
			
			// Verifico si no esta iniciada
			Assert.assertFalse("la tarea esta iniciada", this.tareaSimple
					.verificarSiEstaIniciada());
			this.tareaSimple.setEstado(this.pausada);
			this.tareaSimple.iniciate();
			
			// Verifico si no esta iniciada
			Assert.assertFalse("la tarea esta iniciada", this.tareaSimple
					.verificarSiEstaIniciada());
			this.tareaSimple.setEstado(this.finalizada);
			this.tareaSimple.iniciate();
			
			// Verifico si no esta iniciada
			Assert.assertFalse("la tarea esta iniciada", this.tareaSimple
					.verificarSiEstaIniciada());
			verify(this.iniciada, this.enTrabajo, this.pausada, this.finalizada);

		} catch (Exception e) {

		}

	}

	/**
	 * Test pausateCuandoNoPuede para TareaSimple
	 */
	public void testPausateCuandoNoPuede() {
		// utilizo el try catch, porque los metodos referidos a los estados de
		// una tarea
		// podrian tirar una expeccion entonces necesito que este dentro de try
		// ya que si no me marca error.
		try {
			
			// especifico los metodos que va a recibir todos los mock menos
			// enTrabajo
			this.creada.pausada(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			this.creada.verificarSiEstaPausada();
			this.iniciada.pausada(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			this.iniciada.verificarSiEstaPausada();
			this.pausada.pausada(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			this.pausada.verificarSiEstaPausada();
			this.finalizada.pausada(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			this.finalizada.verificarSiEstaPausada();
			replay(this.pausada, this.creada, this.iniciada, this.finalizada);
			
			// realizo las acciones necesarias!
			this.tareaSimple.pausate();
			
			// Verifico si no esta pausada
			Assert.assertFalse("la tarea esta pausada", this.tareaSimple
					.verificarSiEstaPausada());
			this.tareaSimple.setEstado(this.iniciada);
			this.tareaSimple.pausate();
			
			// Verifico si no esta pausada
			Assert.assertFalse("la tarea esta pausada", this.tareaSimple
					.verificarSiEstaPausada());
			this.tareaSimple.setEstado(this.pausada);
			this.tareaSimple.pausate();
			
			// Verifico si no esta pausada
			Assert.assertFalse("la tarea esta pausada", this.tareaSimple
					.verificarSiEstaPausada());
			this.tareaSimple.setEstado(this.finalizada);
			this.tareaSimple.pausate();
			
			// Verifico si no esta pausada
			Assert.assertFalse("la tarea esta pausada", this.tareaSimple
					.verificarSiEstaPausada());
			verify(this.iniciada, this.creada, this.pausada, this.finalizada);
		} catch (Exception e) {

		}
	}

	/**
	 * Test PoneteEnTrabajoCuandoNoPuede para TareaSimple
	 */
	public void testPoneteEnTrabajoCuandoNoPuede() {
		// utilizo el try catch, porque los metodos referidos a los estados de
		// una tarea
		// podrian tirar una expeccion entonces necesito que este dentro de try
		// ya que si no me marca error.
		try {
			
			// especifico los metodos que va a recibir los mock creada enTrabajo
			// y finalizada
			this.creada.enTrabajo(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			this.creada.verificarSiEstaEnTrabajo();
			this.enTrabajo.enTrabajo(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			this.enTrabajo.verificarSiEstaEnTrabajo();
			this.finalizada.enTrabajo(this.tareaSimple);
			expectLastCall().andThrow(new NoPuedeCambiarseElEstadoExcepccion());
			this.finalizada.verificarSiEstaEnTrabajo();
			replay(this.enTrabajo, this.creada, this.finalizada);
			
			// realizo las acciones necesarias!
			this.tareaSimple.poneteEnTrabajo();
			
			// Verifico si no esta enTrabajo
			Assert.assertFalse("la tarea esta enTrabajo", this.tareaSimple
					.verificarSiEstaEnTrabajo());
			this.tareaSimple.setEstado(this.enTrabajo);
			this.tareaSimple.poneteEnTrabajo();
			
			// Verifico si no esta enTrabajo
			Assert.assertFalse("la tarea esta enTrabajo", this.tareaSimple
					.verificarSiEstaEnTrabajo());
			this.tareaSimple.setEstado(this.finalizada);
			this.tareaSimple.poneteEnTrabajo();
			
			// Verifico si no esta enTrabajo
			Assert.assertFalse("la tarea esta enTrabajo", this.tareaSimple
					.verificarSiEstaEnTrabajo());
			verify(this.enTrabajo, this.creada, this.finalizada);
		} catch (Exception e) {

		}
	}

}
