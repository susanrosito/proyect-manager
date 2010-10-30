package estados;

import estados.Cerrada;




public class TestCerrada extends TestEstado {

	
	public void setUp()
	{
		this.setEstado(Cerrada.GetInstance());
		this.setUnaInstancia(Cerrada.GetInstance());
		this.setOtraInstancia(Cerrada.GetInstance());
	}
	public void testVerificarSiEstaCerrada()
    { assertTrue(this.getEstado().verificarSiEstaCerrada()); }
}