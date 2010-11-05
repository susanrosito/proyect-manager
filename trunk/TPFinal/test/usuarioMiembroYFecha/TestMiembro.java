package usuarioMiembroYFecha;


import junit.framework.Assert;
import junit.framework.TestCase;
import static org.easymock.EasyMock.createMock;


public class TestMiembro extends TestCase {

	Usuario unUsuario;
	Usuario otroUsuario;
	
	private Miembro unMiembro;
	private Miembro otroMiembro;
	
	public void setUp()
	{
		this.unUsuario= createMock(Usuario.class);
		this.otroUsuario= createMock(Usuario.class);
				
		this.unMiembro=new Miembro(this.unUsuario, "Desarrollador");
		this.otroMiembro=new Miembro(this.otroUsuario, "Tester");
		
	}
	/**
	 * Este test comprueba que los valores pasados por parametros se guarden correctamente en
	 * las variables del Miembro.
	 */
	public void testConstructor()
	{
		Assert.assertEquals(0,this.getUnMiembro().getHsTrabajadas());
		Assert.assertEquals(0,this.getOtroMiembro().getHsTrabajadas());
		
		Assert.assertEquals("Desarrollador",this.getUnMiembro().getRol());
		Assert.assertEquals("Tester",this.getOtroMiembro().getRol());
		
		Assert.assertSame(this.getUnUsuario(), this.getUnMiembro().getUsuario());
		Assert.assertSame(this.getOtroUsuario(), this.getOtroMiembro().getUsuario());
		
		
	}
	/**
	 * Este test comprueba que al cambiar de rol a un miembro se cree una nueva instancia
	 * de miembro y cambie solo el rol.
	 */
	public void testCambiarRol(){
		
		//Guarda los roles anteriores de ambas instancias
		String rolAnteriorUnMiembro=this.getUnMiembro().getRol();
		String rolAnteriorOtroMiembro=this.getOtroMiembro().getRol();
		
		//Comprueba que son los mismos	
		Assert.assertEquals(rolAnteriorUnMiembro,this.getUnMiembro().getRol());
		Assert.assertEquals(rolAnteriorOtroMiembro,this.getOtroMiembro().getRol());
			
		//Cambia los roles con otros pasados por parametros
		Miembro nuevoMiembro1=this.getUnMiembro().cambiarRol("Barrendero");
		Miembro nuevoMiembro2=this.getOtroMiembro().cambiarRol("Trapeadora");
		
		/*
		 * Comprueba que las instancias luego de cambiar los nombres son
		 * Diferentes a las originales
		 */
		Assert.assertNotSame(nuevoMiembro1, this.getUnMiembro());
		Assert.assertNotSame(nuevoMiembro2, this.getOtroMiembro());
		
		/*
		 * Comprueba que los roles anteriores no estan en las nuevas instancias
		 * y comprueba que los roles pasados por paramentrso son los que estan
		 * en las nuevas instancias.
		 */ 
		Assert.assertFalse(nuevoMiembro1.getRol()==rolAnteriorUnMiembro);		
		Assert.assertEquals("Barrendero",nuevoMiembro1.getRol());
				
		Assert.assertFalse(nuevoMiembro2.getRol()==rolAnteriorOtroMiembro);
		Assert.assertEquals("Trapeadora",nuevoMiembro2.getRol());
		
		
		
		
	}
	
	public Usuario getUnUsuario() {
		return unUsuario;
	}

	public void setUnUsuario(Usuario unUsuario) {
		this.unUsuario = unUsuario;
	}

	public Usuario getOtroUsuario() {
		return otroUsuario;
	}

	public void setOtroUsuario(Usuario otroUsuario) {
		this.otroUsuario = otroUsuario;
	}

	public Miembro getUnMiembro() {
		return unMiembro;
	}



	public void setUnMiembro(Miembro unMiembro) {
		this.unMiembro = unMiembro;
	}



	public Miembro getOtroMiembro() {
		return otroMiembro;
	}



	public void setOtroMiembro(Miembro otroMiembro) {
		this.otroMiembro = otroMiembro;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
