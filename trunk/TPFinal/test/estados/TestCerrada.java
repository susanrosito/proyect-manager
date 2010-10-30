package estados;

import estados.Cerrada;




public class TestCerrada extends TestEstado {

	
	public void setUp()
	{
		
		this.setUnaInstancia(Cerrada.GetInstance());
		this.setOtraInstancia(Cerrada.GetInstance());
	}

}