package tareas;

import static org.easymock.EasyMock.*;
import junit.framework.Assert;
import junit.framework.TestCase;
import tareas.TareaSimple;
import usuarioMiembroYFecha.Miembro;
import usuarioMiembroYFecha.Usuario;
import usuarioMiembroYFecha.Fecha;
import estados.*;

// los metodos de los estados!!! tendria que dividirlos en 2!! Los que anda todo bonito
// y los que no andan!! osea fallan!! asi es mejor!!
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
		this.fechaTs = new Fecha("20101012");
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
		this.fechaTsCM = new Fecha("20101123");
		this.tareaSimpleConMiembro = new TareaSimple(this.nombreTsCM,
				this.descripcionTsCM, this.fechaTsCM);

		// seteo el mock creada.
		this.tareaSimpleConMiembro.setEstado(this.creada);

		// this.tareaSimpleConMiembro.modificarMiembroAsignado(this.miembro);

		// le seteo el estado, ya que cuando se asigna un Miembro a una tarea,
		// tiene que
		// pasar a iniciada.
		// this.tareaSimpleConMiembro.setEstado(this.iniciada);

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
		// mock creada y
		// que deberia devolver.
		expect(this.creada.verificarSiEstaCreada()).andReturn(true).times(2);
		replay(this.creada);

		// realizo la accion.
		this.tareaSimple.verificarSiEstaCreada();

		// me fijo si devolvio lo que que corresponde.
		Assert.assertTrue("", this.tareaSimple.verificarSiEstaCreada());

		// verifico que al mock le llego el metodo que mencione antes.
		verify(this.creada);
	}

	/**
	 * Test verEstado de una TareaSimple. Muestra que el metodo verEstado
	 * funciona bien, o como se espera.
	 */
	public void testVerEstado() {

		// declaro una variable, que es el valor deseado.
		String nombreCreada = this.creada.toString();

		// especifico el mensaje que podria recibir el mock a probar.
		expect(this.creada.toString()).andReturn("Creada").times(4);
		replay(this.creada);

		// realizo las acciones.
		this.tareaSimple.verEstado();
		this.tareaSimpleConMiembro.verEstado();

		// verifico que el valor que devuelve,con el que espero.
		Assert.assertEquals("el nombre no es creada", nombreCreada,
				this.tareaSimple.verEstado());
		Assert.assertEquals("el nombre no es creada", nombreCreada,
				this.tareaSimpleConMiembro.verEstado());

		// vefirica si recibio el mensaje, las veces que indique.
		verify(this.creada);
	}

	/**
	 * Test cerrate para TareaSimple. Verifica que el metodo cerrate trabaja
	 * Perfecto.
	 */
	public void testCerrate() {
		// creo que me falta comentar todos los test y!!
		// para cada estado.. ver si hace lo que tiene que hacer
		// agregar assert!! para comprobar!!
		this.creada.cerrada(this.tareaSimple);
		this.creada.cerrada(this.tareaSimpleConMiembro);
		replay(this.creada);
		this.tareaSimple.cerrate();
		this.tareaSimpleConMiembro.cerrate();
		verify(this.creada);
	}

	/**
	 * Test reAbrite para TareaSimple.
	 */
	public void testReAbriteCuandoSePuede() {

		// declaro el motivo de reabrir la tarea
		String motivo = "Reabro esta Tarea por que necesita ser finalizada";

		// especifico los mensajes que tendria que recibir el mock creada.
		this.creada.cerrada(this.tareaSimple);
		expect(this.cerrada.verificarSiEstaCerrada()).andReturn(true)
				.anyTimes();

		// especifico los mensajes que tendria que recibir el mock cerrada.
		this.cerrada.iniciada(this.tareaSimple);
		expect(this.iniciada.verificarSiEstaIniciada()).andReturn(true)
				.anyTimes();

		replay(this.creada, this.cerrada, this.iniciada);

		// realizo las acciones necesarias.
		this.tareaSimple.cerrate();

		// seteo porque el metodo cerrate le asigna a tareaSimple una
		// instancia de Cerrada real y yo estoy trabajando con mock.
		this.tareaSimple.setEstado(this.cerrada);
		this.tareaSimple.verificarSiEstaCerrada();
		this.tareaSimple.reAbrite(motivo);

		// seteo porque el metodo reAbrite le asigna a tareaSimple una
		// instancia de Iniciada real y yo estoy trabajando con mock.
		this.tareaSimple.setEstado(this.iniciada);
		this.tareaSimple.verificarSiEstaIniciada();

		// vefirico que los mock recibieron los mensajes que mencione antes.
		verify(this.creada, this.cerrada, this.iniciada);
		// faltan los demas casos.. cuando va a fallar!!!
	}

	public void testReAbriteCuandoNoSePuede() {
		// no se si por cada estado o por todo en el mismo..
		// creo que si no se me hace lio lo puedo hacer en el mismo:P
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
					.anyTimes();

			replay(this.creada, this.iniciada);
			// realizo las acciones necesarias.
			this.tareaSimpleConMiembro
					.modificarMiembroAsignado(this.nuevoMiembro);
			// Compruebo si el miembro es igual al que eh asignado antes.
			Assert.assertEquals("el miembro no es el mismo", this.nuevoMiembro,
					this.tareaSimpleConMiembro.getMiembroAsignado());

			// seteo el mock iniciada en la tarea, porq el metodo
			// modiciarMiembroAsignado
			// me cambia el estado por Iniciada real y estoy trabajando con
			// mock.
			this.tareaSimpleConMiembro.setEstado(this.iniciada);
			this.tareaSimpleConMiembro.verificarSiEstaIniciada();
			// verifico si los mensajes le llegaron a los mock creada y iniciada
			verify(this.creada, this.iniciada);
		} catch (NoPuedeCambiarseElEstadoExcepccion ex) {
			ex.printStackTrace();
		}

	}

	public void testModificarMiembroAsignadoYaTeniendoUno() {
	
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
	public void testIniciate() {
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

	/**
	 * Test modificarMiembroAsignado para TareaSimple
	 */
	public void testPausateCuandoPuede() {

	}

	public void testPausateCuandoNoPuede() {

	}

	public void testVerificarSiEstaPausada() {

	}

	public void testVerificarSiEstaEnTrabajo() {

	}

	public void testVerificarSiEstaIniciada() {

	}

	public void testVerificarSiEstaCreada() {

	}

	public void testVerificarSiEstaFinalizada() {

	}

	public void testVerificarSiEstaCerrada() {

	}

	public String getNombreTs() {
		return nombreTs;
	}

	public void setNombreTs(String nombreTs) {
		this.nombreTs = nombreTs;
	}

	public String getDescripcionTs() {
		return descripcionTs;
	}

	public void setDescripcionTs(String descripcionTs) {
		this.descripcionTs = descripcionTs;
	}

	public Fecha getFechaTs() {
		return fechaTs;
	}

	public void setFechaTs(Fecha fechaTs) {
		this.fechaTs = fechaTs;
	}

	public String getNombreTsCM() {
		return nombreTsCM;
	}

	public void setNombreTsCM(String nombreTsCM) {
		this.nombreTsCM = nombreTsCM;
	}

	public String getDescripcionTsCM() {
		return descripcionTsCM;
	}

	public void setDescripcionTsCM(String descripcionTsCM) {
		this.descripcionTsCM = descripcionTsCM;
	}

	public Fecha getFechaTsCM() {
		return fechaTsCM;
	}

	public void setFechaTsCM(Fecha fechaTsCM) {
		this.fechaTsCM = fechaTsCM;
	}

	public Estado getCreada() {
		return creada;
	}

	public void setCreada(Estado creada) {
		this.creada = creada;
	}

	public Cerrada getCerrada() {
		return cerrada;
	}

	public void setCerrada(Cerrada cerrada) {
		this.cerrada = cerrada;
	}

	public Iniciada getIniciada() {
		return iniciada;
	}

	public void setIniciada(Iniciada iniciada) {
		this.iniciada = iniciada;
	}

	public EnTrabajo getEnTrabajo() {
		return enTrabajo;
	}

	public void setEnTrabajo(EnTrabajo enTrabajo) {
		this.enTrabajo = enTrabajo;
	}

	public Pausada getPausada() {
		return pausada;
	}

	public void setPausada(Pausada pausada) {
		this.pausada = pausada;
	}

	public Finalizada getFinalizada() {
		return finalizada;
	}

	public void setFinalizada(Finalizada finalizada) {
		this.finalizada = finalizada;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public Miembro getMiembro() {
		return miembro;
	}

	public void setMiembro(Miembro miembro) {
		this.miembro = miembro;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Usuario getNuevoUsuario() {
		return nuevoUsuario;
	}

	public void setNuevoUsuario(Usuario nuevoUsuario) {
		this.nuevoUsuario = nuevoUsuario;
	}

	public String getNombreNuevoUsuario() {
		return nombreNuevoUsuario;
	}

	public void setNombreNuevoUsuario(String nombreNuevoUsuario) {
		this.nombreNuevoUsuario = nombreNuevoUsuario;
	}

	public String getEmailNuevoUsuario() {
		return emailNuevoUsuario;
	}

	public void setEmailNuevoUsuario(String emailNuevoUsuario) {
		this.emailNuevoUsuario = emailNuevoUsuario;
	}

	public Miembro getNuevoMiembro() {
		return nuevoMiembro;
	}

	public void setNuevoMiembro(Miembro nuevoMiembro) {
		this.nuevoMiembro = nuevoMiembro;
	}

	public String getNuevoRol() {
		return nuevoRol;
	}

	public void setNuevoRol(String nuevoRol) {
		this.nuevoRol = nuevoRol;
	}

}
