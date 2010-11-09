package proyectoYSistema;

import java.util.LinkedList;
import java.util.List;
import usuarioMiembroYFecha.*;
import junit.framework.Assert;
import junit.framework.TestCase;

public class SistemaTestCase extends TestCase{

	
	 List<Proyecto> proyectos;
	 List<Usuario> usuarios;

	protected void setUp() {
		
	
		System.out.println("setup");
	}
	
	
	public  void testConstructorSistema() {
		
		
		
		// el sistema va a ser un singleton //
		Sistema sist1 = Sistema.GetInstance();	
		
		Sistema sist2 = Sistema.GetInstance();	
	
		
		Assert.assertSame(sist2, sist1); //compara las instancias para conocer si son la misma
		Assert.assertSame(sist1, sist2); //compara las instancias para conocer si son la misma
		
		assertEquals(new LinkedList<Proyecto>(), sist1.getProyectos());
		assertEquals(new LinkedList<Proyecto>(), sist2.getProyectos());
		
		
		assertEquals(new LinkedList<Proyecto>(), sist1.getUsuarios());
		assertEquals(new LinkedList<Proyecto>(), sist2.getUsuarios());
		
		
	}
	
	public void test() {
		
	}
	
}
