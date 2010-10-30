package estados;

import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import estados.EnTrabajo;
import estados.Finalizada;
import estados.Pausada;






public class TestPausada extends TestEstado {

	

	public void setUp()
	{
        super.setUp();
		this.setUnaInstancia(Pausada.GetInstance());
		this.setOtraInstancia(Pausada.GetInstance());;
		this.setEstado(Pausada.GetInstance());
	}

    public void testEnTrabajo() throws NoPuedeCambiarseElEstadoExcepccion{
    	
    	this.getTarea().setEstado(EnTrabajo.GetInstance());           
		replay(this.getTarea());               
		
		this.getEstado().enTrabajo(this.getTarea()); 
		
		verify(this.getTarea());
    }
	
    public void testFinalizada() throws NoPuedeCambiarseElEstadoExcepccion	
	{
    	this.getTarea().setEstado(Finalizada.GetInstance());           
		replay(this.getTarea());               
		
		this.getEstado().finalizada(this.getTarea()); 
		
		verify(this.getTarea());
	}
    public void testVerificarSiEstaPausada()
    { assertFalse(this.getEstado().verificarSiEstaPausada()); }
	

}
