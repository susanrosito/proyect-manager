package proyectoYSistema;

import java.util.LinkedList;
import java.util.List;

import usuarioMiembroYFecha.*;
import junit.framework.Assert;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;

public class SistemaTestCase extends TestCase {
	Sistema sistema;
	List<Proyecto> proyectos;
	List<Usuario> usuarios;

	Proyecto proyecto;
	Usuario usuario;

	protected void setUp() {
		sistema = Sistema.GetInstance();
		usuario = createMock(Usuario.class);
		proyecto = new Proyecto("PrimerProyecto",
				"Este es el proyecto de prueba", usuario);

	}

	/**
	 * es el test del constructor de la clase Sistema. el sistema va a ser un
	 * singleton se va a crear con una lista de proyectos y una lista de
	 * usuarios vacia.
	 */
	public void testConstructorSistema() {

		// el sistema va a ser un singleton //
		Sistema sist1 = Sistema.GetInstance();

		Sistema sist2 = Sistema.GetInstance();

		Assert.assertSame(sist2, sist1); // compara las instancias para conocer
		// si son la misma
		Assert.assertSame(sist1, sist2); // compara las instancias para conocer
		// si son la misma

		// al crearse la UNICA instancia de sistema se debe crear con una lista
		// de proyectos vacia
		assertEquals(new LinkedList<Proyecto>(),sist1.getProyectos());

		// al crearse la UNICA instancia de sistema se debe crear con una lista
		// de usuarios vacia
		assertEquals(new LinkedList<Usuario>(), sist1.getUsuarios());
	}

	/**
	 * Este metodo crea un proyecto a partir de un nombre,una descripcion y un
	 * usario el cual va a ser designado como el miembro creador. Cuando el
	 * proyecto es creado con exito se agrega a la lista de proyectos del
	 * sistema.
	 */
	public void testcrearUnProyecto() {

		// envio el mensaje crearUnProyecto.
		sistema.crearUnProyecto("Proyecto1", "realizar almuerzo", this
				.getUsuario());

		assertEquals("la cantidad de proyectos existentes en el sistema es 1",
				1, sistema.getProyectos().size());
	}

	/**
	 * 
	 */
	public void testcrearUnUsuario() {

		// envio el mensaje crearUnUsuario.
		sistema.crearUnUsuario("Juan", "usuario@gmail.com");

		assertEquals("la cantidad de usuarios existentes en el sistema es 1",
				1, sistema.getUsuarios().size());
	}
	
	
	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
