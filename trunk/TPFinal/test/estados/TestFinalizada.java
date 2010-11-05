package estados;

import estados.Finalizada;





public class TestFinalizada extends TestEstado {

	
	
	public void setUp()
	{
		super.setUp();
		
		this.setEstado(Finalizada.GetInstance());
		this.setUnaInstancia(Finalizada.GetInstance());
		this.setOtraInstancia(Finalizada.GetInstance());
	}
	public void testVerificarSiEstaFinalizada()
    { assertTrue(this.getEstado().verificarSiEstaFinalizada()); }
	
}
