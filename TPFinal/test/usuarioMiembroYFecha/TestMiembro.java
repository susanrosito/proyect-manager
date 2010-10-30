package usuarioMiembroYFecha;


import junit.framework.Assert;
import junit.framework.TestCase;

public class TestMiembro extends TestCase {

	Usuario unUsuario;
	Usuario otroUsuario;
	
	private Miembro unMiembro;
	private Miembro otroMiembro;
	
	public void setUp()
	{
		this.unUsuario=new Usuario("Ignacio Negrette", "i_neg@yahoo.com.ar");
		this.otroUsuario=new Usuario("Tatiana Molinari", "m.tati@yahoo.com.ar");
		
		this.unMiembro=new Miembro(this.unUsuario, "Desarrollador");
		this.otroMiembro=new Miembro(this.otroUsuario, "Tester");
		
	}
	
	public void testConstructor()
	{
		Assert.assertEquals(0,this.getUnMiembro().getHsTrabajadas());
		Assert.assertEquals(0,this.getOtroMiembro().getHsTrabajadas());
		
		Assert.assertEquals("Desarrollador",this.getUnMiembro().getRol());
		Assert.assertEquals("Tester",this.getOtroMiembro().getRol());
		
		Assert.assertSame(this.getUnUsuario(), this.getUnMiembro().getUsuario());
		Assert.assertSame(this.getOtroUsuario(), this.getOtroMiembro().getUsuario());
		
		
	}
	
	public void testCambiarRol(){
		
		
		String rolAnteriorUnMiembro=this.getUnMiembro().getRol();
		String rolAnteriorOtroMiembro=this.getOtroMiembro().getRol();
		//Guarda los roles anteriores de ambas instancias
		
		Assert.assertEquals(rolAnteriorUnMiembro,this.getUnMiembro().getRol());
		Assert.assertEquals(rolAnteriorOtroMiembro,this.getOtroMiembro().getRol());
		//Comprueba que son los mismos		
		
		Miembro nuevoMiembro1=this.getUnMiembro().cambiarRol("Barrendero");
		Miembro nuevoMiembro2=this.getOtroMiembro().cambiarRol("Trapeadora");
		//Cambia los rolos con otros pasados por parametros
		
		Assert.assertNotSame(nuevoMiembro1, this.getUnMiembro());
		Assert.assertNotSame(nuevoMiembro2, this.getOtroMiembro());
		//Comprueba que las instancias luego de cambiar los nombres son
		//Diferentes a las originales
		
		Assert.assertFalse(nuevoMiembro1.getRol()==rolAnteriorUnMiembro);		
		Assert.assertEquals("Barrendero",nuevoMiembro1.getRol());
				
		Assert.assertFalse(nuevoMiembro2.getRol()==rolAnteriorOtroMiembro);
		Assert.assertEquals("Trapeadora",nuevoMiembro2.getRol());
		//Comprueba que los roles anteriores no estan en las nuevas instancias
		// y comprueba que los roles pasados por paramentrso son los que estan
		// en las nuevas instancias.
		
		
		
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
