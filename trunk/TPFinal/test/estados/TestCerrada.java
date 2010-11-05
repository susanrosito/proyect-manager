package estados;

import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import estados.Cerrada;




public class TestCerrada extends TestEstado {

	
	public void setUp()
	{
		super.setUp();
		this.setEstado(Cerrada.GetInstance());
		this.setUnaInstancia(Cerrada.GetInstance());
		this.setOtraInstancia(Cerrada.GetInstance());
	}
	
	public void testIniciada() throws NoPuedeCambiarseElEstadoExcepccion{
		this.getTarea().setEstado(Iniciada.GetInstance());           
		replay(this.getTarea());               
		
		this.getEstado().iniciada(this.getTarea()); 
		
		verify(this.getTarea());
	}
	
	public void testVerificarSiEstaCerrada()
    { assertTrue(this.getEstado().verificarSiEstaCerrada()); }
}