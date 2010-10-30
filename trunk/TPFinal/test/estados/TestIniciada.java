package estados;

import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import estados.EnTrabajo;
import estados.Finalizada;
import estados.Iniciada;






public class TestIniciada extends TestEstado {
	
	
	
	public void setUp()
	{
		super.setUp();
		this.setUnaInstancia(Iniciada.GetInstance());
		this.setOtraInstancia(Iniciada.GetInstance());;
		this.setEstado(Iniciada.GetInstance());
	}

	public void testEnTrabajo() throws NoPuedeCambiarseElEstadoExcepccion{

    	this.getTarea().setEstado(EnTrabajo.GetInstance());           
	    replay(this.getTarea());               
	
	    this.getEstado().enTrabajo(this.getTarea()); 
	
	    verify(this.getTarea());;
			
			
	}
	
    public void testFinalizada() throws NoPuedeCambiarseElEstadoExcepccion	
	{this.getTarea().setEstado(Finalizada.GetInstance());           
    replay(this.getTarea());               
	
    this.getEstado().finalizada(this.getTarea()); 

    verify(this.getTarea());;
	}
	
    public void testVerificarSiEstaIniciada()
    { assertTrue(this.getEstado().verificarSiEstaIniciada()); }
	
}
