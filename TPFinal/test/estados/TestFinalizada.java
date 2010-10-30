package estados;

import estados.Finalizada;





public class TestFinalizada extends TestEstado {

	
	
	public void setUp()
	{
		this.setUnaInstancia(Finalizada.GetInstance());
		this.setOtraInstancia(Finalizada.GetInstance());
	}

	
}
