package estados;


import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import estados.Creada;
import estados.Iniciada;



public class TestCreada extends TestEstado {
	

	
	public void setUp()
	{
		super.setUp();
		this.setUnaInstancia(Creada.GetInstance());
		this.setOtraInstancia(Creada.GetInstance());
		this.setEstado(Creada.GetInstance());
	}


	public void testIniciada() throws NoPuedeCambiarseElEstadoExcepccion{
		this.getTarea().setEstado(Iniciada.GetInstance());           
		replay(this.getTarea());               
		
		this.getEstado().iniciada(this.getTarea()); 
		
		verify(this.getTarea());
	}
	public void testVerificarSiEstaCreada()
    { assertTrue(this.getEstado().verificarSiEstaCreada()); }
	
}
