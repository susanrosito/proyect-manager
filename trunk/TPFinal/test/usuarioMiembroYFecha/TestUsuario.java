package usuarioMiembroYFecha;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestUsuario extends TestCase {

	private Usuario unUsuario;
	private Usuario otroUsuario;
	
	public void setUp()
	{
		this.unUsuario=new Usuario("Ignacio Negrette", "i_neg@yahoo.com.ar");
		this.otroUsuario=new Usuario("Tatiana Molinari", "m.tati@yahoo.com.ar");
		
	}
	/**
	 * Este test verifica que el constructor guarde correctamente en las variables los
	 * objetos pasados por parametro.
	 */
	public void testConstructor()
	{
		Assert.assertNotSame(this.getUnUsuario(), this.getOtroUsuario());
		//Chequea que los objetos creados sean diferentes.
		
		Assert.assertEquals("Ignacio Negrette", this.getUnUsuario().getNombre());
		Assert.assertEquals("i_neg@yahoo.com.ar", this.getUnUsuario().getEmail());
		
		//Chequea que los valores pasados por parametro sean los
		//que tiene guardados los objetos 
		
		Assert.assertEquals("Tatiana Molinari", this.getOtroUsuario().getNombre());
		Assert.assertEquals("m.tati@yahoo.com.ar", this.getOtroUsuario().getEmail());
		
		
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
